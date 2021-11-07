package xyz.markapp.markmvvmtest1.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.network.RetrofitClient
import java.util.*

class DeveloperRepository {
    private val mmDeveloperModelmist = ArrayList<DeveloperModel>()
    private val mutableLiveData = MutableLiveData<List<DeveloperModel>>()

    var now_page = -1
    val per_page = 100

    /*
    since 	integer 	query
    A user ID. Only return users with an ID greater than this ID.

    per_page 	integer 	query
    Results per page (max 100)
    Default: 30
     */

    ////call to internet and  retun  MutableLiveData
    fun getMutableLiveData(since: Int): MutableLiveData<List<DeveloperModel>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
//        val call = userDataService.apiRequestsArray
        val call = userDataService

        call.getUsers(per_page = per_page, since = since)
            ?.enqueue(object : Callback<JsonArray> {
                internal var message: String? = null

                override fun onResponse(call: Call<JsonArray>, resp: Response<JsonArray>) {

                    /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                    val gson = GsonBuilder().create()

                    if (resp != null && resp.body() != null) {

                        val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
                        //Log.e("data",json.toString())
                        if (json != null) {

                            for (i in 0..json.size() - 1) {
                                try {

                                    val jsonobj = JsonParser().parse(json.get(i).toString()).asJsonObject
                                    val responseModel = gson.fromJson(jsonobj, DeveloperModel::class.java)

                                    mmDeveloperModelmist.add(responseModel)

                                } catch (ex: Exception) {
                                    print(ex.localizedMessage)
                                }


                            }
                            mutableLiveData.value = mmDeveloperModelmist

                        }
                    }


                }

                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    t.printStackTrace()

                }
            })


        return mutableLiveData
    }

    companion object {
        //private var since = 0
         const val MAX_SIZE = 300
    }


}
