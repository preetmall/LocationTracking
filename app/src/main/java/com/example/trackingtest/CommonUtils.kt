import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.trackingtest.LocationHelper
import com.example.trackingtest.MyLocationListener
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.lang.Exception

fun addFragment(fragmentManager: FragmentManager, fragment: Fragment, id:Int){
    val transection: FragmentTransaction =fragmentManager.beginTransaction()
    fragment?.let {
        transection.add(id,fragment)
        transection.commitAllowingStateLoss()
    }
}

fun replaceFragment(fragmentManager: FragmentManager,fragment: Fragment,id: Int){
    val transaction: FragmentTransaction =fragmentManager.beginTransaction()
    transaction.replace(id, fragment)
    transaction.commit()
}
fun cretaeFile(location:Location){
    val Root = Environment.getExternalStorageDirectory()
    try {
        val file = File(Root, "TrackingText.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileOutputStream = FileOutputStream(file, true)
        val writer = OutputStreamWriter(fileOutputStream)
        writer.append("${location?.latitude} \t  ${location?.longitude} \n")
        writer.close()
        fileOutputStream.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
