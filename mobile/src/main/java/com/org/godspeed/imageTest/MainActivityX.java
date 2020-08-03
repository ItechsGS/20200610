package com.org.godspeed.imageTest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.org.godspeed.R;

public class MainActivityX extends MediaActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // openGallery();
    }

    public void openUserGallery(View view) {
        openGallery();
    }

    public void openUserCamera(View view) {
        startCameraActivity();
    }

    @Override
    protected void onPhotoTaken() {
        Intent intent = new Intent(MainActivityX.this, PhotoEditorActivity.class);
        intent.putExtra("selectedImagePath", selectedImagePath);
        startActivity(intent);

    }
}
