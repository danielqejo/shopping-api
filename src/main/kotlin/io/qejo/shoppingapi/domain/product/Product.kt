package io.qejo.shoppingapi.domain.product

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.util.*

@Table("products")
class Product (sku: UUID,
               name:String,
               value:BigDecimal) {

    constructor(name:String, value: BigDecimal): this(UUID.randomUUID(), name, value)

    init {
        if (name.isBlank()) throw ProductNameIsBlankException()
        if (value.lessThanOrEqualsTo(ZERO)) throw ProductAverageValueIsZeroOrLessException()
    }

    @Id
    val sku: UUID = sku

    @Column("name")
    val name:String = name

    @Column("value")
    val value:BigDecimal = value

    fun changeWith(newProduct:Product) : Product{
        return Product(sku, newProduct.name, newProduct.value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (sku != other.sku) return false

        return true
    }

    override fun hashCode(): Int {
        return sku.hashCode()
    }

    override fun toString(): String {
        return "Product(sku='$sku', name='$name', value=$value)"
    }

    private fun BigDecimal.lessThanOrEqualsTo(other: BigDecimal): Boolean = this <= other
}
