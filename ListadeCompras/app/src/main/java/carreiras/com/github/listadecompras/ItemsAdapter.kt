package carreiras.com.github.listadecompras

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal
import java.text.NumberFormat

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = mutableListOf<ItemModel>()
    var totalPrice = BigDecimal.ZERO

    fun addItem(newItem: ItemModel) {
        items.add(newItem)
        totalPrice += newItem.preco
        notifyDataSetChanged()
    }

    fun removeItem(item: ItemModel) {
        items.remove(item)
        totalPrice -= item.preco
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textViewItem)
        val button = view.findViewById<ImageButton>(R.id.imageButton)
        val preco = view.findViewById<TextView>(R.id.precoProduto)
        val data = view.findViewById<TextView>(R.id.dataProduto)

        fun bind(item: ItemModel) {
            textView.text = item.name
            val numberFormatter = NumberFormat.getCurrencyInstance()
            preco.text = numberFormatter.format(item.preco)  // Use numberFormatter
            data.text = item.data.toString()

            button.setOnClickListener {
                item.onRemove(item)
            }

        }
    }
}
