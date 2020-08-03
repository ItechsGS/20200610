package com.org.godspeed.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;


public class ScreenshotUtils {

    Context context;

    /*  Method which will return Bitmap after taking screenshot. We have to pass the view which we want to take screenshot.  */
    public static Bitmap getScreenShot(View view, Bitmap watermark) {

        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);


        int w, h;
        Canvas c;
        Paint paint;
        Matrix matrix;
        float scale;
        RectF r;
        w = bitmap.getWidth();
        h = bitmap.getHeight();
        // Create the new bitmap
        // bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG);
        // Copy the original bitmap into the new one
        c = new Canvas(bitmap);
        c.drawBitmap(bitmap, 0, 0, paint);
        // Load the watermark

        // Scale the watermark to be approximately 40% of the source image height
        scale = (float) (((float) h * 0.10) / (float) watermark.getHeight());
        // Create the matrix
        matrix = new Matrix();
        matrix.postScale(scale, scale);
        // Determine the post-scaled size of the watermark
        r = new RectF(0, 0, watermark.getWidth(), watermark.getHeight());
        matrix.mapRect(r);
        // Move the watermark to the bottom right corner
        matrix.postTranslate(w - r.width(), h - r.height());
        // Draw the watermark
        //c.drawBitmap(watermark, matrix, paint);
        // Free up the bitmap memory
        watermark.recycle();

        return bitmap;
    }


    /*  Create Directory where screenshot will save for sharing screenshot  */
    public static File getMainDirectoryName(Context context) {
        //Here we will use getExternalFilesDir and inside that we will make our Demo folder
        //benefit of getExternalFilesDir is that whenever the app uninstalls the images will get deleted automatically.
        File mainDir = new File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Demo");

        //If File is not present create directory
        if (!mainDir.exists()) {
            if (mainDir.mkdir())
                Log.e("Create Directory", "Main Directory Created : " + mainDir);
        }
        return mainDir;
    }

    /*  Store taken screenshot into above created path  */
    public static File store(Bitmap bm, String fileName, File saveFilePath) {
        File dir = new File(saveFilePath.getAbsolutePath());
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(saveFilePath.getAbsolutePath(), fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}