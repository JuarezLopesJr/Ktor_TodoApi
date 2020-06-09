package com.example.ktorTestOne

data class TodoItem(
    val id: Int,
    val title: String,
    val details: String,
    val assignTo: String,
    val dueDate: String,
    val importance: Importance
)

enum class Importance {
    LOW, MEDIUM, HIGH
}

val todoItem1 = TodoItem(
    1,
    "add rest api data access",
    "add database support",
    "me",
   "26-JUN-2020",
    Importance.HIGH
)

val todoItem2 = TodoItem(
    2,
    "add rest api service",
    "add a service to get the data",
    "me",
   "15-MAY-2019",
    Importance.MEDIUM
)

val todoItem3 = TodoItem(
    3,
    "add rest api service",
    "add a service to get the data",
    "me",
   "02-FEV-2000",
    Importance.LOW
)

