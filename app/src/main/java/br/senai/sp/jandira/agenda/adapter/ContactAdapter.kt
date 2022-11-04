package br.senai.sp.jandira.agenda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.senai.sp.jandira.agenda.R
import br.senai.sp.jandira.agenda.model.Contact

class ContactAdapter(var contactsList: List<Contact>, var context: Context) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    fun updateContactsList(newContactList: List<Contact>) {
        this.contactsList = newContactList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout, parent, false) // O false é no caso de querer ou não mudar o layout

        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {

        val contact = contactsList[position]

        holder.contactName.text = contact.nome
        holder.contactPhone.text = contact.telefone
        holder.contactEmail.text = contact.email
        holder.contactNameInitial.text = contact.nome.substring(0, 1)

        holder.cardViewContact.setOnClickListener {
            Toast.makeText(context, "Código: ${contact.id}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {

        val contactName: TextView = view.findViewById(R.id.contact_name)
        val contactPhone: TextView = view.findViewById(R.id.contact_phone)
        val contactEmail: TextView = view.findViewById(R.id.contact_email)
        val contactNameInitial: TextView = view.findViewById(R.id.contact_name_initial)
        val cardViewContact: CardView = view.findViewById(R.id.card_view_contact)

    }

}