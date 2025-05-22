package com.zybooks.todolistapp

import android.media.Rating
import java.util.UUID

data class Task (
    var id: UUID = UUID.randomUUID(),
    var body: String = "",
    var completed: String = "",
    var rating: String = ""
)