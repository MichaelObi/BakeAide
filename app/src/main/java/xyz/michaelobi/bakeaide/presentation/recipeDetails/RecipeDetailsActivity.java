package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import xyz.michaelobi.bakeaide.R;

public class RecipeDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        FragmentManager fragmentManager = getSupportFragmentManager();
        RecipeDetailsFragment recipeDetailsFragment = (RecipeDetailsFragment) fragmentManager
                .findFragmentById(R.id.recipe_details_container);
        if (recipeDetailsFragment == null) {
            recipeDetailsFragment = new RecipeDetailsFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.recipe_details_container, recipeDetailsFragment);
            transaction.commit();
        }
    }
}
