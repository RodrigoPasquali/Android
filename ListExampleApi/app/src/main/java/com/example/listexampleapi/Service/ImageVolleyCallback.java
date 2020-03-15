package com.example.listexampleapi.Service;

import android.graphics.Bitmap;

public interface ImageVolleyCallback {
    void onImageSuccess(Bitmap bitmap);
    void onImageFail();
}
