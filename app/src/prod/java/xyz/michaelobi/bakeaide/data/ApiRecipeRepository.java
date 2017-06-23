package xyz.michaelobi.bakeaide.data;

import java.io.IOException;
import java.util.List;

import rx.Observable;
import xyz.michaelobi.bakeaide.Injector;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 15 06 2017 1:26 PM
 */

public class ApiRecipeRepository implements RecipeRepository {
    private RecipeService recipeService;

    public ApiRecipeRepository() {
        recipeService = Injector.provideRecipeService();
    }

    @Override
    public Observable<List<Recipe>> getRecipeList() {
        return Observable.defer(() -> recipeService.getRecipeList())
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }
}
