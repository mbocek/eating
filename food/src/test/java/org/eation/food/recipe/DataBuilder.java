package org.eation.food.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.String.format;

@Component
public class DataBuilder {

    @Autowired
    private EntityManager em;

    private Queue<String> data = new LinkedList<String>(Arrays.asList("Recipe"));

    @Transactional
    public void setupData() {
        Recipe frenchFries = Recipe.builder().name("French fries")
                .procedure("Peel and rinse the potatoes.")
                .build();
        em.persist(frenchFries);
    }

    @Transactional
    public void tearDown() {
        data.forEach(it -> em.createQuery(format("delete %s ", it)));
    }
}
