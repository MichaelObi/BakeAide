package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import xyz.michaelobi.bakeaide.Injector;
import xyz.michaelobi.bakeaide.data.local.LocalRecipeRepository;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.data.models.Step;
import xyz.michaelobi.mvp.BasePresenter;

/**
 * BakeAide
 * Michael Obi
 * 24 06 2017 7:08 PM
 */

public class RecipeDetailsPresenter extends BasePresenter<RecipeDetailsMvpContract.View>
        implements RecipeDetailsMvpContract.Presenter {
    LocalRecipeRepository localRecipeRepository = Injector.getLocalRecipeRepository();

    @Override
    public void setupRecipeDetails(Recipe recipe) {
        saveLastOpenedRecipe(recipe);
        getView().showIngredients(recipe.getIngredients());
        getView().showStepList(recipe.getSteps());
    }

    @Override
    public void showRecipeStep(Step step) {
        getView().displayStep(step);
    }

    @Override
    public void loadStepDetails(Step step) {
        getView().displayStep(step);
    }

    @Override
    public void saveLastOpenedRecipe(Recipe recipe) {
        localRecipeRepository.storeMostRecentRecipe(recipe);
getView().updateWidgets();
    }
}
