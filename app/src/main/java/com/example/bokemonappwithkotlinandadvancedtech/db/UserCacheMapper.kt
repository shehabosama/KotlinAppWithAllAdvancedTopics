package com.example.bokemonappwithkotlinandadvancedtech.db

import com.example.bokemonappwithkotlinandadvancedtech.model.User
import com.example.bokemonappwithkotlinandadvancedtech.network.UserNetworkEntity
import com.example.bokemonappwithkotlinandadvancedtech.util.EntityMapper
import javax.inject.Inject

class UserCacheMapper @Inject constructor():EntityMapper<UserCashEntity , User> {
    override fun mapFromEntity(entity: UserCashEntity): User {
            return User(
                id = entity.id,
                username = entity.username ,
                email = entity.email ,
                password = entity.password ,
                location = entity.location,
                is_admin = entity.is_admin ,
                is_bloked = entity.is_bloked ,
                user_token = entity.user_token)
    }

    override fun mapToEntity(domainModel: User): UserCashEntity {
        return UserCashEntity(
            id = domainModel.id,
            username = domainModel.username ,
            email = domainModel.email ,
            password = domainModel.password ,
            location = domainModel.location,
            is_admin = domainModel.is_admin ,
            is_bloked = domainModel.is_bloked ,
            user_token = domainModel.user_token)
    }

    fun mapFromEntityList(entities:List<UserCashEntity>):List<User>{
        return entities.map { mapFromEntity(it) }
    }
}