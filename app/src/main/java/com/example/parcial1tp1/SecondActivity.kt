package com.example.parcial1tp13
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial1tp1.R


class SecondActivity : AppCompatActivity() {



    private lateinit var buttonVolver: Button
    private lateinit var spinnerPaises: Spinner
    private lateinit var listView: ListView

    private var paisSeleccionado: String? = null

    var deportistasFiltrados = mutableListOf<Deportista>()
    var nombreDeportistas = mutableListOf<String>()


    val listaDeportistas = listOf(
        // Argentina
        Deportista(1, "Lionel Messi", Deporte.FUTBOL, Pais.ARGENTINA, true),
        Deportista(2, "Diego Maradona", Deporte.FUTBOL, Pais.ARGENTINA, false),
        Deportista(3, "Manu Ginobili", Deporte.BASQUETBOL, Pais.ARGENTINA, false),
        Deportista(4, "Gabriela Sabatini", Deporte.TENIS, Pais.ARGENTINA, true),
        Deportista(5, "Luciana Aymar", Deporte.HOCKEY, Pais.ARGENTINA, false),
        Deportista(6, "Nicolás Massú", Deporte.TENIS, Pais.ARGENTINA, true),
        Deportista(7, "Juan Martín Del Potro", Deporte.TENIS, Pais.ARGENTINA, true),
        Deportista(8, "Paola Egoavil", Deporte.BASQUETBOL, Pais.ARGENTINA, true),
        Deportista(9, "Juan Martín Cúneo", Deporte.HOCKEY, Pais.ARGENTINA, false),
        Deportista(10, "Guido Pella", Deporte.TENIS, Pais.ARGENTINA, false),

        // Uruguay
        Deportista(11, "Luis Suárez", Deporte.FUTBOL, Pais.URUGUAY, true),
        Deportista(12, "Edinson Cavani", Deporte.FUTBOL, Pais.URUGUAY, true),
        Deportista(13, "José Pedro Furman", Deporte.FUTBOL, Pais.URUGUAY, false),
        Deportista(14, "Natalia Falcón", Deporte.FUTBOL, Pais.URUGUAY, false),
        Deportista(15, "Enzo Francescoli", Deporte.FUTBOL, Pais.URUGUAY, true),
        Deportista(16, "Larisa Collazo", Deporte.JUDO, Pais.URUGUAY, true),
        Deportista(17, "Santiago García", Deporte.FUTBOL, Pais.URUGUAY, false),
        Deportista(18, "Débora Rodríguez", Deporte.BASQUETBOL, Pais.URUGUAY, true),
        Deportista(19, "Marcela Cerviño", Deporte.TENIS, Pais.URUGUAY, false),
        Deportista(20, "Juan Manuel Silva", Deporte.CICLISMO, Pais.URUGUAY, true),

        // Colombia
        Deportista(21, "Radamel Falcao García", Deporte.FUTBOL, Pais.COLOMBIA, false),
        Deportista(22, "Mariana Pajón", Deporte.BMX, Pais.COLOMBIA, true),
        Deportista(23, "Nairo Quintana", Deporte.CICLISMO, Pais.COLOMBIA, true),
        Deportista(24, "Caterine Ibargüen", Deporte.ATLETISMO, Pais.COLOMBIA, true),
        Deportista(25, "Rigoberto Urán", Deporte.CICLISMO, Pais.COLOMBIA, false),
        Deportista(26, "María Camila Osorio Serrano", Deporte.TENIS, Pais.COLOMBIA, false),

        // Chile
        Deportista(27, "Alexis Sánchez", Deporte.FUTBOL, Pais.CHILE, false),
        Deportista(28, "Marcelo Ríos", Deporte.TENIS, Pais.CHILE, true),
        Deportista(29, "Fernando González", Deporte.TENIS, Pais.CHILE, true),
        Deportista(30, "María José López", Deporte.VOLEIBOL, Pais.CHILE, false),
        Deportista(31, "Felipe Campos", Deporte.JUDO, Pais.CHILE, true),

        // Brasil
        Deportista(32, "Neymar Jr.", Deporte.FUTBOL, Pais.BRASIL, true),
        Deportista(33, "Pelé", Deporte.FUTBOL, Pais.BRASIL, false),
        Deportista(34, "Gisele Bündchen", Deporte.MODELO, Pais.BRASIL, false),
        Deportista(35, "Roberto Carlos", Deporte.FUTBOL, Pais.BRASIL, false),
        Deportista(36, "Ronaldo", Deporte.FUTBOL, Pais.BRASIL, false),
        Deportista(37, "Ayrton Senna", Deporte.CARRERAS, Pais.BRASIL, false),
        Deportista(38, "Marta Vieira", Deporte.FUTBOL, Pais.BRASIL, true),
        Deportista(39, "Rivaldo", Deporte.FUTBOL, Pais.BRASIL, false),
        Deportista(40, "Fabiana Murer", Deporte.ATLETISMO, Pais.BRASIL, true),
        Deportista(41, "Thiago Silva", Deporte.FUTBOL, Pais.BRASIL, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        spinnerPaises = findViewById(R.id.spinnerPaises)
        listView = findViewById(R.id.listView)
        buttonVolver = findViewById(R.id.buttonVolver)


        val paises = listOf("Argentina", "Uruguay", "Colombia", "Chile", "Brasil")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paises)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPaises.adapter = adapter

        spinnerPaises.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                paisSeleccionado = paises[position]
                filtrarDeportistas()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }


        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombreDeportistas)
        listView.adapter = listAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val deportistaSeleccionado = deportistasFiltrados[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("nombre", deportistaSeleccionado.nombre)
            intent.putExtra("deporte", deportistaSeleccionado.deporte.name)
            intent.putExtra("enActividad", deportistaSeleccionado.enActividad)
            startActivity(intent)
        }

        buttonVolver.setOnClickListener {
            finish()
        }
    }

    private fun filtrarDeportistas() {
        deportistasFiltrados.clear()
        nombreDeportistas.clear()


        for (deportista in listaDeportistas) {
            if (deportista.pais == when (paisSeleccionado) {
                    "Argentina" -> Pais.ARGENTINA
                    "Uruguay" -> Pais.URUGUAY
                    "Colombia" -> Pais.COLOMBIA
                    "Chile" -> Pais.CHILE
                    "Brasil" -> Pais.BRASIL
                    else -> null
                }) {
                deportistasFiltrados.add(deportista)
                nombreDeportistas.add(deportista.nombre)
            }
        }


        (listView.adapter as ArrayAdapter<*>).notifyDataSetChanged()
    }
}


