package com.fandango.swanson.ron

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SwansonQuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)
            .get(SwansonQuoteViewModel::class.java)
        viewModel.init()

        viewModel.quote.observe(this, Observer {
            swansonQuote.text = it
        })

        moreButton.setOnClickListener {
            viewModel.init()
        }
    }
}
