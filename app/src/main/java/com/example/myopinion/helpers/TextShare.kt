package com.example.myopinion.helpers

class TextShare(private val textShareProvider: TextShareProvider) : TextCopyService {
    override fun shareThisText(text: String) {
        textShareProvider.shareThisText(text)
    }
}