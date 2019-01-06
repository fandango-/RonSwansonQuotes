package com.fandango.swanson.ron

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SwansonQuoteViewModel : ViewModel() {

    private val internalQuote = MutableLiveData<String>()
    private val quoteRepo = SwansonQuoteRepository()

    val quote: LiveData<String>
        get() = internalQuote

    fun init() {
        quoteRepo.getQuote({
            internalQuote.value = it
        }, {
            internalQuote.value = if (internalQuote.value != HATE_QUOTE) {
                HATE_QUOTE
            } else {
                LOVE_QUOTE
            }
        })
    }

    companion object {
        private const val HATE_QUOTE = "I hate everything."
        private const val LOVE_QUOTE = "I love nothing."
    }
}