package xyz.markapp.markmvvmtest1.ui.mine

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.markapp.markmvvmtest1.model.User

class MineViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is mine fragment"
    }

    val text: LiveData<String> = _text



}
