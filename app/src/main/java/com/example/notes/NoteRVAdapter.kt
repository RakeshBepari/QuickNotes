package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text

class NoteRVAdapter(private val context: Context, private val listner: INotesRVAdapter): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteViewHolder=NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        noteViewHolder.deleteButton.setOnClickListener {
            listner.onItemClicked(allNotes[noteViewHolder.adapterPosition])
        }
        return noteViewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=allNotes[position]
        holder.text.text=currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateItems(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.text)
        val deleteButton=itemView.findViewById<ImageView>(R.id.deleteButton)


    }
}
interface INotesRVAdapter{
    fun onItemClicked(note:Note)
}
