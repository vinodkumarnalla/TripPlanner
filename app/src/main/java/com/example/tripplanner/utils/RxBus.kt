package com.example.tripplanner.utils

import com.hwangjr.rxbus.Bus
import javax.inject.Inject

class RxBus @Inject constructor() {
    fun send(o: Any?) {
        sBus?.post(o)
    }

    fun register(obj: Any?) {
        sBus?.register(obj)
    }

    fun unRegister(obj: Any?) {
        sBus?.unregister(obj)
    }

    companion object {
        private var sBus: Bus? = Bus()
        @Synchronized
        fun get(): Bus? {
            if (sBus == null) {
                sBus = Bus()
            }
            return sBus
        }
    }
}