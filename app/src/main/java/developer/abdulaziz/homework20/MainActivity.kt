package developer.abdulaziz.homework20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.MyShare.MyShare
import developer.abdulaziz.homework20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var arrayListWallpapers: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(binding.root)
        MyShare.init(this)
        arrayListWallpapers = ArrayList()
        arrayListWallpapers.addAll(MyShare.dataList)
        if (arrayListWallpapers.isEmpty()) {
            arraylistCollection()
        }
    }

    private fun arraylistCollection() {
        loadList("Technology", R.drawable.image__1_, true)
        loadList("Technology", R.drawable.image__5_, true)
        loadList("Technology", R.drawable.image__10_, false)
        loadList("Technology", R.drawable.image__13_, false)
        loadList("Technology", R.drawable.image__29_, false)
        loadList("Technology", R.drawable.image__31_, false)
        loadList("Book", R.drawable.image__2_, true)
        loadList("Book", R.drawable.image__11_, false)
        loadList("Book", R.drawable.image__23_, false)
        loadList("Book", R.drawable.image__24_, false)
        loadList("Book", R.drawable.image__28_, false)
        loadList("Uzbekistan", R.drawable.image__12_, false)
        loadList("Uzbekistan", R.drawable.image__3_, true)
        loadList("Uzbekistan", R.drawable.image__4_, true)
        loadList("Uzbekistan", R.drawable.image__14_, false)
        loadList("Uzbekistan", R.drawable.image__15_, false)
        loadList("Animals", R.drawable.image__6_, true)
        loadList("Animals", R.drawable.image__16_, false)
        loadList("Animals", R.drawable.image__17_, false)
        loadList("Animals", R.drawable.image__18_, false)
        loadList("Animals", R.drawable.image__26_, false)
        loadList("Coding", R.drawable.image__7_, true)
        loadList("Coding", R.drawable.image__30_, false)
        loadList("Nature", R.drawable.image__9_, false)
        loadList("Nature", R.drawable.image__19_, false)
        loadList("Nature", R.drawable.image__22_, false)
        loadList("Nature", R.drawable.image__25_, false)
        loadList("Nature", R.drawable.image__27_, false)
        MyShare.dataList = arrayListWallpapers
    }

    private fun loadList(imageType: String, image: Int, like: Boolean) {
        arrayListWallpapers.add(User(imageType, imageType, image, like, arrayListWallpapers.size))
    }
}
