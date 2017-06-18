package xyz.michaelobi.bakeaide.presentation.recipeList;

import java.util.List;

import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.mvp.Mvp;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 1:21 PM
 */

public interface RecipeListMvpContract {

    public interface View extends Mvp.View {
        void showRecipes(List<Recipe> recipes);

        void showError(String error);

        void showLoading();

        void hideLoading();
    }

    public interface Presenter extends Mvp.Presenter<View> {
        void getRecipes();
    }
}
