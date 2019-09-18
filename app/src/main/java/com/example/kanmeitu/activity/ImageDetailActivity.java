package com.example.kanmeitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.kanmeitu.R;
import com.example.kanmeitu.domain.Image;
import com.example.kanmeitu.util.Constants;
import com.example.kanmeitu.util.ImageUtil;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        PhotoView pv =findViewById(R.id.pv);

        String uri = getIntent().getStringExtra(Constants.ID);
        ImageUtil.show(this,pv,uri);
    }
}
