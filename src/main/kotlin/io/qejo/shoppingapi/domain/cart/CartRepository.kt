package io.qejo.shoppingapi.domain.cart

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartRepository : JpaRepository<Cart, String>
