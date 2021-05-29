package io.qejo.shoppingapi.application.api.cart.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AddItemToCart(@JsonProperty("product_sku") val productSku: String,
                         @JsonProperty("amount") val amount: Int = 1)
