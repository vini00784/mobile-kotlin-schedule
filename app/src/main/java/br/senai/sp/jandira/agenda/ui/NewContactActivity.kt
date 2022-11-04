package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.widget.Toast
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save()

            val openMainActivity = Intent(this, MainActivity::class.java)
            startActivity(openMainActivity)
        }
    }

    private fun save() {

        // Criar o objeto Contact
        val contact = Contact()
        contact.dataNascimento = binding.txtDataNascimento.text.toString()
        contact.email = binding.txtEmail.text.toString()
        contact.nome = binding.txtName.text.toString()
        contact.telefone = binding.txtPhone.text.toString()

        // Criar uma instância do repositório
        contactRepository = ContactRepository(this)
        val id = contactRepository.save(contact)

        Toast.makeText(this, "IF: $id", Toast.LENGTH_SHORT).show()

        finish()
    }
}