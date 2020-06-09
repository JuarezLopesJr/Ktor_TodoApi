package com.example.ktorTestOne

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import java.text.DateFormat

fun main(args: Array<String>): Unit = io.ktor.server.cio.EngineMain.main(args)

val TodoContentV1 = ContentType("application", "vnd.todoapi.v1+json")

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(StatusPages) {
        exception<Throwable> {
            call.respondText(it.localizedMessage, ContentType.Text.Plain)
            throw it
        }
    }

    install(ContentNegotiation) {
        //register(TodoContentV1, GsonConverter())


        gson(TodoContentV1) {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    install(Routing) {
        /* logging to verify each route */
        trace { application.log.trace(it.buildText()) }
        todoApi()
    }


}

/*
jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                indentObjectsWith(DefaultIndenter(" ", "\n"))
            })
//            registerModule(JavaTimeModel())
        }


 */
