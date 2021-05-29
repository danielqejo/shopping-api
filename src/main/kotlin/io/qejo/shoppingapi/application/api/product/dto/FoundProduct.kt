package io.qejo.shoppingapi.application.api.product.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.qejo.shoppingapi.domain.product.Product
import java.math.BigDecimal

data class FoundProduct(@JsonProperty("sku") val sku:String,
                        @JsonProperty("name") val name:String,
                        @JsonProperty("average_value") val averageValue: BigDecimal) {

    constructor(product:Product): this(product.sku, product.name, product.averageValue)
}