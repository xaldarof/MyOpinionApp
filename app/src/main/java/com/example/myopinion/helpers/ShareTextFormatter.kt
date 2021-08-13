package com.example.myopinion.helpers
interface ShareTextFormatterService{
    fun formatForShare(itle: String,body:String,date:String):String
}
class ShareTextFormatter() : ShareTextFormatterService{

    override fun formatForShare(title: String,body:String,date:String): String {
        return """
            $title
            --------------------
            $body
            --------------------
            $date
            Shared from application [MyOpinion]
            
        """.trimIndent()
    }
}