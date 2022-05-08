package com.example.bokemonappwithkotlinandadvancedtech.util

interface EntityMapper<Entity,DomainModel> {
    fun mapFromEntity(entity:Entity):DomainModel //that inside retrofit package
    fun mapToEntity(domainModel:DomainModel):Entity // that inside model package
}