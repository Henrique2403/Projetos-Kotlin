package carreiras.com.github.listadecompras

import java.math.BigDecimal

data class ItemModel(
    val name: String,
    val preco: BigDecimal,
    val onRemove: (ItemModel) -> Unit,
    val data: String)
