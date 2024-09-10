package com.example.guia3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.Toast

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referenciar los botones
        val registerButton = view.findViewById<Button>(R.id.btn_register)
        val showButton = view.findViewById<Button>(R.id.btn_show)
        val updateButton = view.findViewById<Button>(R.id.btn_update)
        val deleteButton = view.findViewById<Button>(R.id.btn_delete)

        // Configurar los OnClickListener para cada botón
        registerButton.setOnClickListener {
            // Lógica para la acción de registrar
            Toast.makeText(requireContext(), "Registrar seleccionado", Toast.LENGTH_SHORT).show()
            // Aquí podrías iniciar un nuevo fragmento o actividad
        }

        showButton.setOnClickListener {
            // Lógica para la acción de mostrar
            Toast.makeText(requireContext(), "Mostrar seleccionado", Toast.LENGTH_SHORT).show()
        }

        updateButton.setOnClickListener {
            // Lógica para la acción de actualizar
            Toast.makeText(requireContext(), "Actualizar seleccionado", Toast.LENGTH_SHORT).show()
        }

        deleteButton.setOnClickListener {
            // Lógica para la acción de eliminar
            Toast.makeText(requireContext(), "Eliminar seleccionado", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
