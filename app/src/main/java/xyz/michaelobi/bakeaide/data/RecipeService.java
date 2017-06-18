package xyz.michaelobi.bakeaide.data;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * Created by Michael on 10/03/2017.
 */

public interface RecipeService {

    @GET("/android-baking-app-json")
    Observable<List<Recipe>> getRecipeList();

}
