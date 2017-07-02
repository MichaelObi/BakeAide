package xyz.michaelobi.bakeaide.data.local;

import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 02 07 2017 3:23 PM
 */

public interface LocalRecipeRepository {

    Recipe getLastAccessedRecipe();

    void storeMostRecentRecipe(Recipe recipe);
}
