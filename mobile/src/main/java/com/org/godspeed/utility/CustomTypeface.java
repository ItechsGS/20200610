package com.org.godspeed.utility;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Dharm on 7/29/2017.
 */
public class CustomTypeface {

    public static Typeface load_AGENCYB_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/AGENCYB.TTF");

        return font;

    }

    public static Typeface load_AGENCYR_Fonts(Context context) {
        Typeface font = null;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/AGENCYR.TTF");
        return font;

    }

    public static Typeface load_bebasneue_regular_Fonts(Context context) {
        Typeface font = null;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/bebasneue_regular.otf");
        return font;

    }

    public static Typeface load_Montserrat_Black_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Black.otf");
        return font;

    }

    public static Typeface load_steelfish_regular_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/steelfish_regular.ttf");
        return font;

    }


    public static Typeface load_Montserrat_Bold_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Bold.otf");
        return font;

    }

    public static Typeface load_Montserrat_ExtraBold_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-ExtraBold.otf");
        return font;

    }

    public static Typeface load_Montserrat_Light_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Light.otf");
        return font;

    }

    public static Typeface load_Montserrat_Regular_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-Regular.otf");
        return font;

    }

    public static Typeface load_Montserrat_Semibold_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-SemiBold.otf");
        return font;

    }

    public static Typeface load_Montserrat_UltraLight_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/Montserrat-UltraLight.otf");
        return font;

    }


    public static Typeface load_Digital_Normal_Fonts(Context context) {
        Typeface font = null;
        font = Typeface.createFromAsset(context.getAssets(), "fonts/digital-7.ttf");
        return font;

    }


}
