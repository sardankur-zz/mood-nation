package com.example.electionfeed;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CustomWebViewClient extends WebViewClient {
	
	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
         view.loadUrl(url);
         return true;
    }
	
    public void onPageFinished(WebView view, String url) {
         view.loadUrl("javascript:refreshPage()");
    }
    
}
