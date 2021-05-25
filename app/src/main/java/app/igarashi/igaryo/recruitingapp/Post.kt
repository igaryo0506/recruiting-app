package app.igarashi.igaryo.recruitingapp
data class Post(
        var name:String = "",
        var content:String = "",
        var year:Int = 0,
        var month: Int = 0,
        var date:Int = 0,
        var startHour:Int = 0,
        var startMinute:Int = 0,
        var endHour:Int = 0,
        var endMinute:Int = 0
)