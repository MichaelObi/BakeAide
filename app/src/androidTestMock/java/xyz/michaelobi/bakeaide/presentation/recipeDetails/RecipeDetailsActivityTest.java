package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.data.models.Ingredient;
import xyz.michaelobi.bakeaide.data.models.Recipe;
import xyz.michaelobi.bakeaide.data.models.Step;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * BakeAide
 * Michael Obi
 * 24 06 2017 5:53 PM
 */

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {

    List<Recipe> mockRecipes = new ArrayList<>();
    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activityTestRule = new ActivityTestRule<RecipeDetailsActivity>(RecipeDetailsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            setupTestData();
            Recipe recipe = mockRecipes.get(0);
            Context mockContext = InstrumentationRegistry
                    .getInstrumentation().getTargetContext();
            Intent result = new Intent(mockContext, RecipeDetailsActivity.class);
            result.putExtra("recipe", recipe);
            return result;
        }
    };

    private void setupTestData() {
        Ingredient ingredient1 = new Ingredient("Flour", 45f, "G");
        Ingredient ingredient2 = new Ingredient("Flour", 2f, "KG");
        List<Ingredient> ingredients = new ArrayList<>();
        Collections.addAll(ingredients, ingredient1, ingredient2);
        Step step1 = new Step();
        step1.setVideoURL("https://www.youtube.com/watch?v=zLRTISKN9Zk");
        step1.setShortDescription("A very long step");
        Step step2 = new Step();
        step2.setVideoURL("https://www.youtube.com/watch?v=zLRTISKN9Zk");
        step2.setShortDescription("A very long step");
        List<Step> steps = new ArrayList<>();
        Collections.addAll(steps, step1, step2);
        Recipe recipe = new Recipe();
        recipe.setName("Recipe Name");
        recipe.setServings(5);
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);
        mockRecipes.add(recipe);
    }

    @Before
    public void setup() {
        Intents.init();

    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void onLoad_activityDisplaysIngredients() {
        onView(withId(R.id.recipe_details_ingredients)).check(matches(isCompletelyDisplayed()));
    }

}
