package io.qejo.shoppingapi.domain.cart

import io.qejo.shoppingapi.domain.Order
import io.qejo.shoppingapi.domain.product.Product
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table("carts")
class Cart(@Id val id:UUID,
           @Column("name") val name: String,
           @MappedCollection(idColumn = "cart_id") val items: Set<CartItem>) {

    constructor() : this (UUID.randomUUID(), "xis", emptySet())

    constructor(id:UUID, items: Set<CartItem>) : this(id, "qejo", items)

    fun add(product: Product, amount: Int = 1): Cart {
        return when(val cartItem = items[product.sku]) {
            null -> Cart(id, items + CartItem(this.id, product, amount))
            else -> Cart(id, items + cartItem.addAmount(amount))
        }
    }

    fun reduce(product: Product, amount: Int = Int.MAX_VALUE): Cart {
        val cartItem = items[product.sku] ?: return this

        return try {
            val newCartItem = cartItem.reduceAmount(amount)
            Cart(id, items + newCartItem)
        }catch (ex: AmountRemovedExceedsPermitted){
            Cart(id, items - cartItem)
        }
    }

    fun createOrder(): Order {
        val map = items.associate { it.productSku to it.amount }
        return Order(map)
    }

    fun totalValue(): BigDecimal  {
        return items.sumOf { it.totalValue() }
    }

    private operator fun Set<CartItem>.get(productSku: UUID): CartItem? = this.firstOrNull { it.productSku == productSku }

    override fun toString(): String {
        return "Cart(id=$id, items=$items, totalValue=${totalValue()})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cart

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}




