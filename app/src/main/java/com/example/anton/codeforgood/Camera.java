package com.example.anton.codeforgood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;



public class Camera extends Activity {

    private static final int REQUEST_CODE = 1;
    private Button button_1;
    public int TAKE_PICTURE = 1;
    private ImageView image_view;
    private static final int CAMERA_REQUEST = 1888;
    String fileName = "capturedImage.jpg";
    private static Uri mCapturedImageURI;
    private String selectedImagePath;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        button_1 = (Button) findViewById(R.id.button1);

        button_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
                String newImageName = "food.jpg";
                File f = new File(Environment.getExternalStorageDirectory()
                        + File.separator + newImageName);
                if(!f.exists()){
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        FileOutputStream fo = new FileOutputStream(f.getAbsoluteFile());

                        fo.write(bytes.toByteArray());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else
                {
                    f.delete();
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        FileOutputStream fo = new FileOutputStream(f.getAbsoluteFile());

                        fo.write(bytes.toByteArray());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //write the bytes in file


                Intent cameraIntent = new Intent(this, PictureInfo.class);
                startActivity(cameraIntent);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}