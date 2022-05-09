package com.example.bokemonappwithkotlinandadvancedtech.network

import android.util.Log
import com.example.bokemonappwithkotlinandadvancedtech.model.Blog
import com.example.bokemonappwithkotlinandadvancedtech.util.EntityMapper
import javax.inject.Inject

class BlogMapper
@Inject
constructor() : EntityMapper<BlogNetworkEntity , Blog>
{
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        Log.e("Tracking", "inseide mapFromEntity" )
        return Blog(id = entity.id ,
            title = entity.title ,
            image = entity.image ,
            body = entity.body,
            category = entity.category)
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(id = domainModel.id ,
            title = domainModel.title ,
            image = domainModel.image ,
            body = domainModel.body,
            category = domainModel.category)
    }

    fun mapFromEntityEntityList(entities:List<BlogNetworkEntity>):List<Blog>{
        return entities.map { mapFromEntity(it) }
    }
}
