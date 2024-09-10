package com.example.guia3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class DeleteFragment : Fragment() {
    private lateinit var dbHelper: VehicleDBHelper
    private lateinit var vehicleIdInput: EditText
    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete, container, false)

        // Initialize the VehicleDBHelper
        dbHelper = VehicleDBHelper(requireContext())

        // Find the EditText and Button from the inflated view
        vehicleIdInput = view.findViewById(R.id.et_vehicle_id)
        deleteButton = view.findViewById(R.id.btn_delete)

        // Set an OnClickListener on the delete button
        deleteButton.setOnClickListener {
            val idText = vehicleIdInput.text.toString()

            // Check if the input is not empty
            if (idText.isNotEmpty()) {
                val id = idText.toInt()
                dbHelper.deleteVehicle(id)
                Toast.makeText(requireContext(), "Vehículo eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Por favor ingrese un ID válido", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
