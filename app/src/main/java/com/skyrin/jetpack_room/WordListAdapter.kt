package com.skyrin.jetpack_room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordListAdapter internal constructor(context: Context): RecyclerView.Adapter<WordListAdapter.WordViewHolder>(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>() // Cached copy of words

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return words.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.wordItemView.text = current.word

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener { v ->
                val cp = holder.layoutPosition
                itemClickListener!!.onItemClick(v, cp, words[cp])
            }
            holder.itemView.setOnLongClickListener { v ->
                val lcp = holder.layoutPosition
                itemClickListener!!.onItemLongClick(v, lcp, words[lcp])
                false
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(view: View, position: Int, word: Word)

        fun onItemLongClick(view: View, position: Int, word: Word)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}