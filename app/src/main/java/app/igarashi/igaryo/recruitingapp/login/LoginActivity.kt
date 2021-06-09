package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

val TAG = "hello"
class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        var isSignUp = true
        var isPerson = true
        var email = ""
        var name = ""
        var password = "default"
        loginSubmitButton.setBackgroundColor(Color.BLUE)

        signInButton.setTextColor(Color.WHITE)
        signUpButton.setTextColor(Color.WHITE)
        signUpButton.setBackgroundColor(Color.BLUE)
        signInButton.setBackgroundColor(Color.GRAY)
        signUpButton.isEnabled = false

        groupButton.setTextColor(Color.WHITE)
        personButton.setTextColor(Color.WHITE)
        personButton.setBackgroundColor(Color.BLUE)
        groupButton.setBackgroundColor(Color.GRAY)
        personButton.isEnabled = false

        loginSubmitButton.setOnClickListener {
            email = emailEditText.text.toString()
            name = userNameEditText.text.toString()
            password = passwordEditText.text.toString()
            if(email == ""  || password == ""){
                Toast.makeText(this, "記入されてないものがあるよ", Toast.LENGTH_SHORT).show()
            }else{
                if(isSignUp){
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser
                                db.collection("users").document(user.uid)
                                        .set(hashMapOf("isPerson" to isPerson,"name" to name))
                                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                                if(isPerson){
                                    val toPersonActivityIntent = Intent(this,PersonActivity::class.java)
                                    startActivity(toPersonActivityIntent)
                                }else{
                                    val toGroupActivityIntent = Intent(this,GroupActivity::class.java)
                                    startActivity(toGroupActivityIntent)
                                }
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                                Toast.makeText(this, "ログインに失敗したよ", Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this){ task ->
                            if (task.isSuccessful) {
                                val user = auth.currentUser
                                db.collection("users").document(user.uid)
                                    .set(hashMapOf("isPerson" to isPerson,"name" to name))
                                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                                db.collection("users").document(user.uid)
                                    .get()
                                    .addOnSuccessListener { document ->
                                        isPerson = document.data?.get("isPerson")?.toString().equals("true")
                                        if(!isPerson!!){
                                            val toPersonActivityIntent = Intent(this,PersonActivity::class.java)
                                            toPersonActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
                                            startActivity(toPersonActivityIntent)
                                        }else{
                                            val toGroupActivityIntent = Intent(this,GroupActivity::class.java)
                                            toGroupActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
                                            startActivity(toGroupActivityIntent)
                                        }
                                    }
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }

        signInButton.setOnClickListener {
            personButton.setBackgroundColor(Color.GRAY)
            groupButton.setBackgroundColor(Color.GRAY)
            signUpButton.isEnabled = true
            signInButton.isEnabled = false
            signUpButton.setBackgroundColor(Color.GRAY)
            signInButton.setBackgroundColor(Color.BLUE)
            personButton.isEnabled=false
            groupButton.isEnabled=false
            isSignUp = false
            loginSubmitButton.text = "ログイン"
            userNameLabelText.isVisible = false
            userNameEditText.isVisible = false
        }
        signUpButton.setOnClickListener {
            if(isPerson){
                personButton.setBackgroundColor(Color.BLUE)
                groupButton.setBackgroundColor(Color.GRAY)
            }else{
                personButton.setBackgroundColor(Color.GRAY)
                groupButton.setBackgroundColor(Color.BLUE)
            }
            signUpButton.isEnabled = false
            signInButton.isEnabled = true
            signUpButton.setBackgroundColor(Color.BLUE)
            signInButton.setBackgroundColor(Color.GRAY)
            personButton.isEnabled=true
            groupButton.isEnabled=true
            isSignUp = true
            loginSubmitButton.text = "登録"
            userNameLabelText.isVisible = true
            userNameEditText.isVisible = true
        }
        groupButton.setOnClickListener {
            groupButton.isEnabled = false
            personButton.isEnabled = true
            groupButton.setBackgroundColor(Color.BLUE)
            personButton.setBackgroundColor(Color.GRAY)
            isPerson = false
        }
        personButton.setOnClickListener {
            groupButton.isEnabled = true
            personButton.isEnabled = false
            groupButton.setBackgroundColor(Color.GRAY)
            personButton.setBackgroundColor(Color.BLUE)
            isPerson = true
        }
    }
}