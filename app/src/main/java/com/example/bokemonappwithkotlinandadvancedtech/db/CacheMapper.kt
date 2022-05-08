package com.example.bokemonappwithkotlinandadvancedtech.db

import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.util.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor():EntityMapper<BlogCashEntity , Blog> {
    override fun mapFromEntity(entity: BlogCashEntity): Blog {
        return Blog(id = entity.id ,
            title = entity.title ,
            body = entity.body ,
            image = entity.image ,
            category = entity.category)
    }

    override fun mapToEntity(domainModel: Blog): BlogCashEntity {
        return BlogCashEntity(id = domainModel.id ,
            title = domainModel.title ,
            body = domainModel.body ,
            image = domainModel.image ,
            category = domainModel.category)
    }

    fun mapFromEntityList(entities:List<BlogCashEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}