package app.igarashi.igaryo.recruitingapp

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_group.*

class GroupActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)
        
        val adapter = GroupRecyclerViewAdapter(this)
        groupRecyclerView.layoutManager = LinearLayoutManager(this)
        groupRecyclerView.adapter = adapter

        Toast.makeText(this, "wow", Toast.LENGTH_SHORT).show()

        db.collection("events")
            .orderBy(Post::time.name, Query.Direction.DESCENDING)
            .limit(100)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "wowow", Toast.LENGTH_SHORT).show()
                    val postList = it.result!!.toObjects(Post::class.java)
                    adapter.addAll(postList)
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this, "投稿の取得に失敗しました", Toast.LENGTH_LONG).show()
                }
            }

        plusButton.setOnClickListener {
            val toPostActivityIntent = Intent(this,PostActivity::class.java)
            startActivity(toPostActivityIntent)
        }
    }
}