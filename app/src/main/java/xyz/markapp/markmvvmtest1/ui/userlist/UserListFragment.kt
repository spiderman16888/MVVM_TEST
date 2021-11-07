package xyz.markapp.markmvvmtest1.ui.userlist

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.markapp.markmvvmtest1.CustomAdatper.Developer_CustomAdapter
import xyz.markapp.markmvvmtest1.R
import xyz.markapp.markmvvmtest1.databinding.FragmentUserlistBinding
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.ui.detail.DetailFragment
import xyz.markapp.markmvvmtest1.ui.detail.DetailViewModel
import java.util.*


class UserListFragment : Fragment() {

//    private lateinit var dashboardViewModel: DashboardViewModel
//    private var _binding: FragmentDashboardBinding? = null

    internal var fragmentUserlistBinding: FragmentUserlistBinding? = null
    internal var loadBar: ProgressBar? = null

    var developerViewModel: DeveloperViewModel? = null
    private var mDeveloper_CustomAdapter: Developer_CustomAdapter? = null


    //for next screen.
    var detailViewModel: DetailViewModel? = null

    private val h1 = object : Handler(Looper.getMainLooper()) {

        @SuppressLint("HandlerLeak")
        override fun handleMessage(msg: Message) {

//            when (msg?.what) {
            Log.d("debug", "get handler");
            getAllDev()
//            }
        }
    }


    // This property is only valid between onCreateView and
    // onDestroyView.
//    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false);

        ///init the View Model
        developerViewModel = ViewModelProvider(this).get(DeveloperViewModel::class.java)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        fragmentUserlistBinding = FragmentUserlistBinding.inflate(inflater, container, false)
        val root: View = fragmentUserlistBinding!!.root

        // bind RecyclerView
        val recyclerView = fragmentUserlistBinding?.viewdeveloper
        loadBar = fragmentUserlistBinding!!.loadBar
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setHasFixedSize(true)

        //init the Custom adataper
        mDeveloper_CustomAdapter = Developer_CustomAdapter()
        mDeveloper_CustomAdapter!!.setHandler(h1);

        //----
        mDeveloper_CustomAdapter!!.setItemClickListener(object : Developer_CustomAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int, currentDeveloper: DeveloperModel) {

                Log.d("debug", "onItemClick= $position");


//                detailViewModel?.setDeveloper(currentDeveloper);

                //---
                //goto next
                // Create new fragment and transaction
                val newFragment: Fragment = DetailFragment(currentDeveloper)
                val transaction: FragmentTransaction = activity?.getSupportFragmentManager()!!.beginTransaction()

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                transaction.replace(R.id.fragment_container, newFragment)
                transaction.addToBackStack(null)

                // Commit the transaction
                transaction.commit()


                //old
                /*   val intent = Intent(activity, DisplayUserDetailActivity::class.java)
                   val extras = Bundle().apply {
                       putInt("position", position)
                       putString("DeveloperModel", Gson().toJson(currentStudent))
                   }
                   intent.putExtras(extras)
                   startActivity(intent)*/

            }
        })

        //set the CustomAdapter
        recyclerView?.setAdapter(mDeveloper_CustomAdapter)

        getAllDev()

        return root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentUserlistBinding = null
//        _binding = null
    }

    //---
    private fun getAllDev() {
        ///get the list of dev from api response
        activity?.let {
            developerViewModel!!.getNextUsers().observe(
                it,
                Observer<List<Any>> { mDeveloperModel ->
                    ///if any thing chnage the update the UI
                    mDeveloper_CustomAdapter?.setDeveloperList(mDeveloperModel as ArrayList<DeveloperModel>)
                    loadBar?.visibility = View.GONE
                })
        }

    }
}
