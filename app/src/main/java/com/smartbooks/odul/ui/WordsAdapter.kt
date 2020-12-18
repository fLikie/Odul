package com.smartbooks.odul.ui

import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smartbooks.odul.R
import com.smartbooks.odul.data.Word

class WordsAdapter(private val data: List<Word>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    companion object {
        private const val TITLE_TYPE = 0
        private const val WORD_TYPE = 1
    }

    private var mediaPlayer = MediaPlayer()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TITLE_TYPE) {
            Title(LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false))
        } else {
            Words(LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TITLE_TYPE) {
            (holder as Title).title.text = data[position].title
        } else {
            (holder as Words).russianWord.text = data[position].russian
            holder.yukagirWord.text = data[position].yukagirski
            if (data[position].audio != null && data[position].audio.isNotEmpty()) {
                holder.audioIcon.visibility = View.VISIBLE
                holder.audioIcon.isClickable = true
                holder.audioIcon.setOnClickListener { playSound(holder, data[position].audio) }
            } else {
                holder.audioIcon.visibility = View.GONE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!data[position].title.isNullOrEmpty()) {
            TITLE_TYPE
        } else {
            WORD_TYPE
        }
    }

    private fun playSound(holder: Words, name: String) {
        val afd: AssetFileDescriptor = holder.itemView.context.assets.openFd(name)
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer()
        }
        mediaPlayer.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
        mediaPlayer.prepare()
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { it.reset() }

    }

    override fun getItemCount(): Int = data.size

    inner class Words(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val russianWord: TextView = itemView.findViewById(R.id.russian_word)
        val yukagirWord: TextView = itemView.findViewById(R.id.yukagir_word)
        val audioIcon: ImageView = itemView.findViewById(R.id.audio)

    }

    inner class Title(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_item)
    }
}