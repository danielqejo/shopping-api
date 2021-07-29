package io.qejo.shoppingapi.domain.product

import java.lang.IllegalArgumentException

class ProductNameIsBlankException : IllegalArgumentException("Product name cannot be Blank!")
