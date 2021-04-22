package com.Mitrakarte_ME.meappmintrakarte.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.Mitrakarte_ME.meappmintrakarte.R;

public class KeyAccount_OrderInvoiceActivity extends AppCompatActivity {

    WebView mWebView;
    String pdf_url;
    String recv_order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_account__order_invoice);

        //Todo: Getting  Order Id
        Intent intent = getIntent();
        recv_order_id = intent.getStringExtra("send_order_id");


        mWebView = findViewById(R.id.webview_order_invoice_k);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);

        pdf_url = "http://mitrakart.com/crm/Orders/printkeyaccountinvoice/";
        pdf_url = pdf_url + recv_order_id;


        //Todo: opening in drive
        /*mWebView.loadUrl("http://docs.google.com/viewer?url="+pdf_url);*/


        //todo: prevent redirect to chrome
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String loadUrl) {
                view.loadUrl(loadUrl);
                return false;
            }

        });


        mWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String pdf_url) {
                mWebView.loadUrl("javascript:(function() { " +
                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
            }
        });

        //https://docs.google.com/viewerng/viewer?embedded=true&url=
        mWebView.loadUrl("https://docs.google.com/viewerng/viewer?embedded=true&url="+pdf_url);
        System.out.println("pdf_url is " +pdf_url);

    }
}