package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_person.*

class GroupActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group)

        auth = Firebase.auth
        
        val adapter = GroupRecyclerViewAdapter(this)
        groupRecyclerView.layoutManager = LinearLayoutManager(this)
        groupRecyclerView.adapter = adapter

        db.collection("events")
            .orderBy(Post::time.name, Query.Direction.DESCENDING)
            .limit(100)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val postList = it.result!!.toObjects(Post::class.java)
                    adapter.addAll(postList)
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this, "投稿の取得に失敗しました", Toast.LENGTH_LONG).show()
                }
            }

        db.collection("users").document(auth.currentUser.uid)
            .get()
            .addOnSuccessListener {
                groupUserNameTextView.text = it.data?.get("name").toString()
            }

        plusButton.setOnClickListener {
            val toPostActivityIntent = Intent(this,PostActivity::class.java)
            startActivity(toPostActivityIntent)
        }

        groupSettingButton.setOnClickListener {
            val toSettingActivityIntent = Intent(this,SettingActivity::class.java)
            startActivity(toSettingActivityIntent)
        }
    }
}