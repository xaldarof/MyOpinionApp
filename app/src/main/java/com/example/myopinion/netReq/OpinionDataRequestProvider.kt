package com.example.myopinion.netReq

import android.app.Activity
import android.content.Context
import android.widget.EditText
import com.example.myopinion.R
import com.example.myopinion.databinding.ActivityWriteOpinionBinding
import com.example.myopinion.helpers.InputValidator
import com.example.myopinion.models.Opinion
import com.example.myopinion.repository.OpinionCacheDataSource
import com.example.myopinion.repository.OpinionDataSource
import com.example.myopinion.repository.entity.OpinionEntity
import com.example.myopinion.tools.FormattedDate
import com.example.myopinion.tools.SuccessDialogShower
import com.example.myopinion.tools.TopSnackBarShower
import com.example.myopinion.utils.KeyWords
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.realm.Realm
import io.realm.RealmResults
import java.util.*

class OpinionDataRequestProvider(private val binding: ActivityWriteOpinionBinding,private val type:String,private val activity: Activity,private val context: Context,realm: Realm): OpinionsRequestService{

    private lateinit var reference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private val formattedDate = FormattedDate.formatted()
    private val opinionDataSource = OpinionDataSource(OpinionCacheDataSource(realm))

    override fun sendData() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference(KeyWords.FIREBAE_PATH)
        firebaseAuth = FirebaseAuth.getInstance()
        val push = reference.push().key

        val title = binding.titleEditText.text.toString()
        val username = binding.authorNameEditText.text.toString()
        val shortDescriptionCompat =  binding.shortDescriptionEditText.text.toString()
        val exactTheme = binding.exactThemeEditText.text.toString()
        val body = binding.bodyEditText.text.toString()
        val postId = push.toString()
        val opinion = Opinion(title,type,username,formattedDate,shortDescriptionCompat,exactTheme,body,postId)

        binding.apply {
            val isValid = InputValidator.isValid(binding.titleEditText,type, authorNameEditText,
                shortDescriptionEditText,exactThemeEditText,bodyEditText)
            if (isValid) {
                SuccessDialogShower().show(context,activity)
                reference.child(push.toString()).setValue(opinion)

                val opinionEntity = OpinionEntity()
                opinionDataSource.saveOpinion(makeOpinionEntity(opinion,opinionEntity))

            } else {
                TopSnackBarShower.show(binding.constraintLayout, activity, activity.applicationContext.resources.getString(
                    R.string.inValidInput))
            }
        }
    }
   private fun makeOpinionEntity(opinion: Opinion,opinionEntity:OpinionEntity):OpinionEntity{
        opinionEntity.title = opinion.title
        opinionEntity.shortDescription = opinion.shortDescription
        opinionEntity.date = opinion.date
        opinionEntity.exactTheme = opinion.exactTheme
        opinionEntity.postId = opinion.postId
        opinionEntity.username = opinion.username
        opinionEntity.body = opinion.body
        opinionEntity.type = opinion.type

       return opinionEntity
    }
}