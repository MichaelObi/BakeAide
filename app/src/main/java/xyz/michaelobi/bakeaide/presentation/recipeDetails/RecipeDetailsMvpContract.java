package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import java.util.List;

import xyz.michaelobi.bakeaide.data.models.Ingredient;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.data.models.Step;
import xyz.michaelobi.mvp.Mvp;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 1:21 PM
 */

public interface RecipeDetailsMvpContract {

    public interface View extends Mvp.View {

        void showIngredients(List<Ingredient> ingredients);

        void showStepList(List<Step> steps);
    }

    public interface Presenter extends Mvp.Presenter<View> {
        void setupRecipeDetails(Recipe recipe);
    }
}
