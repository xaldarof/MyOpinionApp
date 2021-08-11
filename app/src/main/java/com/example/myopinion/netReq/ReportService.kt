package com.example.myopinion.netReq

import com.example.myopinion.models.Report

interface ReportService {
    fun report(report: Report)
}