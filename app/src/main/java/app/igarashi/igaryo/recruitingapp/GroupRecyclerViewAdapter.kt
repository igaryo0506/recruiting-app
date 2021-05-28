package app.igarashi.igaryo.recruitingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<GroupRecyclerViewAdapter.ViewHolder>() {
    private val items:MutableList<Post> = mutableListOf()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        val timeTextView:TextView = view.findViewById(R.id.timeTextView)
        val contentTextView:TextView = view.findViewById(R.id.contentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_group_events_data_cell,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        holder.dayTextView.text = "${item.month}/${item.date}"
        holder.timeTextView.text = item.time
        holder.contentTextView.text = item.content
    }

    fun addAll(items:List<Post>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}