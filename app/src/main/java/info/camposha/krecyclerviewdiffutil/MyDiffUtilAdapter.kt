package info.camposha.krecyclerviewdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.model.view.*

/**
 * This is the same RecyclerView but using DiffUtil instead of
 * notifyDataSetChanged() to animate changes
 */
class MyDiffUtilAdapter(var galaxies: List<String>):
         RecyclerView.Adapter<MyDiffUtilAdapter.MyViewHolder>() {

    var ItemClickListener: ((position: Int, name: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int):
     MyViewHolder = MyViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.model, parent, false))

    override fun getItemCount(): Int = galaxies.size

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bindView(galaxies[position], position)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(galaxy: String, position: Int) {
            itemView.nameTxt.text = galaxy
            itemView.setOnClickListener { ItemClickListener?.invoke(position, galaxy) }
        }
    }

    /**
     *  THIS IS THE ONLY DIFFERENCE BETWEEN the regular MyNotifyDataSetAdapter
     */
    fun updateList(newGalaxies: List<String>) {

        val diffCallback = MyDiffCallback(this.galaxies, newGalaxies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)

        this.galaxies = newGalaxies
    }
}
//end

