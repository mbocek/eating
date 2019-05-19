package org.eation.food.recipe;

import org.springframework.data.repository.CrudRepository;

interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
