package io.qejo.shoppingapi.domain.cart

import io.qejo.shoppingapi.domain.BuyingList
import io.qejo.shoppingapi.domain.product.Product
import java.lang.Exception
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "carts")
@Table(name = "carts")
class Cart(id:UUID, items: MutableSet<CartItem>) {

    constructor(): this(UUID.randomUUID(), mutableSetOf())

    @Id
    val id:String = id.toString()

    @OneToMany(mappedBy = "cartItemId.cartId", cascade = [CascadeType.ALL], orphanRemoval = true)
    var items: MutableSet<CartItem> = items
        private set

    fun add(product: Product, amount: Int = 1) {
        val cartItem = items[product]
        if (cartItem == null) {
            items += CartItem(this.id, product, amount)
            return
        }
        cartItem + amount
    }

    fun reduce(product: Product, amount: Int = Int.MAX_VALUE) {
        val cartItem = items[product] ?: return

        runCatching { cartItem - amount }
            .onFailure {
                when(it) {
                    is AmountRemovedExceedsPermitted -> items -= cartItem
                    else -> throw Exception("Something went wrong trying to remove items from cart", it)
                }
            }
    }

    fun close(): BuyingList {
        val map = items.associate { it.cartItemId.product.name to it.amount }
        items.clear()
        return BuyingList(map)
    }

    fun totalValue(): BigDecimal  {
        return items.sumOf { it.totalValue() }
    }

    private operator fun Set<CartItem>.get(product: Product): CartItem? = this.firstOrNull { it.cartItemId.product == product }

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




