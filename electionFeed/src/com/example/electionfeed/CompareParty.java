package com.example.electionfeed;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CompareParty extends Activity {
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_view);
		WebView webview = (WebView)findViewById(R.id.webView1);
		webview.setWebViewClient(new CustomWebViewClient());
		//webview.loadUrl("http://google.com");
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		String param = "[{'partyname':'Congress','percentage':20},{'partyname':'BJP','percentage':50},{'partyname':'APP','percentage':30}];";
		String html = Constant.getInitialString(param) + Constant.getSecondString("#positive") + Constant.getSecondString("#negative") + Constant.getSecondString("#neutral") + Constant.getThirdString(); 		
		webview.loadDataWithBaseURL("", html, "text/html", "UTF-8", "");
	}

}
