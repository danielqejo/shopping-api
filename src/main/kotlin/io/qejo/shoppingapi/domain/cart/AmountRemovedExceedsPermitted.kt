package io.qejo.shoppingapi.domain.cart

import java.lang.IllegalArgumentException

class AmountRemovedExceedsPermitted :
    IllegalArgumentException("The amount wanted to be diminished is equal or greater than the amount available") {

}
