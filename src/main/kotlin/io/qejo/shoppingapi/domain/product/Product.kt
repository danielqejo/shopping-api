package io.qejo.shoppingapi.domain.product

import java.math.BigDecimal
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "products")
@Table(name = "products")
class Product(sku: String,
              name:String,
              mediumValue:BigDecimal) {

    constructor(name:String, mediumValue: BigDecimal): this(UUID.randomUUID().toString(), name, mediumValue)

    @Id
    @Column(name="sku")
    val sku: String = sku

    @Column(name="name", nullable = false, updatable = true)
    var name:String = name
        private set

    @Column(name="average_value", nullable = false, updatable = true)
    var averageValue:BigDecimal = mediumValue
        private set

    fun changeMediumValue(newMediumValue:BigDecimal){
        this.averageValue = newMediumValue
    }

    fun changeName(newName:String) {
        this.name = newName
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
        return "Product(sku='$sku', name='$name', averageValue=$averageValue)"
    }
}
