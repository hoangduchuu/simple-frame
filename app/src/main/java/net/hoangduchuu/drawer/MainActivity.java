package net.hoangduchuu.drawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName().toString();
    View viewFrame;
    ImageView ivBg, ivFrame;
    Button btnSelectFrame, btnSelectBg, btnSaveImage;

    final static int REQUEST_CODE_CAMERA_BG = 12;
    final static int REQUEST_CODE_CAMERA_FRAME = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIds();

    }

    private void findViewByIds() {
        //initz view
        viewFrame = findViewById(R.id.viewFrame);
        ivBg = (ImageView) findViewById(R.id.ivBackground);
        ivFrame = (ImageView) findViewById(R.id.ivFrame);
        btnSelectFrame = (Button) findViewById(R.id.btnSelectFrame);
        btnSaveImage = (Button) findViewById(R.id.btnSaveImage);
        btnSelectBg = (Button) findViewById(R.id.btnSelectBackground);

        btnSaveImage.setOnClickListener(this);
        btnSelectBg.setOnClickListener(this);
        btnSelectFrame.setOnClickListener(this);

    }


    private void save() {
        Bitmap bitmap = Bitter.capture(viewFrame);
        if (bitmap != null) {
            Log.d("kaka", "chay vo toi if roi");
            Bitter.saveImage(bitmap, getApplicationContext());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveImage:
                save();
                break;
            case R.id.btnSelectBackground:
                selectbg();
                Toast.makeText(this, "btn select bg", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSelectFrame:
                selectFrame();
                Toast.makeText(this, "btn select frame", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "kich sangr", Toast.LENGTH_SHORT).show();
        }
    }

    private void selectbg() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CODE_CAMERA_FRAME);
    }

    private void selectFrame() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, REQUEST_CODE_CAMERA_BG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA_BG || resultCode == RESULT_OK || data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivBg.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_CAMERA_FRAME || resultCode == RESULT_OK || data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivFrame.setImageBitmap(bitmap);
        }


    }
}
