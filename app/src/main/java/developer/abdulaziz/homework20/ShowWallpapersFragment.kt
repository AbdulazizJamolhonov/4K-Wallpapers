package developer.abdulaziz.homework20

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.MyShare.MyShare
import developer.abdulaziz.homework20.Object.MyObject
import developer.abdulaziz.homework20.databinding.FragmentShowWallpapersBinding
import developer.abdulaziz.homework20.databinding.ItemBottomBinding
//import eightbitlab.com.blurview.BlurView
//import eightbitlab.com.blurview.RenderScriptBlur
//import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


@SuppressLint("ResourceType", "SetTextI18n")
class ShowWallpapersFragment : Fragment() {
    private lateinit var binding: FragmentShowWallpapersBinding
    private lateinit var arrayListWallpapers: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentShowWallpapersBinding.inflate(layoutInflater)
        binding.apply {
            MyShare.init(root.context)
//            buildBlurView()
            buildLiked()
            imageWallpapers.setImageResource(MyObject.dataClassWallpapers.image)
            cardViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            cardViewInstall.setOnClickListener { findNavController().navigate(R.id.installWallpaperFragment) }
            cardViewShare.setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, MyObject.dataClassWallpapers.image)
                    type = "*/*"
                }
                startActivity(Intent.createChooser(sendIntent,
                    MyObject.dataClassWallpapers.imageName))
            }
            cardViewAbout.setOnClickListener {
                val bottom = BottomSheetDialog(root.context, R.style.NewDialog)
                val item = ItemBottomBinding.inflate(layoutInflater)
                item.apply {
                    itemWebsite.text = "Website: www.${MyObject.dataClassWallpapers.imageType}.com"
                    itemDownload.text = "Download: ${Random.nextInt(100000, 999999)}"
                    val down = Random.nextInt(3, 10)
                    val size = Random.nextInt(2000, 4000)
                    itemSize.text = "Size: $down - ${down + 1}MB, ${size}x${size + 700}"
                }
                bottom.setContentView(item.root)
                bottom.show()
            }

            return root
        }
    }

    private fun buildLiked() {
        binding.apply {
            var forLike = if (MyObject.dataClassWallpapers.liked) {
                imageLiked.setImageResource(R.drawable.frame_ic_liked_click)
                2
            } else {
                imageLiked.setImageResource(R.drawable.frame_ic_liked)
                1
            }

            cardViewLiked.setOnClickListener {
                forLike += 1
                if (forLike % 2 == 0) {
                    imageLiked.setImageResource(R.drawable.frame_ic_liked_click)
                    MyObject.dataClassWallpapers.liked = true
                    editArrayList()
                } else {
                    imageLiked.setImageResource(R.drawable.frame_ic_liked)
                    MyObject.dataClassWallpapers.liked = false
                    editArrayList()
                }
            }
        }
    }

    private fun editArrayList() {
        arrayListWallpapers = ArrayList()
        arrayListWallpapers.addAll(MyShare.dataList)
        for (i in 0 until arrayListWallpapers.size) {
            if (arrayListWallpapers[i].id == MyObject.dataClassWallpapers.id) {
                arrayListWallpapers[i].liked = MyObject.dataClassWallpapers.liked
            }
        }
        MyShare.dataList = arrayListWallpapers
    }

//    private fun buildBlurView() {
//        binding.apply {
//            blurViewFilter.myBlurs()
//            blurViewBack.myBlurs()
//            blurViewShare.myBlurs()
//            blurViewAbout.myBlurs()
//            blurViewDownload.myBlurs()
//            blurViewInstall.myBlurs()
//            blurViewFilter.myBlurs()
//            blurViewLiked.myBlurs()
//        }
//    }

//    private fun BlurView.myBlurs() {
//        val decorView = requireActivity().window.decorView
//        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
//        this.setupWith(rootView)
//            .setFrameClearDrawable(decorView.background)
//            .setBlurAlgorithm(RenderScriptBlur(binding.root.context))
//            .setBlurRadius(20f)
//            .setBlurAutoUpdate(true)
//            .setHasFixedTransformationMatrix(true)
//    }
}