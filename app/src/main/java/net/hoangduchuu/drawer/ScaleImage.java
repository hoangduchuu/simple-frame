package net.hoangduchuu.drawer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by hoang on 9/7/17.
 */


public class ScaleImage {
    private int drawableImage;
    private Context mContext;

    public ScaleImage(Context mContext, int drawableImage) {
        this.mContext = mContext;
        this.drawableImage = drawableImage;
    }

    public Bitmap getBitmap(Bitmap bitmap) {
        Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize( size );
        bitmap = Bitmap.createScaledBitmap( BitmapFactory.decodeResource(
                mContext.getResources(), drawableImage ), size.x, size.y, true );

        return bitmap;
    }
}