package org.eation.food.recipe

import org.eation.food.SetupSpecTrait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static groovy.json.JsonOutput.toJson
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.hasSize
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerSpec extends Specification implements SetupSpecTrait {

    @Autowired
    private MockMvc mvc

    def "find all recipes"() {
        when:
        def response = mvc.perform(get("/recipe/").contentType(APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$").isArray())
                .andExpect(jsonPath("\$", hasSize(1)))
                .andReturn()
    }

    def "save recipe"() {
        when:
        def request = toJson(Recipe.builder()
                .name("test recipe")
                .procedure("test procedure")
                .build())
        def response = mvc.perform(post("/recipe/")
                .contentType(APPLICATION_JSON)
                .content(request))
        then:
        response.andExpect(status().isCreated())
                .andExpect(redirectedUrlPattern("**/recipe/*"))
                .andReturn()
    }

    def "save empty recipe"() {
        when:
        def request = toJson(Recipe.builder()
                .build())
        def response = mvc.perform(post("/recipe/")
                .contentType(APPLICATION_JSON)
                .content(request))
        then:
        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("\$.errors[0].fieldName").value("name"))
                .andExpect(jsonPath("\$.errors[0].message").value("must not be blank"))
                .andReturn()
    }

    def "get recipe"() {
        when:
        def response = mvc.perform(get("/recipe/1").contentType(APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.name").value("French fries"))
                .andExpect(jsonPath("\$.procedure").isNotEmpty())
                .andReturn()
    }

    def "recipe not found"() {
        when:
        def response = mvc.perform(get("/recipe/1000").contentType(APPLICATION_JSON))
        then:
        response.andExpect(status().isNotFound())
                .andReturn()
    }

}