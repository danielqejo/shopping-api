package io.qejo.shoppingapi.domain.product

import java.lang.IllegalArgumentException

class ProductAverageValueIsZeroOrLessException : IllegalArgumentException("Product Average Value cannot be less than or equals to zero") {
}
