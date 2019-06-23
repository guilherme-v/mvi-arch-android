package br.curitiba.android.mviarch

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun itLaunches() {
        activity.launchActivity(null)
    }
}