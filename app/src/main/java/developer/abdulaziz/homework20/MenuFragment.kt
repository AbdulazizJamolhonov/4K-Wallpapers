package developer.abdulaziz.homework20

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.MyShare.MyShare
import developer.abdulaziz.homework20.databinding.FragmentMenuBinding
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var arrayListWallpapers: ArrayList<User>
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        binding.apply {
            MyShare.init(root.context)
            arrayListWallpapers = ArrayList()
            navHostFragment =
                childFragmentManager.findFragmentById(R.id.fragmentNavigation) as NavHostFragment
            navController = navHostFragment.navController
            arrayListWallpapers.addAll(MyShare.dataList)

            buildBlurView()
            fragmentNavigation(0)

            imageHomeBottom.setOnClickListener {
                fragmentNavigation(0)
            }

            imagePopularBottom.setOnClickListener {
                fragmentNavigation(1)
            }

            imageRandomBottom.setOnClickListener {
                fragmentNavigation(2)
            }

            imageLikedBottom.setOnClickListener {
                fragmentNavigation(3)
            }

            lyHome.setOnClickListener {
                fragmentNavigation(0)
                drawerLayout.closeDrawer(Gravity.START)
            }

            lyPopular.setOnClickListener {
                fragmentNavigation(1)
                drawerLayout.closeDrawer(Gravity.START)
            }

            lyRandom.setOnClickListener {
                fragmentNavigation(2)
                drawerLayout.closeDrawer(Gravity.START)
            }

            lyLiked.setOnClickListener {
                fragmentNavigation(3)
                drawerLayout.closeDrawer(Gravity.START)
            }

            return root
        }
    }

    private fun fragmentNavigation(int: Int) {
        binding.apply {
            imageHomeBottomIndicator.visibility = View.INVISIBLE
            imageHomePopularIndicator.visibility = View.INVISIBLE
            imageHomeRandomIndicator.visibility = View.INVISIBLE
            imageHomeLikedIndicator.visibility = View.INVISIBLE
            when (int) {
                0 -> {
                    imageHomeBottomIndicator.visibility = View.VISIBLE
                    navController.navigate(R.id.homeFragment)
                }
                1 -> {
                    imageHomePopularIndicator.visibility = View.VISIBLE
                    navController.navigate(R.id.popularFragment)
                }
                2 -> {
                    imageHomeRandomIndicator.visibility = View.VISIBLE
                    navController.navigate(R.id.randomFragment)
                }
                3 -> {
                    imageHomeLikedIndicator.visibility = View.VISIBLE
                    navController.navigate(R.id.likedFragment)
                }
            }
        }
    }

    private fun buildBlurView() {
        val decorView = requireActivity().window.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        binding.blurView.setupWith(rootView)
            .setFrameClearDrawable(decorView.background)
            .setBlurAlgorithm(RenderScriptBlur(requireActivity()))
            .setBlurRadius(20f)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)
    }
}