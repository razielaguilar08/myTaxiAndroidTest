package com.mytaxi.android_demo;

import android.Manifest;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;

import com.mytaxi.android_demo.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyTaxiAndroidTest {

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION);

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mActivity = null;
    private String username = "whiteelephant261";
    private String password = "video1";
    private String firstSearch = "Sa";
    private String secondSearch = "Sarah Friedrich";

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void t1_useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
    }

    @Test
    public void t2_myTaxiLoginTest()  {

        //Login with provided credentials
        onView(ViewMatchers.withId(R.id.edt_username)).perform(typeText(username));
        onView(ViewMatchers.withId(R.id.edt_password)).perform(typeText(password));

        //perform button click
        onView(ViewMatchers.withId(R.id.btn_login)).perform(doubleClick());

    }

    @Test
    public void t3_myTaxiCallTest() throws InterruptedException {

        Thread.sleep(10000);
        //typing the word Sa
        onView((ViewMatchers.withId(R.id.textSearch))).perform(typeText(firstSearch));

        Thread.sleep(10000);

        //Searching and clicking on Sarah Friedrich
        onView(withText(secondSearch)).inRoot(RootMatchers.withDecorView(not(is(mActivity.getWindow().
                getDecorView())))).perform(scrollTo()).perform(doubleClick());

        //Clicking on Call Button
        onView(withId(R.id.fab)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
    }
}
