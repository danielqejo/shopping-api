package io.qejo.shoppingapi.domain.cart

import java.lang.IllegalArgumentException

class CartItemAmountIsZeroOrLessException : IllegalArgumentException("CartItem amount cannot be Zero or Less")
