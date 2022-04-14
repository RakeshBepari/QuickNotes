package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(),INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var inputTxt: TextView
    lateinit var submitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton=findViewById<Button>(R.id.addButton)
        inputTxt=findViewById<TextView>(R.id.input)
        val recView=findViewById<RecyclerView>(R.id.recView)


        recView.layoutManager=LinearLayoutManager(this)
        val adapter=NoteRVAdapter(this,this)
        recView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                                    .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateItems(it)
            }

        })



    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show()
    }

    fun AddToList(view: View) {
        val noteText= inputTxt.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText: Note Created", Toast.LENGTH_SHORT).show()
        }
        Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show()

    }
}