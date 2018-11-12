package net.mytestfragment.com.testfragment.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

//import com.jaeger.library.StatusBarUtil;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import net.mytestfragment.com.testfragment.R;
import net.mytestfragment.com.testfragment.utils.UIUtils;
import net.mytestfragment.com.testfragment.widget.CustomLoadView;

public class WebTencentActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webView;
    private LinearLayout parent;
    private CustomLoadView customLoadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        customLoadView = new CustomLoadView(this,null);
        customLoadView.show();
        delay();

        //initWebView();
    }

    Runnable runnable1 = new Runnable(){

        public void run() {

            switch (times){
                case 1:
                    customLoadView.loading();
                    break;
                case 2:
                    customLoadView.loadFail();
                    break;
                case 3:
                    customLoadView.loadSuccess();
                    break;
            }
            times++;
            if(times>3){
                new Handler().postDelayed(runnable2, 500);
            }else{
                delay();
            }


        }

    };

    Runnable runnable2 = new Runnable(){
        @Override
        public void run() {
            initWebView();
        }
    };

    private int times = 1;
    private void delay(){

        new Handler().postDelayed(runnable1, 2500);
    }

    private void initWebView() {

        setContentView(R.layout.activity_web);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        webView = (WebView) findViewById(R.id.webview);
        parent =  findViewById(R.id.parent);


        WebSettings settings = webView.getSettings();           //和系统webview一样
        settings.setJavaScriptEnabled(true);                    //支持Javascript 与js交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setAllowFileAccess(true);                      //设置可以访问文件
        settings.setSupportZoom(true);                          //支持缩放
        settings.setBuiltInZoomControls(true);                  //设置内置的缩放控件
        settings.setUseWideViewPort(true);                      //自适应屏幕
        settings.setSupportMultipleWindows(true);               //多窗口
        settings.setDefaultTextEncodingName("utf-8");            //设置编码格式
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);       //缓存模式
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//忽略SSL证书错误
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                return super.onJsAlert(webView, s, s1, jsResult);
            }

            @Override
            public void onReceivedTitle(WebView webView, String s) {
                super.onReceivedTitle(webView, s);
            }

            @Override
            public void onProgressChanged(WebView webView, int progress) {
                super.onProgressChanged(webView, progress);
                progressBar.setVisibility(View.VISIBLE);
                if(progress<100) {
                    progressBar.setProgress(progress); //设置进度条
                }else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

         //      webView.reload(); 刷新页面
        webView.loadUrl("http://baidu.com");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            finish();
        }
        return true;
    }
}