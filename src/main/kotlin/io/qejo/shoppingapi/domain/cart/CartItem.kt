package io.qejo.shoppingapi.domain.cart

import io.qejo.shoppingapi.domain.product.Product
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "cart_items")
@Table(name = "cart_items")
class CartItem(cartId: String, product: Product, amount: Int) {

    @EmbeddedId
    val cartItemId: CartItemId = CartItemId(cartId, product)

    @Column(name = "amount", nullable = false)
    var amount: Int = amount
        private set

    operator fun plus(amount: Int) {
        this.amount += amount
    }

    operator fun minus(amount: Int) {
        if(amount >= this.amount) {
            throw AmountRemovedExceedsPermitted()
        }
        this.amount -= amount
    }

    fun totalValue(): BigDecimal {
        return cartItemId.product.averageValue.multiply(BigDecimal(amount))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        if (cartItemId != other.cartItemId) return false

        return true
    }

    override fun hashCode(): Int {
        return cartItemId.hashCode()
    }

    override fun toString(): String {
        return "CartItem(cartItemId=$cartItemId, amount=$amount)"
    }

}
