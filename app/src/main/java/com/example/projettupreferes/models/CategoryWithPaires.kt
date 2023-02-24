package com.example.projettupreferes.models

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithPaires(
    @Embedded var category: Category,
    @Relation(
        parentColumn = "idCategory",
        entityColumn = "categoryId"
    )
    var paires: List<Paire>
)
