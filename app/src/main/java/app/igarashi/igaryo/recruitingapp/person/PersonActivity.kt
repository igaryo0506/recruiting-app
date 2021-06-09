package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    var arr = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        val adapter = PersonRecyclerViewAdapter(this)
        personRecyclerView.layoutManager = LinearLayoutManager(this)
        personRecyclerView.adapter = adapter
        db.collection("events")
                .get()
                .addOnSuccessListener { results ->
                    for(document in results){
                        arr += document.id
                    }
                    adapter.arr = arr
                    val postList = results.toObjects(Post::class.java)
                    adapter.addAll(postList)
                    adapter.notifyDataSetChanged()
                }
        personSettingButton.setOnClickListener {
            val toSettingActivityIntent = Intent(this,SettingActivity::class.java)
            startActivity(toSettingActivityIntent)
        }
    }
    fun joinEvent(id:String){
        auth = Firebase.auth
        db.collection("events").document(id)
                .get()
                .addOnSuccessListener { it ->
                    var post:Post = it.toObject(Post::class.java)!!
                    var newParticipants:MutableList<String> = post?.participants
                    newParticipants.add(auth.currentUser.uid!!)
                    Log.d("xxx","size:${newParticipants.size}")
                    db.collection("events").document(id).update("participants",newParticipants)
                }
        Log.d("xxx","${id}に${auth.currentUser.uid}でjoinできたね")
    }

}