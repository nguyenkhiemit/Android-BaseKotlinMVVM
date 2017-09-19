package com.newgate.basekotlinmvvm.base.utility

import android.support.annotation.IntDef

/**
 * Created by apple on 9/11/17.
 */
class Constant {
    companion object {
        val BASE_URL = "https://test-services.vntrip.vn/vntrip/"

        val BASE_IMAGE_URL = "https://statics.vntrip.vn/data-v2/img_origin/"

        @IntDef(REQUEST_NONE,
                REQUEST_RUNNING,
                REQUEST_SUCCEEDED,
                REQUEST_FAILED)

        @Retention(AnnotationRetention.SOURCE)

        annotation class RequestState

        const val REQUEST_NONE = 0L
        const val REQUEST_RUNNING = 1L
        const val REQUEST_SUCCEEDED = 2L
        const val REQUEST_FAILED = 3L
    }

    @RequestState
    private var state: Long = 0L

    fun setRequestState(@RequestState state: Long) {
        this.state = state
    }
}

class KeyCode {
    companion object {
        const val SUCCESS = "success"
    }
}