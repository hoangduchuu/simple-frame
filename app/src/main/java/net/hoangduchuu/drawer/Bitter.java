package net.hoangduchuu.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by hoang on 9/6/17.
 */

public class Bitter {
    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the viewFrame
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the viewFrame's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the viewFrame on the canvas
//        viewFrame.draw(canvas);
        //return the bitmap
        view.draw(canvas);
        return returnedBitmap;
    }

    public static Bitmap loadBitmapFromView(View v) {

        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Bitmap b = Bitmap.createBitmap(v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.draw(c);
        return b;

    }

    public static Bitmap bitmapCache(View view) {
        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        return view.getDrawingCache();
    }

    public static Bitmap extractBitmap(View iViewGroup) {

        int width = iViewGroup.getWidth();
        int height = iViewGroup.getWidth();

        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(createBitmap);

        iViewGroup.draw(canvas);

        return createBitmap;

    }


    public static Bitmap loadBitmapFromView2(View view) {

        // width measure spec
        int widthSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredWidth(), View.MeasureSpec.AT_MOST);
        // height measure spec
        int heightSpec = View.MeasureSpec.makeMeasureSpec(
                view.getMeasuredHeight(), View.MeasureSpec.AT_MOST);
        Log.d("measure: ", "" + widthSpec + ": " + heightSpec);
        // measure the viewFrame
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        // set the layout sizes
        view.layout(view.getLeft(), view.getTop(), view.getMeasuredWidth() + view.getLeft(), view.getMeasuredHeight() + view.getTop());
        // create the bitmap
        Log.d("viewFrame.getTop() ", "" + view.getTop());
        Log.d("viewFrame.getMeasuredWidth() ", "" + view.getMeasuredWidth());
        Log.d("viewFrame.getLeft() ", "" + view.getLeft());
        Log.d("viewFrame.getMeasuredHeight() ", "" + view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        // create a canvas used to get the viewFrame's image and draw it on the bitmap
        Canvas c = new Canvas(bitmap);
        // position the image inside the canvas
        c.translate(-view.getScrollX(), -view.getScrollY());
        // get the canvas
        view.draw(c);

        return bitmap;
    }

    public static Bitmap capture(View layout) {
        Bitmap bitmap;
        layout.setDrawingCacheEnabled(true);
        layout.buildDrawingCache();
        bitmap = layout.getDrawingCache();
        return bitmap;
    }

    public static void saveImage(Bitmap finalBitmap, Context context) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/RedFrame");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "RedBeanImage-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.d("kaka", "chay vo toi close cmnr");
            Toast.makeText(context, "da luu hinh anh", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("kaka", "dao");

        }
    }


}
