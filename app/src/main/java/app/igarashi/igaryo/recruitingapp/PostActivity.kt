package app.igarashi.igaryo.recruitingapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_post.*
import java.util.*


class PostActivity : AppCompatActivity() {
    var group = "default"
    var title:String = ""
    var content:String = ""
    var year:Int = -1
    var month: Int = -1
    var date:Int = -1
    var startTime = ""
    var endTime = ""
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        clearAll()
        submitButton.setOnClickListener {
            title = circleNameEditText.text.toString();
            content = eventContentEditText.text.toString();
            if(checkNull()){
                clearAll()
                val time = "$startTime〜$endTime"
                val post = Post(group,title,content,year,month,date,time)
                db.collection("events")
                    .add(post)
                    .addOnSuccessListener {
                        Toast.makeText(this, "投稿しました", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener{
                        Toast.makeText(this, "投稿に失敗しました", Toast.LENGTH_LONG).show()
                    }
            }
        }
        dateEditText.setOnClickListener{
            val c = Calendar.getInstance()
            val nowYear = c.get(Calendar.YEAR)
            val nowMonth = c.get(Calendar.MONTH)
            val nowDate = c.get(Calendar.DATE)
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener() {_, year, month, dayOfMonth->
                    this.year = year
                    this.month = month
                    this.date = dayOfMonth
                    dateEditText.setText("${year}/${month + 1}/${dayOfMonth}")
                },
                nowYear,
                nowMonth,
                nowDate)
            datePickerDialog.show()
        }
        startTimeEditText.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener(){_,hourOfDay,minute ->
                    if(minute < 10){
                        startTime = "$hourOfDay:0$minute"
                    }else{
                        startTime = "$hourOfDay:$minute"
                    }
                    startTimeEditText.setText(startTime)
                },
                0,0,true)
            timePickerDialog.show()
        }
        endTimeEditText.setOnClickListener{
            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener(){_,hourOfDay,minute ->
                    if(minute < 10){
                        endTime = "$hourOfDay:0$minute"
                    }else{
                        endTime = "$hourOfDay:$minute"
                    }
                    startTimeEditText.setText(endTime)
                },
                0,0,true)
            timePickerDialog.show()
        }
    }
    private fun checkNull() : Boolean{
        if(title == ""){
            Toast.makeText(this, "タイトルが指定されてないよ", Toast.LENGTH_SHORT).show()
            circleNameEditText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            return false
        }else{
            circleNameEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        }
        if(content == ""){
            Toast.makeText(this, "イベント内容が指定されてないよ", Toast.LENGTH_SHORT).show()
            eventContentEditText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            return false
        }else{
            eventContentEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        }
        if(year==-1) {
            Toast.makeText(this, "日付が指定されてないよ", Toast.LENGTH_SHORT).show()
            dateEditText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            return false
        }else{
            dateEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        }
        if(startTime == "" || endTime == "") {
            Toast.makeText(this, "時刻が指定されてないよ", Toast.LENGTH_SHORT).show()
            startTimeEditText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            endTimeEditText.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
            return false
        }else{
            startTimeEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
            endTimeEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        }
        return true
    }
    private fun clearAll(){
        circleNameEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        eventContentEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        dateEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        startTimeEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        endTimeEditText.background.setColorFilter(Color.parseColor("#808080"),PorterDuff.Mode.SRC_IN)
        circleNameEditText.setText("")
        eventContentEditText.setText("")
        dateEditText.setText("")
        startTimeEditText.setText("")
        endTimeEditText.setText("")
    }
}