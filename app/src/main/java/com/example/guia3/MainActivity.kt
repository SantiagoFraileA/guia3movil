package com.example.guia3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reemplaza el fragmento inicial si es la primera vez que se crea la actividad
        if (savedInstanceState == null) {
            showDefaultMessage()
        }

        // Referencia a los botones
        val registerButton = findViewById<Button>(R.id.btn_register)
        val showButton = findViewById<Button>(R.id.btn_show)
        val updateButton = findViewById<Button>(R.id.btn_update)
        val deleteButton = findViewById<Button>(R.id.btn_delete)

        // Configura los OnClickListener para cada botón
        registerButton.setOnClickListener {
            replaceFragment(RegisterFragment())
        }

        showButton.setOnClickListener {
            replaceFragment(ShowFragment())
        }

        updateButton.setOnClickListener {
            replaceFragment(UpdateFragment())
        }

        deleteButton.setOnClickListener {
            replaceFragment(DeleteFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        // Oculta el mensaje por defecto y muestra el contenedor del fragmento
        findViewById<TextView>(R.id.tv_default_message).visibility = View.GONE
        findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE
    }

    private fun showDefaultMessage() {
        // Muestra el mensaje por defecto y oculta el contenedor del fragmento
        findViewById<TextView>(R.id.tv_default_message).visibility = View.VISIBLE
        findViewById<FrameLayout>(R.id.fragment_container).visibility = View.GONE
    }
}
