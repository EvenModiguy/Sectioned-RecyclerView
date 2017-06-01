package zhukic.sample;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import zhukic.sectionedrecyclerview.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.et_name), isDisplayed()));
        appCompatEditText.perform(replaceText("A"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.et_genre), isDisplayed()));
        appCompatEditText2.perform(replaceText("genre"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.et_year), isDisplayed()));
        appCompatEditText3.perform(replaceText("11111"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        withParent(allOf(withId(R.id.buttonPanel),
                                withParent(withId(R.id.parentPanel)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.movieName), withText("A"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("A")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.movieName), withText("A Beautiful Mind"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        2),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("A Beautiful Mind")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.subheaderText), withText("A"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("A")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.subheaderText), withText("B"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        3),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("B")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.subheaderText), withText("B"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        3),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("B")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}