package xyz.michaelobi.bakeaide.presentation.recipeList;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import xyz.michaelobi.bakeaide.data.RecipeRepository;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.mvp.BasePresenter;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 1:23 PM
 */

public class RecipeListPresenter extends BasePresenter<RecipeListMvpContract.View> implements RecipeListMvpContract.Presenter {
    private RecipeRepository recipeRepository = new RecipeRepository();

    @Override
    public void getRecipes() {
        checkViewAttached();
        getView().showLoading();
        addSubscription(recipeRepository.getRecipeList()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Recipe>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Recipe> recipes) {
                        getView().hideLoading();
                        getView().showRecipes(recipes);
                    }
                })
        );
    }
}
