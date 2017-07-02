package xyz.michaelobi.bakeaide.data.local;

import io.realm.Realm;
import io.realm.RealmResults;
import xyz.michaelobi.bakeaide.data.models.Recipe;

/**
 * BakeAide
 * Michael Obi
 * 02 07 2017 4:02 PM
 */

public class RealmRecipeRepository implements LocalRecipeRepository {
    private Realm realm;

    public RealmRecipeRepository() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public Recipe getLastAccessedRecipe() {
        return realm.where(Recipe.class).findFirst();
    }

    @Override
    public void storeMostRecentRecipe(final Recipe recipe) {
        final RealmResults<Recipe> results = realm.where(Recipe.class).findAll();
        realm.executeTransaction(realmObject -> {
            results.deleteAllFromRealm();
            realmObject.copyToRealm(recipe);
        });
    }
}
