package io.qejo.shoppingapi.application.api.product.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class ProductCreate(@JsonProperty("name") val name: String,
                         @JsonProperty("average_value") var averageValue: BigDecimal)