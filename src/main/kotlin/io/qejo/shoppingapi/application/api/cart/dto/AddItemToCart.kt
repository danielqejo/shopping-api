package io.qejo.shoppingapi.application.api.cart.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class AddItemToCart(@JsonProperty("product_sku") val productSku: UUID,
                         @JsonProperty("amount") val amount: Int = 1)
