package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.adapter.ContactAdapter
import br.senai.sp.jandira.agenda.dao.ContactDao
import br.senai.sp.jandira.agenda.databinding.ActivityMainBinding
import br.senai.sp.jandira.agenda.repository.ContactRepository

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapterContacts: ContactAdapter
    lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbNewContact.setOnClickListener {
            val openNewContactActivity = Intent(this, NewContactActivity::class.java)
            startActivity(openNewContactActivity)
        }

    }

    override fun onResume() {
        super.onResume()
        loadRecyclerView()
    }

    private fun loadRecyclerView() {

        contactRepository = ContactRepository(this)

        val contacts = contactRepository.getAll()
        adapterContacts = ContactAdapter(contacts, this)

        var rvContacts = binding.rvContacts
        rvContacts.adapter = adapterContacts
        rvContacts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterContacts.updateContactsList(contactRepository.getAll())
    }
}