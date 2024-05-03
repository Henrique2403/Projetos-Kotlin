package carreiras.com.github.listadecompras

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextPreco = findViewById<EditText>(R.id.editTextPreco)
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)  // New TextView

        button.setOnClickListener {
            if (editTextNome.text.isEmpty() || editTextPreco.text.isEmpty()) {
                editTextNome.error = "Preencha um valor"
                editTextPreco.error = "Preencha um valor"
                return@setOnClickListener
            }

            val data = LocalDateTime.now()

            val dataFormatada = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

            val precoFormatado = BigDecimal.ZERO

            val item = ItemModel(
                name = editTextNome.text.toString(),
                preco = editTextPreco.text.toString().toBigDecimal(),
                data = dataFormatada,
                onRemove = {
                    itemsAdapter.removeItem(it)
                    totalPriceTextView.text = formatTotalPrice(itemsAdapter.totalPrice)
                },

                )

            Log.d("Tag", "$item")

            itemsAdapter.addItem(item)
            editTextNome.text.clear()
            editTextPreco.text.clear()

            // Update total price on item add
            totalPriceTextView.text = formatTotalPrice(itemsAdapter.totalPrice)
        }
    }

    private fun formatTotalPrice(totalPrice: BigDecimal): String {
        val numberFormatter = NumberFormat.getCurrencyInstance()
        val formattedTotalPrice = "Total: " + numberFormatter.format(totalPrice)
        return formattedTotalPrice
    }

}
