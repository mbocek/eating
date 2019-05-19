package org.eation.food.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
class RecipeController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Recipe>> findAll() {
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> find(@PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@Valid @RequestBody Recipe recipe) {
        Recipe savedRecipe = recipeRepository.save(recipe);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedRecipe.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
