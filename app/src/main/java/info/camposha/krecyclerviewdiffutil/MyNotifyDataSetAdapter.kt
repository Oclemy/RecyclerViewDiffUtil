package info.camposha.krecyclerviewdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.model.view.*

/**
 * This class will generate the recycled views and load data when they come into screen using
 * view holder pattern. Updates from the data source to the recyclerview will occur through
 * notifyDatasetChanged
 */
class MyNotifyDataSetAdapter(var stars: List<String>):
         RecyclerView.Adapter<MyNotifyDataSetAdapter.MyViewHolder>() {

    var ItemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.model, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        // Size of items to load
        return stars.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bindView(stars[position], position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(star: String, position: Int) {
            itemView.nameTxt.text = star

            itemView.setOnClickListener {
                ItemClickListener?.invoke(position, star)
            }
        }
    }
    fun updateList(newStars: List<String>) {
        this.stars = newStars

        // Call this when you change the data of Recycler View to refresh the items
        notifyDataSetChanged()
    }
}
