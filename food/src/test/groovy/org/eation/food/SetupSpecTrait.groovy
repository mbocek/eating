package org.eation.food

import org.eation.food.recipe.DataBuilder
import org.junit.After
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired

trait SetupSpecTrait {

    @Autowired
    private DataBuilder dataBuilder

    @Before
    def setupDataSpec() {
        dataBuilder.setupData()
    }

    @After
    def cleanupDataSpec() {
        dataBuilder.tearDown()
    }
}