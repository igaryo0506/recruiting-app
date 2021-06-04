package app.igarashi.igaryo.recruitingapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<PersonRecyclerViewAdapter.ViewHolder>() {
    private val items:MutableList<Post> = mutableListOf()
    var arr = arrayOf<String>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        val timeTextView:TextView = view.findViewById(R.id.timeTextView)
        val contentTextView:TextView = view.findViewById(R.id.contentTextView)
        val joinButton: Button = view.findViewById(R.id.joinButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_person_events_data_cell,parent,false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleTextView.text = item.title
        holder.dayTextView.text = "${item.month}/${item.date}"
        holder.timeTextView.text = item.time
        holder.contentTextView.text = item.content
        holder.joinButton.setOnClickListener {
            holder.joinButton.text = "参加登録が完了しました"
            val pa = PersonActivity()
            pa.joinEvent(arr[position])
        }
    }

    fun addAll(items:List<Post>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}