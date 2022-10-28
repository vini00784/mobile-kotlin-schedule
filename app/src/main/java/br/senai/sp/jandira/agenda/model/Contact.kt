package br.senai.sp.jandira.agenda.model

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "tbl_contact") // Isso é uma anotação que permite falar para o ROOM que essa classe é dele e ele tem permissão para criar uma tabela com exatamente esses mesmos atributos.
class Contact {

    @PrimaryKey(autoGenerate = true) // Isso vem sempre acima ou à esquerda do ID!
    var id = 0

    var nome = ""
    var telefone = ""
    var email = ""

    @ColumnInfo(name = "data_nascimento") // Define como esse atributo vai ser nomeado dentro do banco
    var dataNascimento: LocalDate? = null

    var foto: Drawable? = null

}