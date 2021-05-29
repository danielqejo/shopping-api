package io.qejo.shoppingapi.application.api.advice

data class Error(val message:String, var errors: List<String> = emptyList())
