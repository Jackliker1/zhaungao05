package com.bawei.classify.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.bawei.classify.BR;
import com.bawei.classify.R;
import com.bawei.classify.databinding.ActivityDetailsPageBinding;
import com.bawei.mvvm.view.BaseActivity;
import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.database.MySql;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class DetailsPageActivity extends MVVMBaseActivity<BaseViewModel, ActivityDetailsPageBinding> {

    private String url;
    private String title;
    private String price;
    private int index = 0;
    private WebView mWebView;

    @Override
    protected void prepareSetValues(HashMap mMap) {
        mMap.put(BR.ownerDetails, this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details_page;
    }

    @Override
    protected void initEvn() {

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        price = intent.getStringExtra("price");

        mWebView = findViewById(R.id.webView);
        mWebView.loadUrl("file:android_asset/DetailsPage.html");
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mWebView.loadUrl("javascript:settingBanner( '" + url +"')");
                mWebView.loadUrl("javascript:settingTitle( '" + title +"')");
                mWebView.loadUrl("javascript:settingPrice( '" + price +"')");
                setBack();
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        });

        mWebView.addJavascriptInterface(this,"MyAndroid");

    }

    public void addShopCar(View view){

        MySql mySql = new MySql(this,"ShopCar.db",null,1);
        SQLiteDatabase db = mySql.getReadableDatabase();
        db.execSQL("insert into goods values(?,?,?)",new Object[]{title,url,price});
        showToast("添加成功");

    }

    public void setBack(){

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.wallpaper14);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] bytes = stream.toByteArray();
                String s = Base64.encodeToString(bytes, Base64.DEFAULT);
                mWebView.loadUrl("javascript:setBanner('" + s + "')");
            }
        });

    }

    @JavascriptInterface
    public void goBack(){
        finish();
    }

}
