package org.eation.food

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloController {

    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    HttpResponse<Response> index() {
        return HttpResponse.ok().body(new Response("Hello"))
    }

    class Response {
        String message

        Response(String message) {
            this.message = message
        }
    }
}
