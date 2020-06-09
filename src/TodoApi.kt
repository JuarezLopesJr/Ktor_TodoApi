package com.example.ktorTestOne

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Routing.todoApi() {
    var todos = mutableListOf(todoItem1, todoItem2, todoItem3)

    route("/api") {

        /* header("Accept", "application/vnd.todoapi.v1+json") {
         }*/

        accept(TodoContentV1) {
            get("/todos") {
                call.respond(todos)
            }
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]!!

            try {
                val todo = todos[id.toInt()]
                call.respond(todo)
            } catch (e: Throwable) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post("/todos") {
            val todo = call.receive<TodoItem>()
            val newTodo = TodoItem(
                todos.size + 1,
                todo.title,
                todo.details,
                todo.assignTo,
                todo.dueDate,
                todo.importance
            )

            todos = (todos + newTodo).toMutableList()

            call.respond(HttpStatusCode.Created, todos)
        }

        patch("/todos/{id}") {
            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@patch
            }
            val foundItem = todos.getOrNull(id.toInt())
            if (foundItem == null) {
                call.respond(HttpStatusCode.NotFound)
                return@patch
            }

            val todo = call.receive<TodoItem>()
            todos = todos.filter { it.id != todo.id }.toMutableList()
            todos = (todos + todo).toMutableList()

            call.respond(HttpStatusCode.NoContent)
        }

        delete("/todos/{id}") {
            val id = call.parameters["id"]
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            val foundItem = todos.getOrNull(id.toInt())
            if (foundItem == null) {
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }
            todos = todos.filter { it.id != id.toInt() }.toMutableList()

            call.respond(HttpStatusCode.NoContent)
        }
    }


}


