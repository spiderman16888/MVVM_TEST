package xyz.markapp.markmvvmtest1.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.markapp.markmvvmtest1.R
import xyz.markapp.markmvvmtest1.databinding.ActivityDisplayUserDetailBinding
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.model.User
import xyz.markapp.markmvvmtest1.network.RetrofitClient


class DisplayUserDetailActivity : AppCompatActivity() {

//    private var user: DeveloperModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    //---


}
