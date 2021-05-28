package app.igarashi.igaryo.recruitingapp
data class Post(
        var group:String = "",
        var title:String = "",
        var content:String = "",
        var year:Int = 0,
        var month: Int = 0,
        var date:Int = 0,
        var time:String = ""
)