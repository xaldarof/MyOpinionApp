package com.opinion.myopinion.netReq

import com.opinion.myopinion.models.Report
import com.google.firebase.firestore.FirebaseFirestore

class ReportServiceProvider(private var fireStore: FirebaseFirestore) : ReportService {
    override fun report(report: Report) {
        fireStore = FirebaseFirestore.getInstance()

        fireStore.collection("reports").add(report)

    }
}