package com.opinion.myopinion.netReq

import com.opinion.myopinion.models.Report

interface ReportService {
    fun report(report: Report)
}