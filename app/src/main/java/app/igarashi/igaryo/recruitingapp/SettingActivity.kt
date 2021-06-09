package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val auth = Firebase.auth

        logoutButton.setOnClickListener {
            auth.signOut()
            val toLoginActivityIntent = Intent(this,LoginActivity::class.java)
            startActivity(toLoginActivityIntent)
        }
    }
}