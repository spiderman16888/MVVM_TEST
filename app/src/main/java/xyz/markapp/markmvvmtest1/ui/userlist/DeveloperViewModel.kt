package xyz.markapp.markmvvmtest1.ui.userlist

import android.app.Application
import android.content.ClipData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.repository.DeveloperRepository
import android.content.ClipData.Item

import androidx.lifecycle.MutableLiveData
import xyz.markapp.markmvvmtest1.model.User


class DeveloperViewModel(application: Application) : AndroidViewModel(application) {
    private val mDeveloperRepository: DeveloperRepository

//    val allDeveloper: LiveData<List<DeveloperModel>>
//        get() = mDeveloperRepository.getMutableLiveData(0)

    init {
        mDeveloperRepository = DeveloperRepository()
    }

    fun getNextUsers(): LiveData<List<DeveloperModel>> {
        mDeveloperRepository.now_page++;
        val next_since = mDeveloperRepository.now_page * mDeveloperRepository.per_page
        return mDeveloperRepository.getMutableLiveData(next_since)
    }





}
