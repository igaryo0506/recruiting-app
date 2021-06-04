package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null){
            var isPerson : Boolean? = false
            db.collection("users").document(currentUser.uid)
                    .get()
                    .addOnSuccessListener { document ->
                        isPerson = document.data?.get("isPerson")?.toString().equals("true")
                        if(isPerson!!){
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