@file:Suppress("unused")

package cn.wj.android.base.thread

import android.os.Handler
import android.os.Looper

/**
 * 主线程管理器
 * > 在任意地方将代码 post 到主线程执行
 *
 * ```
 * MainThreadManager.postToMainThread({
 *      // do something
 * })
 * ```
 */
object MainThreadManager {

    /** 主线程 Handler */
    @Volatile
    private var mMainHandler: Handler? = null

    /**
     * 将事件[runnable]延时[delay]ms之后在主线程执行
     * > [delay] 默认 `0L`
     */
    @JvmStatic
    fun postToMainThread(runnable: Runnable, delay: Long = 0L) {
        if (mMainHandler == null) {
            synchronized(MainThreadManager::class.java) {
                if (mMainHandler == null) {
                    mMainHandler = Handler(Looper.getMainLooper())
                }
            }
        }
        mMainHandler?.postDelayed(runnable, delay)
    }
}