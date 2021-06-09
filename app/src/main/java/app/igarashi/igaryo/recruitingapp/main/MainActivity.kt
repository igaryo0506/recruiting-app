package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null){
            var isPerson: Boolean?
            db.collection("users").document(currentUser.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        isPerson = document.data?.get("isPerson")?.toString().equals("true")
                        if(!isPerson!!){
                            val toPersonActivityIntent = Intent(this,PersonActivity::class.java)
                            startActivity(toPersonActivityIntent)
                        }else{
                            val toGroupActivityIntent = Intent(this,GroupActivity::class.java)
                            startActivity(toGroupActivityIntent)
                        }
                    }
                    .addOnFailureListener{
                        //Log.d("xxx","failure")
                    }

        }else{
            val toLoginActivityIntent = Intent(this,LoginActivity::class.java)
            startActivity(toLoginActivityIntent)
        }
    }
}