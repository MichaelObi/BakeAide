package xyz.michaelobi.bakeaide;

import xyz.michaelobi.bakeaide.data.MockRecipeRepository;
import xyz.michaelobi.bakeaide.data.RecipeRepository;
import xyz.michaelobi.bakeaide.data.local.LocalRecipeRepository;
import xyz.michaelobi.bakeaide.data.local.RealmRecipeRepository;

public class Injector {
    private static LocalRecipeRepository localRecipeRepository;

    public static LocalRecipeRepository getLocalRecipeRepository() {
        if (localRecipeRepository == null) {
            localRecipeRepository = new RealmRecipeRepository();
        }
        return localRecipeRepository;
    }
    public static RecipeRepository provideRecipeRepository() {
        return new MockRecipeRepository();
    }
}
