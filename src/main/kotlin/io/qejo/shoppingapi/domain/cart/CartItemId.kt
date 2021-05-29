package io.qejo.shoppingapi.domain.cart

import io.qejo.shoppingapi.domain.product.Product
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Embeddable
data class CartItemId(@Column(name = "cart_id") val cartId: String,
                      @OneToOne @JoinColumn(name = "product_sku") val product: Product) : Serializable
