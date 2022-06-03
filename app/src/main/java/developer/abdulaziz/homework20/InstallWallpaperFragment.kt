package developer.abdulaziz.homework20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.homework20.Object.MyObject
import developer.abdulaziz.homework20.databinding.FragmentInstallWallpaperBinding

class InstallWallpaperFragment : Fragment() {
    private lateinit var binding: FragmentInstallWallpaperBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentInstallWallpaperBinding.inflate(layoutInflater)
        binding.apply {
            imageWallpapers.setImageResource(MyObject.dataClassWallpapers.image)
            cardViewBack.setOnClickListener { findNavController().popBackStack() }
            cardViewInstallAll.setOnClickListener {
                Toast.makeText(root.context, "Install Main Screen and Screen Lock", Toast.LENGTH_SHORT).show()
            }
            cardViewInstallMain.setOnClickListener {
                Toast.makeText(root.context, "Install Main Screen", Toast.LENGTH_SHORT).show()
            }
            cardViewInstallScreen.setOnClickListener {
                Toast.makeText(root.context, "Install Screen Lock", Toast.LENGTH_SHORT).show()
            }
            return root
        }
    }
}