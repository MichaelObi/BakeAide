package xyz.michaelobi.bakeaide.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.RealmList;
import rx.Observable;
import xyz.michaelobi.bakeaide.data.models.Ingredient;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.data.models.Step;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 1:26 PM
 */

public class MockRecipeRepository implements RecipeRepository {

    List<Recipe> mockRecipes = new ArrayList<>();


    public MockRecipeRepository() {
        Ingredient ingredient1 = new Ingredient("Flour", 45f, "G");
        Ingredient ingredient2 = new Ingredient("Flour", 2f, "KG");
        RealmList<Ingredient> ingredients = new RealmList<>();
        Collections.addAll(ingredients, ingredient1, ingredient2);
        Step step1 = new Step();
        step1.setVideoURL("https://www.youtube.com/watch?v=zLRTISKN9Zk");
        step1.setShortDescription("A very long step");
        Step step2 = new Step();
        step2.setVideoURL("https://www.youtube.com/watch?v=zLRTISKN9Zk");
        step2.setShortDescription("A very long step");
        RealmList<Step> steps = new RealmList<>();
        Collections.addAll(steps, step1, step2);
        Recipe recipe = new Recipe();
        recipe.setName("Recipe Name");
        recipe.setServings(5);
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        mockRecipes.add(recipe);
    }

    public Observable<List<Recipe>> getRecipeList() {
        return Observable.just(mockRecipes);
    }
}
