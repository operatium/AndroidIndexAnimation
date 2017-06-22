package com.demo1

import android.app.Activity
import android.os.Bundle

import butterknife.ButterKnife
import butterknife.InjectView
import butterknife.OnClick

/**
 * Created by java on 2017/6/20.
 */

class LoadingActivity : Activity() {
    @InjectView(R.id.tv)
    internal var tv: TitanicTextView? = null

    private var m_titanic: Titanic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        ButterKnife.inject(this)

        // set fancy typeface
        tv!!.typeface = Typefaces.get(this, "Satisfy-Regular.ttf")

        // start animation
        m_titanic = Titanic()
        m_titanic!!.start(tv)
    }

    @OnClick(R.id.tv)
    fun onViewClicked() {
        m_titanic!!.cancel()
    }
}
