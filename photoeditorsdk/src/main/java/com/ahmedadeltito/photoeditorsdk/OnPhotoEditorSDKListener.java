package com.ahmedadeltito.photoeditorsdk;

import android.graphics.Bitmap;

/**
 * Created by Ahmed Adel on 02/06/2017.
 */

public interface OnPhotoEditorSDKListener {

    void onEditTextChangeListener(String text, int colorCode, Bitmap bitmap, float x, float y);

    void onAddViewListener(ViewType viewType, int numberOfAddedViews);

    void onRemoveViewListener(int numberOfAddedViews);

    void onStartViewChangeListener(ViewType viewType);

    void onStopViewChangeListener(ViewType viewType);

}
