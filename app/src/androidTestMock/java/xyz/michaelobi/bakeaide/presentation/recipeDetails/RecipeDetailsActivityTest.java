package xyz.michaelobi.bakeaide.presentation.recipeDetails;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import xyz.michaelobi.bakeaide.R;
import xyz.michaelobi.bakeaide.presentation.recipeList.RecipeListActivity;

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

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> activityTestRule = new ActivityTestRule<>(RecipeDetailsActivity.class);

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
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rv_ingredients)).check(matches(isCompletelyDisplayed()));
    }

}
