package io.qejo.shoppingapi.domain.cart

import io.qejo.shoppingapi.domain.product.Product
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceConstructor
import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.*

@Table("cart_items")
data class CartItem(@Id val id: Long,
                    @Column("cart_id") val cartId: UUID,
                    @Column("product_sku") val productSku: UUID,
                    @Transient @Value("value") val value: BigDecimal,
                    @Column("amount") val amount: Int) {

    constructor(cartId: UUID, product: Product, amount: Int) : this(0, cartId, product.sku, product.value, amount)

    @PersistenceConstructor
    constructor(id: Long, cartId: UUID, product: Product, amount: Int) :
            this (id, cartId, product.sku, product.value, amount)

    init {
        if(amount <= 0) throw AmountRemovedExceedsPermitted()
    }

    fun addAmount(amount: Int): CartItem {
        return CartItem(id, cartId, productSku,value, this.amount + amount)
    }

    fun reduceAmount(amount: Int): CartItem {
        return CartItem(id, cartId, productSku,value, this.amount - amount)
    }

    fun totalValue(): BigDecimal {
        return value.multiply(BigDecimal(amount))
    }

}
