package com.honestwalker.androidutils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {
	
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) { 
        int width = bitmap.getWidth(); 
        int height = bitmap.getHeight(); 
        Matrix matrix = new Matrix(); 
        float scaleWidht = ((float) w / width); 
        float scaleHeight = ((float) h / height); 
        matrix.postScale(scaleWidht, scaleHeight); 
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, 
                matrix, true); 
        return newbmp; 
    }
	
	public static Bitmap zoomBitmap(Bitmap bitmap,double zoomLevel) {
		int width = bitmap.getWidth(); 
        int height = bitmap.getHeight(); 
        Matrix matrix = new Matrix(); 
        float scaleWidht = ((float) (width / zoomLevel) / width); 
        float scaleHeight = ((float) (height / zoomLevel) / height); 
        matrix.postScale(scaleWidht, scaleHeight); 
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, 
                matrix, true); 
        return newbmp; 
	}

}
