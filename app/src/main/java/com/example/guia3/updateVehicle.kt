package com.example.guia3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class UpdateFragment : Fragment() {
    private lateinit var dbHelper: VehicleDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Initialize the VehicleDBHelper
        dbHelper = VehicleDBHelper(requireContext())

        // Find the views from the inflated layout
        val idEditText = view.findViewById<EditText>(R.id.et_id)
        val brandEditText = view.findViewById<EditText>(R.id.et_brand)
        val modelEditText = view.findViewById<EditText>(R.id.et_model)
        val yearEditText = view.findViewById<EditText>(R.id.et_year)
        val mileageEditText = view.findViewById<EditText>(R.id.et_mileage)
        val availableCheckBox = view.findViewById<CheckBox>(R.id.cb_available)
        val loadButton = view.findViewById<Button>(R.id.btn_load)
        val saveButton = view.findViewById<Button>(R.id.btn_save)

        // Set an OnClickListener on the load button
        loadButton.setOnClickListener {
            val idText = idEditText.text.toString()
            if (idText.isNotEmpty()) {
                val id = idText.toInt()
                val vehicle = dbHelper.getVehicleById(id)
                if (vehicle != null) {
                    // Fill the fields with the vehicle's details
                    brandEditText.setText(vehicle[0])
                    modelEditText.setText(vehicle[1])
                    yearEditText.setText(vehicle[2])
                    mileageEditText.setText(vehicle[3])
                    availableCheckBox.isChecked = vehicle[4] == "Sí"
                } else {
                    Toast.makeText(requireContext(), "Vehículo no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, ingresa un ID", Toast.LENGTH_SHORT).show()
            }
        }

        // Set an OnClickListener on the save button
        saveButton.setOnClickListener {
            val idText = idEditText.text.toString()
            val brand = brandEditText.text.toString()
            val model = modelEditText.text.toString()
            val yearText = yearEditText.text.toString()
            val mileageText = mileageEditText.text.toString()

            if (idText.isNotEmpty() && brand.isNotEmpty() && model.isNotEmpty() && yearText.isNotEmpty() && mileageText.isNotEmpty()) {
                val id = idText.toInt()
                val year = yearText.toInt()
                val mileage = mileageText.toInt()
                val available = availableCheckBox.isChecked

                // TODO: Implement logic to retrieve the image URI
                val imageUri = "" // Placeholder, replace with actual image URI logic

                // Update the vehicle in the database
                dbHelper.updateVehicle(id, brand, model, year, mileage, available, imageUri)

                // Notify the user of successful update
                Toast.makeText(requireContext(), "Vehículo actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
