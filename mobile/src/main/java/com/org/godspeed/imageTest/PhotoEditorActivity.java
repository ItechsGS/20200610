package com.org.godspeed.imageTest;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ahmedadeltito.photoeditorsdk.BrushDrawingView;
import com.ahmedadeltito.photoeditorsdk.OnPhotoEditorSDKListener;
import com.ahmedadeltito.photoeditorsdk.PhotoEditorSDK;
import com.ahmedadeltito.photoeditorsdk.ViewType;
import com.org.godspeed.R;
import com.org.godspeed.imageTest.widget.SlidingUpPanelLayout;
import com.viewpagerindicator.PageIndicator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.org.godspeed.allOtherClasses.Session_summery.IMAGE_PATH_FOR_SHARE;
import static com.org.godspeed.utility.UtilityClass.hide;
import static com.org.godspeed.utility.UtilityClass.showWaitDialog;

public class PhotoEditorActivity extends MediaActivity implements View.OnClickListener, OnPhotoEditorSDKListener {

    private final String TAG = "PhotoEditorActivity";
    PixelGridView pixelGridView;
    private RelativeLayout parentImageRelativeLayout, parent_image_rx;
    private RecyclerView drawingViewColorPickerRecyclerView;
    private TextView undoTextView, undoTextTextView, doneDrawingTextView, eraseDrawingTextView;
    private SlidingUpPanelLayout mLayout;
    private Typeface emojiFont;
    private View topShadow;
    private RelativeLayout topShadowRelativeLayout;
    private View bottomShadow;
    private RelativeLayout bottomShadowRelativeLayout;
    private ArrayList<Integer> colorPickerColors;
    private int colorCodeTextView = -1;
    private PhotoEditorSDK photoEditorSDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_photo_editor);

        getSupportActionBar().hide();
        selectedImagePath = getIntent().getExtras().getString("selectedImagePath");
        SetDATA();
        colorPickerColors = new ArrayList<>();
        colorPickerColors.add(getResources().getColor(R.color.black));
        colorPickerColors.add(getResources().getColor(R.color.blue_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.brown_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.green_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.orange_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.red_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.red_orange_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.sky_blue_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.violet_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.white));
        colorPickerColors.add(getResources().getColor(R.color.yellow_color_picker));
        colorPickerColors.add(getResources().getColor(R.color.yellow_green_color_picker));

    }

    private void SetDATA() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
        pixelGridView = new PixelGridView(this);
        Typeface newFont = Typeface.createFromAsset(getAssets(), "Eventtus-Icons.ttf");
        emojiFont = Typeface.createFromAsset(getAssets(), "emojione-android.ttf");

        BrushDrawingView brushDrawingView = findViewById(R.id.drawing_view);
        drawingViewColorPickerRecyclerView = findViewById(R.id.drawing_view_color_picker_recycler_view);
        parentImageRelativeLayout = findViewById(R.id.parent_image_rl);
        TextView closeTextView = findViewById(R.id.close_tv);
        TextView addTextView = findViewById(R.id.add_text_tv);
        TextView addPencil = findViewById(R.id.add_pencil_tv);
        RelativeLayout deleteRelativeLayout = findViewById(R.id.delete_rl);
        TextView deleteTextView = findViewById(R.id.delete_tv);
        TextView addImageEmojiTextView = findViewById(R.id.add_image_emoji_tv);
        ImageView rotate_tv = findViewById(R.id.rotate_tv);
        TextView saveTextView = findViewById(R.id.save_tv);
        TextView saveTextTextView = findViewById(R.id.save_text_tv);
        undoTextView = findViewById(R.id.undo_tv);
        undoTextTextView = findViewById(R.id.undo_text_tv);
        doneDrawingTextView = findViewById(R.id.done_drawing_tv);
        eraseDrawingTextView = findViewById(R.id.erase_drawing_tv);
        TextView clearAllTextView = findViewById(R.id.clear_all_tv);
        TextView clearAllTextTextView = findViewById(R.id.clear_all_text_tv);
        TextView goToNextTextView = findViewById(R.id.go_to_next_screen_tv);
        ImageView photoEditImageView = findViewById(R.id.photo_edit_iv);
        mLayout = findViewById(R.id.sliding_layout);
        topShadow = findViewById(R.id.top_shadow);
        topShadowRelativeLayout = findViewById(R.id.top_parent_rl);
        bottomShadow = findViewById(R.id.bottom_shadow);
        bottomShadowRelativeLayout = findViewById(R.id.bottom_parent_rl);

        ViewPager pager = findViewById(R.id.image_emoji_view_pager);
        PageIndicator indicator = findViewById(R.id.image_emoji_indicator);

        photoEditImageView.setImageBitmap(bitmap);

        closeTextView.setTypeface(newFont);
        addTextView.setTypeface(newFont);
        addPencil.setTypeface(newFont);
        addImageEmojiTextView.setTypeface(newFont);

        saveTextView.setTypeface(newFont);

        undoTextView.setTypeface(newFont);
        clearAllTextView.setTypeface(newFont);
        goToNextTextView.setTypeface(newFont);
        deleteTextView.setTypeface(newFont);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(5);
        indicator.setViewPager(pager);

        photoEditorSDK = new PhotoEditorSDK.PhotoEditorSDKBuilder(PhotoEditorActivity.this)
                .parentView(parentImageRelativeLayout) // add parent image view
                .childView(photoEditImageView) // add the desired image view
                .deleteView(deleteRelativeLayout) // add the deleted view that will appear during the movement of the views
                .brushDrawingView(brushDrawingView) // add the brush drawing view that is responsible for drawing on the image view
                .buildPhotoEditorSDK(); // build photo editor sdk
        photoEditorSDK.setOnPhotoEditorSDKListener(this);


        closeTextView.setOnClickListener(this);
        addImageEmojiTextView.setOnClickListener(this);
        rotate_tv.setOnClickListener(this);
        addTextView.setOnClickListener(this);
        addPencil.setOnClickListener(this);
        saveTextView.setOnClickListener(this);
        saveTextTextView.setOnClickListener(this);
        undoTextView.setOnClickListener(this);
        undoTextTextView.setOnClickListener(this);
        doneDrawingTextView.setOnClickListener(this);
        eraseDrawingTextView.setOnClickListener(this);
        clearAllTextView.setOnClickListener(this);
        clearAllTextTextView.setOnClickListener(this);
        goToNextTextView.setOnClickListener(this);


    }

    @Override
    protected void onPhotoTaken() {
        Log.d(TAG, "onPhotoTaken: ");
        SetDATA();
    }

    private boolean stringIsNotEmpty(String string) {
        if (string != null && !string.equals("null")) {
            return !string.trim().equals("");
        }
        return false;
    }

    public void addEmoji(String emojiName) {
        photoEditorSDK.addEmoji(emojiName, emojiFont);
        if (mLayout != null)
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

    }

    public void addImage(Bitmap image, String text, int colorPallate) {
        String textX = "";
        if (text == null || text.equalsIgnoreCase("")) {
            textX = "Test Text";
        } else {
            textX = text;
        }
        photoEditorSDK.addTextWithImage(textX, colorPallate, image);
        if (mLayout != null)
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    private void addText(String text, int colorCodeTextView) {
        photoEditorSDK.addText(text, colorCodeTextView);
    }

    private void clearAllViews() {
        photoEditorSDK.clearAllViews();
    }

    private void undoViews() {
        photoEditorSDK.viewUndo();
    }

    private void eraseDrawing() {
        photoEditorSDK.brushEraser();
    }

    private void openAddTextPopupWindow(String text, int colorCode, Bitmap bitmap) {
        colorCodeTextView = colorCode;

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View addTextPopupWindowRootView = inflater.inflate(R.layout.add_text_popup_window, null);
        final EditText addTextEditText = addTextPopupWindowRootView.findViewById(R.id.add_text_edit_text);
        addTextEditText.requestFocus();
        TextView addTextDoneTextView = addTextPopupWindowRootView.findViewById(R.id.add_text_done_tv);
        RecyclerView addTextColorPickerRecyclerView = addTextPopupWindowRootView.findViewById(R.id.add_text_color_picker_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(PhotoEditorActivity.this, LinearLayoutManager.HORIZONTAL, false);
        addTextColorPickerRecyclerView.setLayoutManager(layoutManager);
        addTextColorPickerRecyclerView.setHasFixedSize(true);
        ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(PhotoEditorActivity.this, colorPickerColors);
        colorPickerAdapter.setOnColorPickerClickListener(colorCode1 -> {
            addTextEditText.setTextColor(colorCode1);
            colorCodeTextView = colorCode1;
        });
        addTextColorPickerRecyclerView.setAdapter(colorPickerAdapter);
        if (stringIsNotEmpty(text)) {
            addTextEditText.setText(text);
            addTextEditText.setTextColor(colorCode == -1 ? getResources().getColor(R.color.white) : colorCode);
        }
        final PopupWindow pop = new PopupWindow(PhotoEditorActivity.this);
        pop.setContentView(addTextPopupWindowRootView);
        pop.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        pop.setFocusable(true);
        pop.setBackgroundDrawable(null);
        pop.showAtLocation(addTextPopupWindowRootView, Gravity.TOP, 0, 0);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        addTextDoneTextView.setOnClickListener(view -> {
            if (bitmap == null) {
                addText(addTextEditText.getText().toString(), colorCodeTextView);
            } else {
                addImage(bitmap, addTextEditText.getText().toString(), colorCodeTextView);
            }
            InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm1.hideSoftInputFromWindow(view.getWindowToken(), 0);
            pop.dismiss();
        });
    }

    private void updateView(int visibility) {
        topShadow.setVisibility(visibility);
        topShadowRelativeLayout.setVisibility(visibility);
        bottomShadow.setVisibility(visibility);
        bottomShadowRelativeLayout.setVisibility(visibility);
    }

    private void crop_and_rotate() {
        File file = new File(selectedImagePath);
        String path = file.getPath();
        Uri bmpUri = Uri.parse("file://" + path);

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(bmpUri, "image/*");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(new File(bmpUri.getPath()).getAbsolutePath(), options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;

        intent.putExtra("outputX", imageHeight);
        intent.putExtra("outputY", imageWidth);
        Log.d(TAG, "crop_and_rotate: " + imageHeight + "  " + imageWidth);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);

        intent.putExtra("return-data", false);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, bmpUri);


        startActivityForResult(intent, GALLERY_INTENT_CALLED);
    }

    private void updateBrushDrawingView(boolean brushDrawingMode) {
        photoEditorSDK.setBrushDrawingMode(brushDrawingMode);
        if (brushDrawingMode) {
            updateView(View.GONE);
            drawingViewColorPickerRecyclerView.setVisibility(View.VISIBLE);
            doneDrawingTextView.setVisibility(View.VISIBLE);
            eraseDrawingTextView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(PhotoEditorActivity.this, LinearLayoutManager.HORIZONTAL, false);
            drawingViewColorPickerRecyclerView.setLayoutManager(layoutManager);
            drawingViewColorPickerRecyclerView.setHasFixedSize(true);
            ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(PhotoEditorActivity.this, colorPickerColors);
            colorPickerAdapter.setOnColorPickerClickListener(colorCode -> photoEditorSDK.setBrushColor(colorCode));
            drawingViewColorPickerRecyclerView.setAdapter(colorPickerAdapter);
        } else {
            updateView(View.VISIBLE);
            drawingViewColorPickerRecyclerView.setVisibility(View.GONE);
            doneDrawingTextView.setVisibility(View.GONE);
            eraseDrawingTextView.setVisibility(View.GONE);
        }
    }

    private void returnBackWithSavedImage() {
        showWaitDialog(new Dialog(this), this);
        //updateView(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentImageRelativeLayout.setLayoutParams(layoutParams);
        new CountDownTimer(1000, 500) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageName = "IMG_" + timeStamp + ".jpg";
                Intent returnIntent = new Intent();
                returnIntent.putExtra("imagePath", photoEditorSDK.saveImage(getResources().getString(R.string.app_name_for_directory), imageName));
                setResult(Activity.RESULT_OK, returnIntent);

                Log.d(TAG, "onFinish: " + returnIntent.getStringExtra("imagePath"));
                onClickX(returnIntent.getStringExtra("imagePath"));

                //finish();

                //IMAGE_PATH_FOR_SHARE= "";
                // onClickX(returnIntent.getStringExtra("imagePath"));

            }
        }.start();
    }


    public void onClickX(String v) {

        IMAGE_PATH_FOR_SHARE = v;

        new Handler().postDelayed(() -> {
            hide();
            finish();

        }, 200);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.close_tv) {
            onBackPressed();
        } else if (v.getId() == R.id.add_image_emoji_tv) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        } else if (v.getId() == R.id.add_text_tv) {
            openAddTextPopupWindow("", -1, null);
        } else if (v.getId() == R.id.add_pencil_tv) {
            updateBrushDrawingView(true);
        } else if (v.getId() == R.id.done_drawing_tv) {
            updateBrushDrawingView(false);
        } else if (v.getId() == R.id.save_tv || v.getId() == R.id.save_text_tv) {
            returnBackWithSavedImage();
        } else if (v.getId() == R.id.clear_all_tv || v.getId() == R.id.clear_all_text_tv) {
            clearAllViews();
        } else if (v.getId() == R.id.undo_text_tv || v.getId() == R.id.undo_tv) {
            undoViews();
        } else if (v.getId() == R.id.erase_drawing_tv) {
            eraseDrawing();
        } else if (v.getId() == R.id.go_to_next_screen_tv) {
            returnBackWithSavedImage();
        } else if (v.getId() == R.id.rotate_tv) {


            crop_and_rotate();
        }
    }


    @Override
    public void onEditTextChangeListener(String text, int colorCode, Bitmap bitmap, float x, float y) {
        openAddTextPopupWindow(text, colorCode, bitmap);
    }


    @Override
    public void onAddViewListener(ViewType viewType, int numberOfAddedViews) {
        if (numberOfAddedViews > 0) {
            undoTextView.setVisibility(View.VISIBLE);
            undoTextTextView.setVisibility(View.VISIBLE);
        }
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onAddViewListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onAddViewListener");
                break;
            case IMAGE:
                Log.i("IMAGE", "onAddViewListener");
                break;
            case TEXT:
                Log.i("TEXT", "onAddViewListener");
                break;
        }
    }

    @Override
    public void onRemoveViewListener(int numberOfAddedViews) {
        Log.i(TAG, "onRemoveViewListener");
        if (numberOfAddedViews == 0) {
            undoTextView.setVisibility(View.GONE);
            undoTextTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStartViewChangeListener(ViewType viewType) {
        parentImageRelativeLayout.addView(pixelGridView);
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onStartViewChangeListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onStartViewChangeListener");
                break;
            case IMAGE:

                Log.i("IMAGE", "onStartViewChangeListener");
                break;
            case TEXT:
                Log.i("TEXT", "onStartViewChangeListener");
                break;
        }
    }

    @Override
    public void onStopViewChangeListener(ViewType viewType) {
        parentImageRelativeLayout.removeView(pixelGridView);
        switch (viewType) {
            case BRUSH_DRAWING:
                Log.i("BRUSH_DRAWING", "onStopViewChangeListener");
                break;
            case EMOJI:
                Log.i("EMOJI", "onStopViewChangeListener");
                break;
            case IMAGE:

                Log.i("IMAGE", "onStopViewChangeListener");
                break;
            case TEXT:
                Log.i("TEXT", "onStopViewChangeListener");
                break;
        }
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ImageFragment(); //ChildFragment1 at position 0
                case 1:
                    return new EmojiFragment(); //ChildFragment2 at position 1
            }
            return null; //does not happen
        }

        @Override
        public int getCount() {
            return 2; //three fragments
        }
    }


    public class PixelGridView extends View {
        private final float width;
        int horizontalGridCount = 5;
        private Drawable horiz;
        private Drawable vert;

        public PixelGridView(@NonNull Context context) {
            this(context, null);
        }

        public PixelGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            horiz = new ColorDrawable(Color.WHITE);
            horiz.setAlpha(140);
            vert = new ColorDrawable(Color.WHITE);
            vert.setAlpha(140);
            width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1.3f, context.getResources().getDisplayMetrics());
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            horiz.setBounds(left, 0, right, (int) width);
            vert.setBounds(0, top, (int) width, bottom);
        }

        private float getLinePosition(int lineNumber) {
            int lineCount = horizontalGridCount;
            return (1f / (lineCount + 1)) * (lineNumber + 1f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // drawTask.start();
            int count = horizontalGridCount;
            for (int n = 0; n < count; n++) {
                float pos = getLinePosition(n);
                // Draw horizontal line
                canvas.translate(0, pos * getHeight());
                horiz.draw(canvas);
                canvas.translate(0, -pos * getHeight());
                // Draw vertical line
                canvas.translate(pos * getWidth(), 0);
                vert.draw(canvas);
                canvas.translate(-pos * getWidth(), 0);
            }
            //drawTask.end(count);
        }
    }
}
