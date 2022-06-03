package developer.abdulaziz.homework20

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.abdulaziz.homework20.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var countDownTimer = object : CountDownTimer(500, 100) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            findNavController().navigate(R.id.action_splashFragment_to_menuFragment)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        countDownTimer.start()

        return binding.root
    }

}