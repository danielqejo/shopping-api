package io.qejo.shoppingapi.application.api.product.database

import io.qejo.shoppingapi.application.api.database.RepositoryExplicitAdd
import io.qejo.shoppingapi.domain.product.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository
    : CrudRepository<Product, UUID>, PagingAndSortingRepository<Product, UUID>, RepositoryExplicitAdd<Product>
