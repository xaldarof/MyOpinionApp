package com.example.myopinion.netReq

import android.app.Activity
import android.content.Context
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

class OpinionDataRequestProvider(private val binding: ActivityWriteOpinionBinding,
                                 private val type:String,private val activity: Activity,
                                 private val context: Context,realm: Realm): OpinionsRequestService{

    private lateinit var reference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    private val formattedDate = FormattedDate.formatted()
    private val opinionDataSource = OpinionDataSource(OpinionCacheDataSource(realm))
    private val inputValidator = InputValidator(binding,type)
    private val opinionMaker = OpinionMaker(binding,formattedDate)

    override fun sendData() {
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference(KeyWords.FIREBAE_PATH)
        firebaseAuth = FirebaseAuth.getInstance()
        val push = reference.push().key

        binding.apply {
          publishDataIfValid(push.toString(),opinionMaker.getMadeOpinion(push.toString(),type))
        }
    }
    private fun publishDataIfValid(push:String,opinion: Opinion){
        if (inputValidator.isValidUserInput()) {
            SuccessDialogShower().show(context,activity)
            reference.child(push).setValue(opinion)

            saveThisOpinionToDataBase(opinion)

        } else {
            TopSnackBarShower.show(binding.constraintLayout, activity, activity.applicationContext.resources.getString(
                R.string.inValidInput))
        }
    }
    private fun saveThisOpinionToDataBase(opinion: Opinion) {
        val opinionEntity = OpinionEntity()
        val opinionEntityMaker = OpinionEntityMaker(OpinionEntityProvider(opinion,opinionEntity))
        opinionDataSource.saveOpinion(opinionEntityMaker.opinionToOpinionEntity())
    }
}