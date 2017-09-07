package net.hoangduchuu.drawer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    View viewFrame;
    ImageView ivBg, ivFrame;
    Button btnSelectFrame, btnSelectBg, btnSaveImage;

    final static int REQUEST_CODE_CAMERA_BG = 12;
    final static int REQUEST_CODE_CAMERA_FRAME = 22;
    ViewGroup mRoot;
    private int mXDelta;
    private int mYDelta;


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

        mRoot = (RelativeLayout) findViewById(R.id.content_main_relative_layout);

        ivFrame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) ivFrame.getLayoutParams();
                        mXDelta = x - lParams.leftMargin;
                        mYDelta = y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivFrame.getLayoutParams();
                        layoutParams.leftMargin = x - mXDelta;
                        layoutParams.topMargin = y - mYDelta;
                        layoutParams.rightMargin = -250;
                        layoutParams.bottomMargin = -250;
                        ivFrame.setLayoutParams(layoutParams);
                        break;
                }
                viewFrame.invalidate();
                return true;
            }
        });

    }


    private void save() {
        Bitmap bitmap = Yamaha.capture(viewFrame);
        if (bitmap != null) {
            Yamaha.saveImage(bitmap, getApplicationContext());
            Log.d("huudeptrai", "chay vo toi if roi");

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
                break;
            case R.id.btnSelectFrame:
                selectFrame();
                break;
            default:
        }
    }

    private void selectbg() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, REQUEST_CODE_CAMERA_BG);
    }

    private void selectFrame() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, REQUEST_CODE_CAMERA_FRAME);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_CAMERA_BG:
                if (requestCode == REQUEST_CODE_CAMERA_BG && resultCode == RESULT_OK && data != null) {
                    insertBg(data);
                }
                break;
            case REQUEST_CODE_CAMERA_FRAME:
                if (requestCode == REQUEST_CODE_CAMERA_FRAME && resultCode == RESULT_OK && data != null) {
                    insertFrame(data);
                }
                break;
            default:
                break;
        }


    }

    private void insertFrame(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        ivFrame.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        Log.d("huudeptrai", "finish insertFrame");
    }

    private void insertBg(Intent data) {
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        ivBg.setImageBitmap(bitmap);
        Log.d("huudeptrai", "finish insertBg");


    }

}
