package com.example.guia3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment

class ShowFragment : Fragment() {

    private lateinit var tableLayout: TableLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_show, container, false)
        tableLayout = view.findViewById(R.id.table_vehicle_data)

        val dbHelper = VehicleDBHelper(requireContext())
        val vehicles = dbHelper.getAllVehiclesWithDetails()

        // Agregar encabezado de tabla
        val headerRow = TableRow(requireContext())
        val headers = listOf("ID", "Marca", "Modelo", "Año", "Kilometraje", "Disponible")
        headers.forEach { header ->
            headerRow.addView(createTextView(header, isHeader = true))
        }
        tableLayout.addView(headerRow)

        // Agregar filas con los datos de vehículos
        for (vehicle in vehicles) {
            val dataRow = TableRow(requireContext())
            vehicle.forEach { column ->
                dataRow.addView(createTextView(column))
            }
            tableLayout.addView(dataRow)
        }

        return view
    }

    private fun createTextView(text: String, isHeader: Boolean = false): TextView {
        val textView = TextView(requireContext())
        textView.text = text
        textView.setPadding(8.dp, 8.dp, 8.dp, 8.dp)
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        if (isHeader) {
            textView.setBackgroundColor(requireContext().getColor(android.R.color.darker_gray))
            textView.setTextColor(requireContext().getColor(android.R.color.white))
        }
        return textView
    }

    private val Int.dp: Int
        get() = (this * requireContext().resources.displayMetrics.density).toInt()
}
