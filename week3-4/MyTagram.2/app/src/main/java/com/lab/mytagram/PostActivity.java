package com.lab.mytagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    ImageView imageView;
    EditText txtView;
    static final int CAPTURE_IMAGE = 0;
    ImageButton btnOk;
    ImageButton btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageView = findViewById(R.id.imageView);
        txtView = findViewById(R.id.txtMessage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                /*if (intent.resolveActivity(getPackageManager()) != null) {
                    Log.w("null checker", "not null");
                    startActivityForResult(intent, CAPTURE_IMAGE);
                }else Log.w("null checker", intent.resolveActivity(getPackageManager()).toString());*/
                startActivityForResult(intent, CAPTURE_IMAGE);
            }
        });

        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("msg", txtView.getText());
                bundle.putParcelable("bitmap", ((BitmapDrawable)imageView.getDrawable()).getBitmap());

                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE && resultCode == Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(image);
        }
    }
}