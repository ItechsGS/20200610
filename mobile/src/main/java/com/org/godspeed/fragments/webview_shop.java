package com.org.godspeed.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.org.godspeed.R;
import com.org.godspeed.utility.GodSpeedInterface;
import com.org.godspeed.utility.UtilityClass;

import static com.org.godspeed.utility.UtilityClass.hide;

public class webview_shop extends Fragment implements GodSpeedInterface {

    Context context;
    private WebView webView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_shop_fragment, container, false);
        context = getActivity();
        webView = view.findViewById(R.id.shop_web);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    UtilityClass.showWaitDialog(new Dialog(context), context);
                    view.getSettings().setJavaScriptEnabled(true);
                }
                if (progress == 100) {
                    hide();

                    //progressDialog.dismiss();
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100) {
                    UtilityClass.showWaitDialog(new Dialog(context), context);
                    view.getSettings().setJavaScriptEnabled(true);
                }
                if (progress == 100) {
                    hide();

                    //progressDialog.dismiss();
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("https://godspeed.org/shop/");
        return view;
    }


    @Override
    public void ApiResponse(String result) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
