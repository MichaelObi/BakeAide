package xyz.michaelobi.bakeaide.presentation.recipeList;

import android.content.ComponentName;
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
import xyz.michaelobi.bakeaide.presentation.recipeDetails.RecipeDetailsActivity;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasType;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * BakeAide
 * Michael Obi
 * 23 06 2017 1:08 PM
 */

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {
    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void setup() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void onLoad_activityDisplaysRecyclerViewOfRecipes() {
        onView(withId(R.id.rv_recipes)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void onClickingRecipeCard_OpenRecipeDetails() {
        onView(withId(R.id.rv_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(RecipeDetailsActivity.class.getName()));
        intended(hasExtraWithKey("recipe"));

    }

}
