package net.hoangduchuu.drawer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hoang on 9/6/17.
 */

public class Yamaha {

    public static Bitmap capture(View layout) {
        Bitmap bitmap;
        layout.setDrawingCacheEnabled( true );
        layout.buildDrawingCache();
        bitmap = layout.getDrawingCache();
        return bitmap;
    }

    public static void saveImage(Bitmap finalBitmap, Context context, int quality) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File( root + "/RedFrame" );
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt( n );
        String fname = "RedBean-Image-" + n + ".jpg";
        File file = new File( myDir, fname );
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream( file );
            finalBitmap.compress( Bitmap.CompressFormat.JPEG, quality, out );
            out.flush();
            out.close();
            Log.d( "huudeptrai", "chay vo toi close cmnr" );
            String msg = "da luu hinh anh" + root + myDir.toString() + "/" + fname;
//            AlertDialog.Builder builder = new AlertDialog.Builder( context );
//            builder.setMessage( msg );
//            builder.show();
            Toast.makeText( context, msg, Toast.LENGTH_SHORT ).show();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d( "huudeptrai", "loi cmnr" );

        }
    }

    public static List<Icon> fakeList() {
        List<Icon> iconList = new ArrayList<>();
        iconList = new ArrayList<>();

        iconList.add( new Icon( R.drawable.dream ) );
        iconList.add( new Icon( R.drawable.two ) );
        iconList.add( new Icon( R.drawable.three ) );
        iconList.add( new Icon( R.drawable.four ) );
        iconList.add( new Icon( R.drawable.five ) );
        iconList.add( new Icon( R.drawable.six ) );
        iconList.add( new Icon( R.drawable.seven ) );
        iconList.add( new Icon( R.drawable.eight ) );
        iconList.add( new Icon( R.drawable.nine ) );
        iconList.add( new Icon( R.drawable.ten ) );
        iconList.add( new Icon( R.drawable.adore ) );
        iconList.add( new Icon( R.drawable.ah ) );
        iconList.add( new Icon( R.drawable.amazed ) );
        iconList.add( new Icon( R.drawable.angry ) );
        iconList.add( new Icon( R.drawable.bad ) );
        iconList.add( new Icon( R.drawable.baffle ) );
        iconList.add( new Icon( R.drawable.beat ) );
        iconList.add( new Icon( R.drawable.adore ) );
        iconList.add( new Icon( R.drawable.ah ) );
        iconList.add( new Icon( R.drawable.amazed ) );
        iconList.add( new Icon( R.drawable.angry ) );
        iconList.add( new Icon( R.drawable.bad ) );
        iconList.add( new Icon( R.drawable.baffle ) );
        iconList.add( new Icon( R.drawable.beat ) );
        iconList.add( new Icon( R.drawable.adore ) );
        iconList.add( new Icon( R.drawable.ah ) );
        iconList.add( new Icon( R.drawable.amazed ) );
        iconList.add( new Icon( R.drawable.angry ) );
        iconList.add( new Icon( R.drawable.bad ) );
        iconList.add( new Icon( R.drawable.baffle ) );
        iconList.add( new Icon( R.drawable.beat ) );
        return iconList;
    }


}
