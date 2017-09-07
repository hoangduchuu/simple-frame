package net.hoangduchuu.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * Created by hoang on 9/6/17.
 */

public class Yamaha {

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
        String fname = "RedBean-Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Log.d("huudeptrai", "chay vo toi close cmnr");
            Toast.makeText(context, "da luu hinh anh" + root + myDir.toString() + "/" + fname, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("huudeptrai", "loi cmnr");

        }
    }


}
