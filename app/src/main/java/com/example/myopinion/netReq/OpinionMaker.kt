package com.example.myopinion.netReq

import com.example.myopinion.databinding.ActivityWriteOpinionBinding
import com.example.myopinion.models.Opinion
import com.example.myopinion.tools.FormattedDate

class OpinionMaker(private val binding: ActivityWriteOpinionBinding,private val date:String) {


     fun getMadeOpinion (postId: String,type: String):Opinion{
         val title = binding.titleEditText.text.toString()
         val username = binding.authorNameEditText.text.toString()
         val shortDescriptionCompat =  binding.shortDescriptionEditText.text.toString()
         val exactTheme = binding.exactThemeEditText.text.toString()
         val body = binding.bodyEditText.text.toString()

         return Opinion(title,type,username,date,shortDescriptionCompat,exactTheme, body, postId)
     }
}