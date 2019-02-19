package com.yoshi1125hisa.camera_android;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final static int RESULT_CAMERA = 1001;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.image_view);

        Button cameraButton = findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RESULT_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_CAMERA) {
            Bitmap bitmap;
            // cancel
            if (data.getExtras() == null) {
                Log.d("error", "cancel ?");
                return;
            } else {
                bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    int bmpWidth = bitmap.getWidth();
                    int bmpHeight = bitmap.getHeight();
                    Log.d("message", String.format("w= %d", bmpWidth));
                    Log.d("message", String.format("h= %d", bmpHeight));
                }
            }

            imageView.setImageBitmap(bitmap);
        }
    }
}