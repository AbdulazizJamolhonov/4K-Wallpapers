package developer.abdulaziz.homework20.MyShare

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdulaziz.homework20.Class.User

object MyShare {
    private const val NAME = "my_cache_file"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var dataList: ArrayList<User>
        get() = gsonStringToList(sharedPreferences.getString("keyList", "[]")!!)
        set(value) = sharedPreferences.edit {
            it.putString("keyList", listToGsonString(value))
        }

    private fun gsonStringToList(gsonString: String): ArrayList<User> {
        val type = object : TypeToken<ArrayList<User>>() {}.type
        return Gson().fromJson(gsonString, type)
    }

    private fun listToGsonString(list: ArrayList<User>): String {
        return Gson().toJson(list)
    }
}