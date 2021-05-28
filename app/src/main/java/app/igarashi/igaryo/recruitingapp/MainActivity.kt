package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val intent = Intent(this,GroupActivity::class.java)
        startActivity(intent)
//        if(currentUser != null){
//            Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
//        }else{
//            val toLoginActivityIntent = Intent(this,LoginActivity::class.java)
//            startActivity(toLoginActivityIntent)
//        }
    }
}