package xyz.michaelobi.bakeaide.data;

import java.util.List;

import rx.Observable;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 23 06 2017 2:44 PM
 */

public interface RecipeRepository {
    Observable<List<Recipe>> getRecipeList();
}
