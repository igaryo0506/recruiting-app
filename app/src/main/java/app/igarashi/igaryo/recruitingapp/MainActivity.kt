package app.igarashi.igaryo.recruitingapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        circleButton.setOnClickListener {
            val toCircleActivityIntent = Intent(this,CircleActivity::class.java)
            startActivity(toCircleActivityIntent)
        }
        memberButton.setOnClickListener {
            val toMemberActivityIntent = Intent(this,MemberActivity::class.java)
            startActivity(toMemberActivityIntent)
        }
    }
}