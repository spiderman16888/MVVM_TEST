package xyz.markapp.markmvvmtest1.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import xyz.markapp.markmvvmtest1.databinding.ActivityDisplayUserDetailBinding
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.model.User


class DetailFragment() : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var _binding: ActivityDisplayUserDetailBinding? = null

    var developerModel: DeveloperModel? = null;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    constructor(currentDeveloper: DeveloperModel) : this() {
        developerModel = currentDeveloper;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //activity?.actionBar?.setDisplayHomeAsUpEnabled(true);
        //activity?.actionBar?.setDisplayShowHomeEnabled(true);

        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        ///init the View Model
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        // Obtain binding
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_display_user_detail)
        _binding = ActivityDisplayUserDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        detailViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        // Get the Intent that started this activity and extract the string
//        val mDeveloperModel = intent?.getStringExtra("DeveloperModel")
//        var developerModel: DeveloperModel? = Gson().fromJson(mDeveloperModel, DeveloperModel::class.java)

        // Data is received
//        developerModel = detailViewModel.getDeveloper()?.value?.also {
//
//            developerModel = it
//        };
//        detailViewModel!!.getDeveloper()?.observe(viewLifecycleOwner, { item ->
//            developerModel = item
        do_get_UserDetail(developerModel);
//        });

        /*   detailViewModel!!.getDeveloper()?.observe(getViewLifecycleOwner(), { item ->
               developerModel = item
           });*/


        // Bind layout with ViewModel
//        binding?.developerModel = developerModel

        //---
        // LiveData needs the lifecycle owner
//        binding.lifecycleOwner = this


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
                    binding?.developerModel = developerModel
                })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false);
                requireActivity().onBackPressed();
            }

        }
        return true
    }


}
