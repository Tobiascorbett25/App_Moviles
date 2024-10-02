package com.example.parcial1tp13
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.parcial1tp1.R


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var nationET1: EditText
    private lateinit var nationET2: EditText
    private lateinit var nationET3: EditText


    private lateinit var nationName1: String
    private lateinit var nationName2: String
    private lateinit var nationName3: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button1)
        nationET1 = findViewById(R.id.paisET1)
        nationET2 = findViewById(R.id.paisET2)
        nationET3 = findViewById(R.id.paisET3)


        button.setOnClickListener {

            if (validarPaisesIngresados(nationET1.text, nationET2.text, nationET3.text)) {
                nationName1 = nationET1.text.toString()
                nationName2 = nationET2.text.toString()
                nationName3 = nationET3.text.toString()


                if (validarPaisesAceptados(nationName1, nationName2, nationName3)) {


                    if (noPaisesRepetidos(nationName1, nationName2, nationName3)) {

                        val intent = Intent(this, SecondActivity::class.java)
                        intent.putExtra("PrimerPais", nationName1)
                        intent.putExtra("SegundoPais", nationName2)
                        intent.putExtra("TercerPais", nationName3)
                        startActivity(intent)
                    } else {

                        Toast.makeText(this, "Ingrese 3 países distintos", Toast.LENGTH_SHORT).show()
                    }
                } else {

                    Toast.makeText(this, "Ingrese países válidos. \nPaises aceptados: \nArgentina, Brasil, Chile,\nColombia, Uruguay", Toast.LENGTH_SHORT).show()
                }
            } else {

                Toast.makeText(this, "Ingrese 3 países", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun validarPaisesAceptados(pais1: String, pais2: String, pais3: String): Boolean {
        try {

            val paisEnum1 = Pais.valueOf(pais1.uppercase())
            val paisEnum2 = Pais.valueOf(pais2.uppercase())
            val paisEnum3 = Pais.valueOf(pais3.uppercase())
            return paisEnum1 in Pais.values() &&
                    paisEnum2 in Pais.values() &&
                    paisEnum3 in Pais.values()

        } catch (e: IllegalArgumentException) {
            return false
        }
    }


    fun validarPaisesIngresados(pais1: Editable, pais2: Editable, pais3: Editable): Boolean {
        return pais1.isNotEmpty() &&
                pais2.isNotEmpty() &&
                pais3.isNotEmpty()
    }


    private fun noPaisesRepetidos(pais1: String, pais2: String, pais3: String): Boolean {
        return pais1.uppercase() != pais2.uppercase() &&
                pais2.uppercase() != pais3.uppercase() &&
                pais1.uppercase() != pais3.uppercase()
    }
}