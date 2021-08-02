package com.example.mysampleproject

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mysampleproject.entity.BannerEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/com.example.lib_base.tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {




        @Test fun testSerialize(){
            System.out.println(JSON.stringify(Data.serializer(), Data(42)))
            System.out.println(JSON.indented.stringify(Data.serializer().list, listOf(Data(42))))
            System.out.print(obj)

            /*
            {"a":42,"b":"er","d":7.1}  // 默认格式
            [
                {
                    "a": 42,
                    "b": "er",
                    "d": 7.1
                }
            ]// 缩进格式
            Data(a=2, b=er, c=5, dd=3.4)
             */
        }
    }
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.mysampleproject", appContext.packageName)

}