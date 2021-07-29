package io.qejo.shoppingapi.application.api.database

import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.stereotype.Component

@Component
class RepositoryExplicitAddImpl<T>(val jdbcAggregateTemplate: JdbcAggregateTemplate): RepositoryExplicitAdd<T> {

    override fun add(obj: T): T {
        return jdbcAggregateTemplate.insert(obj)
    }

}
