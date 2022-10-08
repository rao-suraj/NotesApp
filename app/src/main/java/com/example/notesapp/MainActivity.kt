package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModal: NoteViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recycleView.adapter=adapter

        viewModal=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModal::class.java)
        viewModal.allNotes.observe(this, Observer {list->
            list?.let{
            adapter.updateList(it)
        }
        })
    }

    override fun onItemCLicked(notes: Note) {
        viewModal.deleteNote(notes)
    }

    fun insertNotes(view: View) {
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModal.insertNote(Note(noteText))
        }
    }
}