package io.qejo.shoppingapi.application.api.cart.database

import io.qejo.shoppingapi.application.api.database.RepositoryExplicitAdd
import io.qejo.shoppingapi.domain.cart.Cart
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface CartRepository : CrudRepository<Cart, UUID>, PagingAndSortingRepository<Cart, UUID>, RepositoryExplicitAdd<Cart>{

    @Query("""
        SELECT c.*, p.*, ci.*   
        FROM carts c 
        left join cart_items ci on c.id = ci.cart_id
        left join products p on p.sku = ci.product_sku
        where c.id = :id
        """)
    fun findByIdOrNull(id: UUID): Cart?
}
