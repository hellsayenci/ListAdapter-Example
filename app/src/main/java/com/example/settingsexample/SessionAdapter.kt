package com.example.settingsexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SessionAdapter :
    ListAdapter<BaseSessionData, SessionAdapter.BaseHolder>(SessionDiffCallback) {

    open inner class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class HeaderViewHolder(itemView: View) :
        BaseHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(header: SessionHeader) {
            textView.text = header.text
        }
    }

    inner class SessionViewHolder(itemView: View) :
        BaseHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(header: Session) {
            textView.text = header.text
        }
    }

    inner class FooterViewHolder(itemView: View) :
        BaseHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.text)

        fun bind(header: SessionFooter) {
            textView.text = header.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return when (viewType) {
            ViewTypes.HEADER.ordinal -> {
                HeaderViewHolder(view)
            }
            ViewTypes.ITEM.ordinal -> {
                SessionViewHolder(view)
            }
            ViewTypes.FOOTER.ordinal -> {
                FooterViewHolder(view)
            }
            else -> FooterViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SessionHeader -> ViewTypes.HEADER.ordinal
            is Session -> ViewTypes.ITEM.ordinal
            is SessionFooter -> ViewTypes.FOOTER.ordinal
            else -> ViewTypes.FOOTER.ordinal
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        when (val item = getItem(position)) {
            is SessionHeader -> {
                (holder as HeaderViewHolder).bind(item)
            }
            is Session -> {
                (holder as SessionViewHolder).bind(item)
            }
            is SessionFooter -> {
                (holder as FooterViewHolder).bind(item)
            }
        }
    }
}

object SessionDiffCallback : DiffUtil.ItemCallback<BaseSessionData>() {
    override fun areItemsTheSame(oldItem: BaseSessionData, newItem: BaseSessionData): Boolean {
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItem: BaseSessionData, newItem: BaseSessionData): Boolean {
        return if (oldItem is SessionHeader && newItem is SessionHeader) {
            oldItem.text == newItem.text
        } else if (oldItem is Session && newItem is Session) {
            oldItem.text == newItem.text
        } else if (oldItem is SessionFooter && newItem is SessionFooter) {
            oldItem.text == newItem.text
        } else {
            false
        }
    }
}

enum class ViewTypes(i: Int) {
    HEADER(0),
    ITEM(1),
    FOOTER(2)
}
