package io.qejo.shoppingapi.application.api.cart.exception

import io.qejo.shoppingapi.application.api.exception.NotFoundException
import java.util.*

class CartNotFoundException(cartId:UUID): NotFoundException("Cart", cartId.toString())
