package xyz.markapp.markmvvmtest1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.model.User
import xyz.markapp.markmvvmtest1.network.RetrofitClient

class DetailViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    //---
 /*   private var saved_developer: MutableLiveData<DeveloperModel?>? = MutableLiveData()

    fun setDeveloper(developer: DeveloperModel?) {
        saved_developer!!.value = developer
    }

    fun getDeveloper(): LiveData<DeveloperModel?>? {
        return saved_developer
    }*/


    //---
    private var saved_user: MutableLiveData<User?>? = MutableLiveData()

    fun setUser(user: User?) {
        saved_user!!.value = user
    }

    fun getUser(): LiveData<User?>? {
        return saved_user
    }

//    var user: User? = null

//    val allDeveloper: LiveData<List<DeveloperModel>>
//        get() = mDeveloperRepository.getMutableLiveData(0)

//     init {
//          user = getUserDatail(null)
//     }

    /*   fun getNextUsers(): LiveData<List<DeveloperModel>> {
            mDeveloperRepository.now_page++;
            val next_since = mDeveloperRepository.now_page * mDeveloperRepository.per_page
            return mDeveloperRepository.getMutableLiveData(next_since)
       }*/

    fun getUserDatail(developerModel: DeveloperModel?): MutableLiveData<User?>? {

        if (developerModel == null)
            return null;

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service
        val call = userDataService

        call.getUser(developerModel?.login)
            ?.enqueue(object : Callback<User?> {
//                internal var message: String? = null

                override fun onResponse(call: Call<User?>, response: Response<User?>) {

                    /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                    if (response != null) {
                        //user.toString();
                        //developerModel?.user = user.body();
                        //Log.d("test", "get user.")

//                        binding?.tvLocation?.setText(developerModel?.user?.location)
//                        binding?.tvLink?.setText(developerModel?.user?.blog)

                        //set again.
                        //binding?.developerModel = developerModel
//                              return user.body();

                        setUser(response.body());

//                        return (response.body());
                    }
//                        return null;


                }

                override fun onFailure(call: Call<User?>, t: Throwable) {
                    t.printStackTrace()
                }

            })


        return saved_user;
    }

}
