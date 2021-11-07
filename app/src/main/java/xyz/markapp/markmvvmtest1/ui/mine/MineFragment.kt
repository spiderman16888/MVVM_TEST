package xyz.markapp.markmvvmtest1.ui.mine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.markapp.markmvvmtest1.databinding.FragmentMineBinding
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.model.User
import xyz.markapp.markmvvmtest1.ui.detail.DetailViewModel

class MineFragment : Fragment() {

    private lateinit var mineViewModel: MineViewModel
    private var _binding: FragmentMineBinding? = null

    var developerModel: DeveloperModel? = null
    var userModel: User? = null

    private lateinit var detailViewModel: DetailViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ///init the View Model
        mineViewModel = ViewModelProvider(this).get(MineViewModel::class.java)

        ///init the View Model
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Obtain binding
        _binding = FragmentMineBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        mineViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        developerModel = DeveloperModel();
        developerModel?.login = "spiderman16888";

        do_get_UserDetail(developerModel);
        //spiderman16888

        //--


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun do_get_UserDetail(developerModel: DeveloperModel?) {

        if (developerModel == null)
            return

        activity?.let {
            detailViewModel!!.getUserDatail(developerModel)?.observe(
                it,
                Observer<User?> { user ->
                    ///if any thing chnage the update the UI
                    developerModel?.user = user;

                    // Bind layout with ViewModel
                    binding?.userModel = user
                })
        }
    }
}
