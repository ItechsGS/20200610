package com.org.godspeed.allOtherClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.org.godspeed.R;
import com.org.godspeed.utility.CustomTypeface;

public class FileCaseWebViewScreen extends Activity {

    private WebView webViewFileCase;
    private ProgressDialog pd;
    private TextView textViewScreenName, textViewFromDateText, textViewFromDate, textViewPrintFilecase, textViewToDateText, textViewToDate, textViewDownloadFilecase;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_filecase_webview_screen);
        context = this;
        initializeTextViewFields();
        Log.e("Screen", "Filecase WebView");
        webViewFileCase = findViewById(R.id.webViewFileCase);
        webViewFileCase.getSettings().setJavaScriptEnabled(true);
        webViewFileCase.getSettings().setUseWideViewPort(true);
        webViewFileCase.getSettings().setBuiltInZoomControls(true);
        webViewFileCase.getSettings().setLightTouchEnabled(true);
        webViewFileCase.setBackgroundColor(Color.parseColor("#00000000"));
        webViewFileCase.setWebViewClient(new MyWebViewClient());
        webViewFileCase.loadUrl("http://jitendrasinghbhati.me/gym/file_case.php");

        pd = new ProgressDialog(this);
        pd.setTitle(getResources().getString(R.string.app_name));
        pd.setMessage("Please wait...");
        pd.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.ltr_anim, R.anim.rtl_anim);
    }

    private void initializeTextViewFields() {

        textViewScreenName = findViewById(R.id.textViewScreenName);
        textViewScreenName.setTypeface(CustomTypeface.load_AGENCYB_Fonts(context));

        textViewFromDateText = findViewById(R.id.textViewFromDateText);
        textViewFromDateText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewFromDate = findViewById(R.id.textViewFromDate);
        textViewFromDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewPrintFilecase = findViewById(R.id.textViewPrintFilecase);
        textViewPrintFilecase.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewToDateText = findViewById(R.id.textViewToDateText);
        textViewToDateText.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewToDate = findViewById(R.id.textViewToDate);
        textViewToDate.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));

        textViewDownloadFilecase = findViewById(R.id.textViewDownloadFilecase);
        textViewDownloadFilecase.setTypeface(CustomTypeface.load_AGENCYR_Fonts(context));
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            if (pd != null)
                pd.dismiss();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }
    }


}
