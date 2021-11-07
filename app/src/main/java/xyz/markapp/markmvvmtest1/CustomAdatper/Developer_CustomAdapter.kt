package xyz.markapp.markmvvmtest1.CustomAdatper


import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import xyz.markapp.markmvvmtest1.R
import xyz.markapp.markmvvmtest1.databinding.RowListItemBinding
import xyz.markapp.markmvvmtest1.model.DeveloperModel
import xyz.markapp.markmvvmtest1.repository.DeveloperRepository
import java.util.*

class Developer_CustomAdapter : RecyclerView.Adapter<Developer_CustomAdapter.DeveloperViewHolder>() {

    private var mDeveloperModel: ArrayList<DeveloperModel>? = null
    private var h1: Handler? = null


    fun setHandler(h1: Handler) {
        this.h1 = h1
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): DeveloperViewHolder {
        val mDeveloperListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )

        return DeveloperViewHolder(mDeveloperListItemBinding)
    }


    override fun onBindViewHolder(mDeveloperViewHolder: DeveloperViewHolder, i: Int) {


        if (i > 0 && (i % 50 == 0) && (i >= (mDeveloperModel!!.size - 50))) {
            Log.d("111", "getNextUser.");
            h1?.sendMessage(Message());
//            return
        }

        val currentDeveloper = mDeveloperModel!![i]
        mDeveloperViewHolder.mDeveloperListItemBinding.developerModel = currentDeveloper


        mDeveloperViewHolder.itemView.setOnClickListener {
            itemClickListener.onItemClick(it, i, currentDeveloper)
        }

    }


    override fun getItemCount(): Int {
        return if (mDeveloperModel != null) {
            DeveloperRepository.MAX_SIZE
//            mDeveloperModel!!.size
        } else {
            0
        }
    }


    fun setDeveloperList(mDeveloperModel: ArrayList<DeveloperModel>) {
        this.mDeveloperModel = mDeveloperModel
        notifyDataSetChanged()
    }


    inner class DeveloperViewHolder(var mDeveloperListItemBinding: RowListItemBinding) :
        RecyclerView.ViewHolder(mDeveloperListItemBinding.root)


    //---
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int, currentDeveloper: DeveloperModel)
    }

    private lateinit var itemClickListener: OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}
