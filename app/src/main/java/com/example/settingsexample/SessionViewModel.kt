package com.example.settingsexample

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SessionViewModel : ViewModel() {

    val sessions: MutableLiveData<List<BaseSessionData>> = MutableLiveData()

    init {
        sessions.value = createInitialList()

        withDelay(3000) {
            val list = sessions.value?.toMutableList() ?: mutableListOf()
            list.removeAt(2)
            sessions.value = list
        }

        withDelay(6000) {
            val list = sessions.value?.toMutableList() ?: mutableListOf()
            list.add(3, Session(ItemTypes.ITEM.ordinal, "item5"))
            list.add(4, Session(ItemTypes.ITEM.ordinal, "item4"))
            sessions.value = list
        }

        withDelay(9000) {
            val list = sessions.value?.toMutableList() ?: mutableListOf()
            list[0] = Session(ItemTypes.HEADER.ordinal, "header changed")
            list[2] = Session(ItemTypes.ITEM.ordinal, "item2")
            list[3] = Session(ItemTypes.ITEM.ordinal, "item3")
            list[4] = Session(ItemTypes.ITEM.ordinal, "item4")
            list[5] = Session(ItemTypes.FOOTER.ordinal, "footer changed")
            sessions.value = list
        }
    }


    private fun createInitialList(): MutableList<BaseSessionData> {
        return mutableListOf<BaseSessionData>().apply {
            add(SessionHeader(ItemTypes.HEADER.ordinal, "header"))
            add(Session(ItemTypes.ITEM.ordinal, "item1"))
            add(Session(ItemTypes.ITEM.ordinal, "item2"))
            add(Session(ItemTypes.ITEM.ordinal, "item3"))
            add(SessionFooter(ItemTypes.FOOTER.ordinal, "footer"))
        }
    }

    private fun withDelay(delay : Long, block : () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
    }

    enum class ItemTypes(i: Int) {
        HEADER(0),
        ITEM(1),
        FOOTER(2)
    }
}