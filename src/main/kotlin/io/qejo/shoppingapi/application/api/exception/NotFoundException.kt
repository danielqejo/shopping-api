package io.qejo.shoppingapi.application.api.exception

open class NotFoundException(what:String, with: String)
    : RuntimeException("Unable to find $what with identificator $with"){
}
