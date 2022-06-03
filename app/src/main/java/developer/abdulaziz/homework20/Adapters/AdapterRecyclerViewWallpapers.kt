package developer.abdulaziz.homework20.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import developer.abdulaziz.homework20.Class.User
import developer.abdulaziz.homework20.Object.MyObject
import developer.abdulaziz.homework20.R
import com.squareup.picasso.Picasso

class AdapterRecyclerViewWallpapers(
    val context: Context,
    private val arrayList: ArrayList<User>,
    val navController: NavController,
    val myWallpapersOnClickListener: MyWallpapersOnClickListener,
) :
    RecyclerView.Adapter<AdapterRecyclerViewWallpapers.VH>() {
    inner class VH(private var itemRV: View) : RecyclerView.ViewHolder(itemRV) {
        fun onBind(int: Int, position: Int) {
            val image = itemRV.findViewById<ImageView>(R.id.image_itemWallpapers)
            Picasso.get().load(int).resize(1024, 800).onlyScaleDown().centerCrop().into(image)
            itemRV.setOnClickListener {
                MyObject.dataClassWallpapers = User(arrayList[position].imageType,
                    arrayList[position].imageName,
                    arrayList[position].image,
                    arrayList[position].liked, arrayList[position].id)
                myWallpapersOnClickListener.onClick(navController)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(arrayList[position].image, position)

    }

    override fun getItemCount(): Int = arrayList.size
}

interface MyWallpapersOnClickListener {
    fun onClick(navController: NavController)
}