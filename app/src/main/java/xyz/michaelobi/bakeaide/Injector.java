package xyz.michaelobi.bakeaide;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.michaelobi.bakeaide.data.RecipeService;

public class Injector {
    private static Retrofit retrofitInstance;
    private static RecipeService recipeService;


    public static Retrofit provideRetrofit() {
        if (retrofitInstance == null) {

            Retrofit.Builder retrofit = new Retrofit.Builder().client(new OkHttpClient())
                    .baseUrl("http://go.udacity.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
            retrofitInstance = retrofit.build();
        }
        return retrofitInstance;
    }

    public static RecipeService provideRecipeService() {
        if (recipeService == null) {
            recipeService = provideRetrofit().create(RecipeService.class);
        }
        return recipeService;
    }

}
