package com.opinion.myopinion.netReq

import com.opinion.myopinion.models.Report

class Reporter(private val reportServiceProvider: ReportServiceProvider) : ReportService {
    override fun report(report: Report) {
        reportServiceProvider.report(report)
    }
}