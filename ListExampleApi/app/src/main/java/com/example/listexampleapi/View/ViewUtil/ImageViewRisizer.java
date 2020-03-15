package com.example.listexampleapi.View.ViewUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.listexampleapi.R;

import java.util.NoSuchElementException;

public class ImageViewRisizer {
    private Context context;

    public ImageViewRisizer(Context context){
        this.context = context;
    }

    public void scaleImage(ImageView view, int displayWidth) throws NoSuchElementException {
        Bitmap bitmap = null;

        try {
            Drawable drawing = view.getDrawable();
            bitmap = ((BitmapDrawable) drawing).getBitmap();
        } catch (NullPointerException e) {
            throw new NoSuchElementException(context.getResources().getString(R.string.cant_find_bitmap_drawable));
        } catch (ClassCastException e) {

        }

        int width = 0;

        try {
            width = bitmap.getWidth();
        } catch (NullPointerException e) {
            throw new NoSuchElementException(context.getResources().getString(R.string.cant_find_bitmap_drawable));
        }

        int height = bitmap.getHeight();
        int boundingHeigth = dpToPx(250);
        int boundingWidth = dpToPx(displayWidth);

        float xScale = ((float) boundingWidth) / width;
        float yScale = ((float) boundingHeigth) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = scaledBitmap.getWidth();
        height = scaledBitmap.getHeight();
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        view.setImageDrawable(result);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    private int dpToPx(int dp) {
        float density = this.context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }
}
