package io.qejo.shoppingapi.application.api.product.exception

import io.qejo.shoppingapi.application.api.exception.NotFoundException

data class ProductNotFoundException(val productSku: String): NotFoundException("Product", productSku)
