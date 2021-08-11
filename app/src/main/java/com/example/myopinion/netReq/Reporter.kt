package com.example.myopinion.netReq

import com.example.myopinion.models.Report

class Reporter(private val reportServiceProvider: ReportServiceProvider) : ReportService {
    override fun report(report: Report) {
        reportServiceProvider.report(report)
    }
}