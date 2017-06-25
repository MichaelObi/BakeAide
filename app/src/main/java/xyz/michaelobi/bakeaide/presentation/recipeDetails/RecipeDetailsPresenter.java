package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.mvp.BasePresenter;

/**
 * BakeAide
 * Michael Obi
 * 24 06 2017 7:08 PM
 */

public class RecipeDetailsPresenter extends BasePresenter<RecipeDetailsMvpContract.View>
        implements RecipeDetailsMvpContract.Presenter {

    @Override
    public void setupRecipeDetails(Recipe recipe) {
        getView().showIngredients(recipe.getIngredients());
        getView().showStepList(recipe.getSteps());
    }
}
