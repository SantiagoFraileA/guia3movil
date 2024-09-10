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

class RegisterFragment : Fragment() {
    private lateinit var dbHelper: VehicleDBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Initialize the VehicleDBHelper
        dbHelper = VehicleDBHelper(requireContext())

        // Find the views from the inflated layout
        val brandEditText = view.findViewById<EditText>(R.id.et_brand)
        val modelEditText = view.findViewById<EditText>(R.id.et_model)
        val yearEditText = view.findViewById<EditText>(R.id.et_year)
        val mileageEditText = view.findViewById<EditText>(R.id.et_mileage)
        val availableCheckBox = view.findViewById<CheckBox>(R.id.cb_available)
        val saveButton = view.findViewById<Button>(R.id.btn_save)

        // Set an OnClickListener on the save button
        saveButton.setOnClickListener {
            val brand = brandEditText.text.toString()
            val model = modelEditText.text.toString()
            val yearText = yearEditText.text.toString()
            val mileageText = mileageEditText.text.toString()

            // Validate inputs before proceeding
            if (brand.isNotEmpty() && model.isNotEmpty() && yearText.isNotEmpty() && mileageText.isNotEmpty()) {
                val year = yearText.toInt()
                val mileage = mileageText.toInt()
                val available = availableCheckBox.isChecked

                // TODO: Implement logic to retrieve the image URI
                val imageUri = "" // Placeholder, replace with actual image URI logic

                // Insert the vehicle into the database
                dbHelper.insertVehicle(brand, model, year, mileage, available, imageUri)

                // Notify the user of successful registration
                Toast.makeText(requireContext(), "Vehículo registrado", Toast.LENGTH_SHORT).show()

                // Clear input fields after saving
                brandEditText.text.clear()
                modelEditText.text.clear()
                yearEditText.text.clear()
                mileageEditText.text.clear()
                availableCheckBox.isChecked = false
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
