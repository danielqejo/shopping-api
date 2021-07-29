package io.qejo.shoppingapi.domain

import io.qejo.shoppingapi.domain.product.Product
import java.util.*

data class Order(val productNameAmount: Map<UUID, Int>) {
}
