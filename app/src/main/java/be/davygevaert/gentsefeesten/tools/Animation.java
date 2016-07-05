package be.davygevaert.gentsefeesten.tools;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import be.davygevaert.gentsefeesten.constanten.Constants;

/**
 * Created by Davy on 16/06/2016.
 */
public class Animation {
    public static void setUpAnimation(Constants.AnimType type, AppCompatActivity appCompatActivity) {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // start animatie
            switch (type) {
                case ExplodeJava: { // Explosie-effect via Java-code
                    Explode enterTransition = new Explode();
                    enterTransition.setDuration(1000);
                    appCompatActivity.getWindow().setEnterTransition(enterTransition);
                    break;
                }
                case SlideJava: { // Slide-effect via Java-code
                    Slide enterAnimation = new Slide();
                    enterAnimation.setSlideEdge(Gravity.RIGHT);
                    enterAnimation.setDuration(1000);
                    appCompatActivity.getWindow().setEnterTransition(enterAnimation);
                    break;
                }
                case FadeJava: { // Fade-effect via Java-code
                    Fade enterAnimation = new Fade();
                    enterAnimation.setDuration(1000);
                    appCompatActivity.getWindow().setEnterTransition(enterAnimation);
                    break;
                }
            }
        }
    }

    public static void startActivity_With_Animation(Intent intent, Context context, Constants.AnimType type) {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // start intent met animatie material design
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
            // intent.putExtra(Constants.KEY_TYPE, Constants.AnimType.ExplodeJava);
            intent.putExtra(Constants.KEY_TYPE, type);
            context.startActivity(intent, options.toBundle());
        } else {
            // start intent zonder animatie material design , voor pre-Lollipop toestellen zoals JellyBean, Cream, KitKat
            context.startActivity(intent);
        }
    }
}

