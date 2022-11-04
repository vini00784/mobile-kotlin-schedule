package br.senai.sp.jandira.agenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.view.View
import android.widget.Toast
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.databinding.ActivityNewContactBinding
import br.senai.sp.jandira.agenda.model.Contact
import br.senai.sp.jandira.agenda.repository.ContactRepository
import java.time.LocalDate

class NewContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityNewContactBinding
    lateinit var contactRepository: ContactRepository
    lateinit var contact: Contact
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save()
        }



        id = intent.getIntExtra("id", 0)

        if (id > 0) {
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonSave.text = "Atualizar"
            loadContact()
        }
    }

    private fun loadContact() {
        contactRepository = ContactRepository(this)
        contact = contactRepository.getContactById(id)

        binding.textViewName.text = "Nome:"
        binding.txtName.setText(contact.nome)

        binding.textViewEmail.text = "Email:"
        binding.txtEmail.setText(contact.email)

        binding.textViewPhone.text = "Telefone:"
        binding.txtPhone.setText(contact.telefone)

        binding.textViewBirthDate.text = "Data de Nascimento:"
//        binding.txtDataNascimento.setText(contact.dataNascimento)
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