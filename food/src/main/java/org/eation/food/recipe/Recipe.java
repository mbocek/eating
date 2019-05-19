package org.eation.food.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String name;

    @OneToMany
    private List<Ingredient> ingredientes;

    @NotBlank
    @Size(max = 4000)
    private String procedure;
}
