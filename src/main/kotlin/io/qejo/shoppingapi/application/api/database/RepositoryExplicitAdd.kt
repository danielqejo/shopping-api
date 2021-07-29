package io.qejo.shoppingapi.application.api.database

interface RepositoryExplicitAdd<T> {
    fun add(obj: T): T
}
