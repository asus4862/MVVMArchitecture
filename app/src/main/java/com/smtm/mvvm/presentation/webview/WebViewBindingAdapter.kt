package com.smtm.mvvm.presentation.webview

import android.text.TextUtils
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import androidx.databinding.BindingAdapter
import android.webkit.WebView
import android.webkit.WebViewClient

@BindingAdapter("setWebViewClient")
fun setWebViewClient(webView: WebView, client: WebViewClient) {
    webView.webViewClient = client
    WebView.setWebContentsDebuggingEnabled(false)
    webView.settings.javaScriptEnabled = true
    webView.settings.loadsImagesAutomatically = true
    webView.settings.useWideViewPort = true
    webView.settings.domStorageEnabled = true
}

@BindingAdapter("webViewUrl")
fun loadUrl(webView: WebView, url: String) {
    if (TextUtils.isEmpty(url)) {
        return;
    }
    webView.loadUrl(url);
}

@BindingAdapter("htmlString")
fun loadHTML(view: WebView, htmlString: String) {
    if (htmlString.isEmpty()) {
        return
    }
    try {
        view.webChromeClient = WebChromeClient()
        view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                return false
            }
        }
        view.loadData(htmlString, "text/html", "UTF-8")
    } catch (ex: Exception) {
    }
}

