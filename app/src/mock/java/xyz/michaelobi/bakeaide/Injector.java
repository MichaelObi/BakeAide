package xyz.michaelobi.bakeaide;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.michaelobi.bakeaide.data.MockRecipeRepository;
import xyz.michaelobi.bakeaide.data.RecipeRepository;
import xyz.michaelobi.bakeaide.data.RecipeService;

public class Injector {

    public static RecipeRepository provideRecipeRepository() {
        return new MockRecipeRepository();
    }
}
