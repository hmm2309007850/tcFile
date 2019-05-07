package com.stu.stu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

public class PopupActivity extends AppCompatActivity {

    public WebView wbPopupactivity;
    public WebSettings webSettings;
    public String url;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadCallbackBelow;
    private Uri imageUri;
    private int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");

        initWebView();
    }

    private void initWebView() {

        wbPopupactivity = findViewById(R.id.wb_popupactivity);
        new WebViewTask().execute();

    }

    private class WebViewTask extends AsyncTask<Void, Void, Boolean> {

        String sessionCookie;
        CookieManager cookieManager;

        @Override
        protected void onPreExecute() {
            CookieSyncManager.createInstance(PopupActivity.this);
            cookieManager = CookieManager.getInstance();

            sessionCookie = Constant.INSTANCE.getSESSION();

            //sessionCookie = new PersistentConfig(getApplicationContext()).getCookieString();

            if (sessionCookie != null) {
                // delete old cookies
                cookieManager.removeSessionCookie();
            }
            super.onPreExecute();
        }

        protected Boolean doInBackground(Void... param) {
            // this is very important - THIS IS THE HACK
            SystemClock.sleep(1000);
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (sessionCookie != null) {
                cookieManager.setCookie(url,//SyncStateContract.Constants.ServerUrl.WEB_URL,
                        sessionCookie);
                CookieSyncManager.getInstance().sync();
            }

            webSettings = wbPopupactivity.getSettings();

            webSettings.setJavaScriptEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setSupportZoom(false);
            webSettings.setBuiltInZoomControls(false);
            webSettings.setDisplayZoomControls(false);//不显示webview缩放按钮
            webSettings.setPluginState(PluginState.ON);
            wbPopupactivity.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            wbPopupactivity.setScrollbarFadingEnabled(true);
            // 设置可以访问文件
            webSettings.setAllowFileAccess(true);

            wbPopupactivity.setWebChromeClient(new WebChromeClient() {

                @Override
                public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                    Log.e("WangJ", "运行方法 onShowFileChooser");
                    // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
                    mUploadCallbackAboveL = filePathCallback;
                    takePhoto();
                    return true;
                }
            });

            wbPopupactivity.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                    view.loadUrl(url);
                    Log.e("PopupActivity", "shouldOverrideKeyEvent");
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    Log.e("PopupActivity", "onPageStarted");
                    super.onPageStarted(view, url, favicon);
                }


            });

            wbPopupactivity.loadUrl(url);

            //ShowUrlView(Constants.ServerUrl.INDEX);
        }
    }

    /**
     * 调用相机
     */
    private void takePhoto() {
        // 指定拍照存储位置的方式调起相机
        String filePath = Environment.getExternalStorageDirectory() + File.separator
                + Environment.DIRECTORY_PICTURES + File.separator;
        String fileName = "IMG_" + DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        imageUri = Uri.fromFile(new File(filePath + fileName));

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(intent, REQUEST_CODE);


        // 选择图片（不包括相机拍照）,则不用成功后发刷新图库的广播
//        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//        i.addCategory(Intent.CATEGORY_OPENABLE);
//        i.setType("image/*");
//        startActivityForResult(Intent.createChooser(i, "Image Chooser"), REQUEST_CODE);

        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        Intent Photo = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooserIntent = Intent.createChooser(Photo, "Image Chooser");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});

        startActivityForResult(chooserIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            // 经过上边(1)、(2)两个赋值操作，此处即可根据其值是否为空来决定采用哪种处理方法
            if (mUploadCallbackBelow != null) {
                //chooseBelow(resultCode, data);
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            } else if (mUploadCallbackAboveL != null) {
                //chooseAbove(resultCode, data);
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "发生错误", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
