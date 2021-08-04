package com.example.myopinion.helpers

class TextFilter(private val textFilterProvider: TextFilterProvider) : TextFilterService {
    override fun filter(filterString: String) {
        textFilterProvider.filter(filterString)
    }
}