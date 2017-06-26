package xyz.michaelobi.bakeaide.presentation.recipeStep;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Step;

/**
 * BakeAide
 * Michael Obi
 * 26 06 2017 8:59 AM
 */

public class RecipeStepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        RecipeStepFragment recipeStepFragment = (RecipeStepFragment) fragmentManager
                .findFragmentById(R.id.recipe_step_container);
        Step step = getIntent().getParcelableExtra("step");
        if (step == null) {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (recipeStepFragment == null) {
            recipeStepFragment = RecipeStepFragment.newInstance(step);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.recipe_step_container, recipeStepFragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
