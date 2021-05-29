package io.qejo.shoppingapi.domain.cart

class AmountRemovedExceedsPermitted :
    Throwable("The amount wanted to be diminished is equal or greater than the amount available") {

}
