package com.mohfahmi.core.domain.utils

import com.mohfahmi.core.data.source.local.database.entity.ActivityEntity
import com.mohfahmi.core.data.source.network.response.ArticleResponse
import com.mohfahmi.core.domain.models.ActivityDomain
import com.mohfahmi.core.domain.models.ArticleDomain

fun ActivityEntity.mapToDomain(): ActivityDomain {
    return with(this) {
        ActivityDomain(id, name, amount, date, isExpense)
    }
}

fun ActivityDomain.mapToEntity(): ActivityEntity {
    return with(this) {
        ActivityEntity(id, name, amount, date, isExpense)
    }
}

fun ArticleResponse.mapToDomain(): List<ArticleDomain> {
    return with(this) {
        data!!.map {
            ArticleDomain(it?.image?.small!!, it.link!!, it.title!!)
        }
    }
}