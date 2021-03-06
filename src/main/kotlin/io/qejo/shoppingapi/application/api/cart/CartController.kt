package io.qejo.shoppingapi.application.api.cart

import io.qejo.shoppingapi.application.api.cart.dto.AddItemToCart
import io.qejo.shoppingapi.application.api.cart.exception.CartNotFoundException
import io.qejo.shoppingapi.application.api.product.database.ProductRepository
import io.qejo.shoppingapi.application.api.product.exception.ProductNotFoundException
import io.qejo.shoppingapi.domain.cart.Cart
import io.qejo.shoppingapi.application.api.cart.database.CartRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["/carts"], consumes = [MediaType.APPLICATION_JSON_VALUE])
class CartController(val cartRepository: CartRepository,
                     val productRepository: ProductRepository
) {

    @PostMapping(path =[""])
    fun create(): ResponseEntity<Any> {
        val newCart = Cart()
        cartRepository.add(newCart)
        return ResponseEntity.ok(newCart)
    }

    @PatchMapping(path = ["{cartId}/add"])
    fun addItem(@PathVariable cartId:UUID,
                @RequestBody addItemToCart: AddItemToCart) : ResponseEntity<Any>{
        val cart = cartRepository.findByIdOrNull(cartId)
            ?: throw CartNotFoundException(cartId)

        val product = productRepository.findByIdOrNull(addItemToCart.productSku)
            ?: throw ProductNotFoundException(addItemToCart.productSku.toString())

        val addedItemToCart = cart.add(product, addItemToCart.amount)

        val saved = cartRepository.save(addedItemToCart)

        return ResponseEntity.ok(saved)
    }

    @PatchMapping(path = ["{cartId}/reduce"])
    fun reduceItem(@PathVariable cartId:UUID,
                @RequestBody addItemToCart: AddItemToCart) : ResponseEntity<Any>{
        val cart = cartRepository.findByIdOrNull(cartId)
            ?: throw CartNotFoundException(cartId)

        val product = productRepository.findByIdOrNull(addItemToCart.productSku)
            ?: throw ProductNotFoundException(addItemToCart.productSku.toString())

        cart.reduce(product, addItemToCart.amount)

        val saved = cartRepository.save(cart)

        return ResponseEntity.ok(saved)
    }

    @PostMapping(path = ["{cartId}/close"])
    fun reduceItem(@PathVariable cartId:UUID) : ResponseEntity<Any>{
        val cart = cartRepository.findByIdOrNull(cartId)
            ?: throw CartNotFoundException(cartId)

        val buyingList = cart.createOrder()

        cartRepository.save(cart)

        return ResponseEntity.ok(buyingList)
    }



}
