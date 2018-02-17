package com.EgyPoint.Electronic.Tests.Activities;

import android.content.Intent;

import com.EgyPoint.Electronic.Tests.R;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class Splash  extends AwesomeSplash {
    public boolean isFirstStart;

    //DO NOT OVERRIDE onCreate()!
    //if you need to start some services do it in initSplash()!
    @Override
    public void initSplash(ConfigSplash configSplash) {

   /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        // configSplash.setRevealFlagX(MediaBrowserCompat.MediaItem.Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        // configSplash.setRevealFlagY(MediaBrowserCompat.MediaItem.Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher_round); //or any other drawable
        configSplash.setAnimLogoSplashDuration(4000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);
        //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
        //Customize Path
        //  configSplash.setPathSplash(Constants.lo); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.colorPrimary); //path object filling color


        //Customize Title
     /*   configSplash.setTitleSplash("  semicolonsoft               " +
                "للحلول البرمجية والتقنية\n");
        configSplash.setTitleTextColor(R.color.colorAccent);
         configSplash.setTitleTextSize(22f); //float value
          configSplash.setAnimTitleDuration(3000);
         configSplash.setAnimTitleTechnique(Techniques.FlipInX);
*/
        //  configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {

        Intent i =new Intent(this,LoginActivity.class);
        startActivity(i);


        //transit to another activity here
        //or do whatever you want
    }

}