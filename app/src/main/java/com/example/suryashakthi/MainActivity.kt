package com.example.suryashakthi

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.suryashakthi.data.AppDatabase
import com.example.suryashakthi.model.EnergyLog
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import kotlinx.coroutines.launch
import java.io.File
import android.net.Uri
import androidx.core.content.FileProvider
import android.content.ActivityNotFoundException
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlin.random.Random
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Components
        val etGenerated = findViewById<EditText>(R.id.etGenerated)
        val etConsumed = findViewById<EditText>(R.id.etConsumed)

        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        val btnAnalytics = findViewById<Button>(R.id.btnAnalytics)
        val btnExport = findViewById<Button>(R.id.btnExport)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvEfficiency = findViewById<TextView>(R.id.tvEfficiency)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val tvSuggestion = findViewById<TextView>(R.id.tvSuggestion)

        val progressEfficiency =
            findViewById<ProgressBar>(R.id.progressEfficiency)

        // Database
        val db = AppDatabase.getDatabase(this)
        val dao = db.energyDao()
        val spinnerWeather =
            findViewById<Spinner>(R.id.spinnerWeather)

        val weatherOptions =
            arrayOf("Sunny ☀️", "Cloudy ☁️", "Rainy 🌧")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            weatherOptions
        )

        spinnerWeather.adapter = adapter
        // Load Last Entry
        lifecycleScope.launch {

            val logs = dao.getAllLogs()

            if (logs.isNotEmpty()) {

                val last = logs.last()

                tvResult.text =
                    "Last Entry:\nNet: ${last.generated - last.consumed} kWh\nSavings: ₹${last.savings}"
            }
        }

        // Calculate Button
        btnCalculate.setOnClickListener {

            val generatedInput = etGenerated.text.toString()
            val consumedInput = etConsumed.text.toString()

            // Validation
            if (generatedInput.isEmpty() || consumedInput.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please enter both values",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }
            val selectedWeather =
                spinnerWeather.selectedItem.toString()

            val inputGenerated =
                generatedInput.toDoubleOrNull()

            if (inputGenerated == null) {

                Toast.makeText(
                    this,
                    "Enter valid generated energy",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val weatherMultiplier = when {

                selectedWeather.contains("Sunny") -> 1.2

                selectedWeather.contains("Cloudy") -> 0.8

                else -> 0.5
            }

            val generated =
                inputGenerated * weatherMultiplier
            val consumed = consumedInput.toDoubleOrNull()

            if (generated == null || consumed == null) {

                Toast.makeText(
                    this,
                    "Enter valid numbers",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Calculations
            val netEnergy = generated - consumed
            val exportMessage = if (netEnergy > 0) {

                "Extra energy exported to grid ⚡"

            } else {

                "Using more energy than generated"
            }
            val savings = netEnergy * 5

            val efficiency = if (generated > 0) {
                ((generated - consumed) / generated * 100).toInt()
            } else {
                0
            }

            // Update UI
            progressEfficiency.progress =
                efficiency.coerceIn(0, 100)

            tvEfficiency.text = "Efficiency: $efficiency%"

            val status = when {

                efficiency >= 80 ->
                    "Excellent Solar Usage 🌞"

                efficiency >= 50 ->
                    "Good Energy Management 👍"

                efficiency >= 20 ->
                    "Average Efficiency ⚡"

                else ->
                    "High Energy Consumption ⚠️"
            }

            tvStatus.text = status

            val suggestion = when {

                efficiency >= 80 ->
                    "AI Tip: Excellent solar usage. Best time to run heavy appliances is now 🌞"

                efficiency >= 50 ->
                    "AI Tip: Energy usage is balanced. Try reducing unnecessary loads during evening hours ⚡"

                efficiency >= 20 ->
                    "AI Tip: Moderate efficiency detected. Consider optimizing appliance usage."

                else ->
                    "AI Warning: High consumption detected. Reduce appliance usage or improve solar generation."
            }

            tvSuggestion.text = suggestion

            val resultText =
                "Net Energy: $netEnergy kWh\n" +
                        "Savings: ₹$savings\n" +
                        exportMessage

            tvResult.text = resultText

            Toast.makeText(
                this,
                resultText,
                Toast.LENGTH_LONG
            ).show()

            // Save to Room DB
            lifecycleScope.launch {

                val log = EnergyLog(
                    generated = generated,
                    consumed = consumed,
                    savings = savings
                )

                dao.insert(log)
            }

            // Clear Inputs
            etGenerated.text.clear()
            etConsumed.text.clear()
        }

        // History Screen
        btnHistory.setOnClickListener {

            startActivity(
                Intent(this, HistoryActivity::class.java)
            )
        }

        // Analytics Screen
        btnAnalytics.setOnClickListener {

            startActivity(
                Intent(this, AnalyticsActivity::class.java)
            )
        }

        // Export PDF

        btnExport.setOnClickListener {

            try {

                val file = File(
                    getExternalFilesDir(null),
                    "EnergyReport.pdf"
                )

                val writer = PdfWriter(file)
                val pdfDocument = PdfDocument(writer)
                val document = Document(pdfDocument)

                document.add(
                    Paragraph("SURYA SHAKTHI ENERGY REPORT")
                )

                document.add(Paragraph(" "))

                document.add(
                    Paragraph(tvResult.text.toString())
                )

                document.add(
                    Paragraph(tvEfficiency.text.toString())
                )

                document.add(
                    Paragraph(tvStatus.text.toString())
                )

                document.add(
                    Paragraph(tvSuggestion.text.toString())
                )

                document.close()

                Toast.makeText(
                    this,
                    "PDF Generated Successfully",
                    Toast.LENGTH_LONG
                ).show()

                // Open PDF
                val uri: Uri = FileProvider.getUriForFile(
                    this,
                    "${packageName}.provider",
                    file
                )

                val intent = Intent(Intent.ACTION_VIEW)

                intent.setDataAndType(uri, "application/pdf")

                intent.flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or
                            Intent.FLAG_ACTIVITY_NO_HISTORY

                try {

                    startActivity(intent)

                } catch (e: ActivityNotFoundException) {

                    Toast.makeText(
                        this,
                        "No PDF Viewer Installed",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "PDF Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}