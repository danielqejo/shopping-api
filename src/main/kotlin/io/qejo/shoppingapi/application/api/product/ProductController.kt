package io.qejo.shoppingapi.application.api.product

import io.qejo.shoppingapi.application.api.product.dto.FoundProduct
import io.qejo.shoppingapi.application.api.product.dto.ProductCreate
import io.qejo.shoppingapi.application.api.product.exception.ProductNotFoundException
import io.qejo.shoppingapi.domain.product.Product
import io.qejo.shoppingapi.domain.product.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path=["/products"], consumes = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(val productRepository: ProductRepository) {

    @PostMapping(path = [""])
    fun create(@RequestBody productCreate: ProductCreate):ResponseEntity<Any> {
        val newProduct = Product(productCreate.name, productCreate.averageValue)
        productRepository.save(newProduct)
        return ResponseEntity.ok().build()
    }

    @GetMapping(path = ["{sku}"])
    fun findBy(@PathVariable sku: String): ResponseEntity<Any> {
        val product = productRepository.findByIdOrNull(sku) ?: throw ProductNotFoundException(sku)

        return ResponseEntity.ok(FoundProduct(product))
    }

    @GetMapping(path = [""])
    fun findBy(@RequestParam(defaultValue = "20") pageSize: Int,
               @RequestParam(defaultValue = "0") pageNumber: Int): ResponseEntity<Any> {
        val pageRequest = PageRequest.of(pageNumber, pageSize)
        val products = productRepository.findAll(pageRequest)

        val foundProducts = products.map { FoundProduct(it) }
        return ResponseEntity.ok(foundProducts)
    }

}