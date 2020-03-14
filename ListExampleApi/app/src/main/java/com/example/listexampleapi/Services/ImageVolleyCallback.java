package com.example.listexampleapi.Services;

import android.graphics.Bitmap;

public interface ImageVolleyCallback {
    void onImageSuccess(Bitmap bitmap);
    void onImageFail();
}
