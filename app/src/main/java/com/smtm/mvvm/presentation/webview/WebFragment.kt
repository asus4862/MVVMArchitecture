package com.smtm.mvvm.presentation.webview


import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.smtm.mvvm.R
import com.smtm.mvvm.databinding.FragmentWebviewBinding
import com.smtm.mvvm.presentation.base.RxBaseFragment

import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * https://github.com/Tigran-Khachikyan/News/blob/master/app/src/main/java/com/example/news/presentation/web/WebFragment.kt
 */
class WebFragment : RxBaseFragment<FragmentWebviewBinding>() {
    private val webViewModel: WebViewModel by viewModel()

    override fun getLayoutId(): Int {
        return R.layout.fragment_webview
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("gg", "SearchFragment onActivityCreated()")

        // TODO: Use the ViewModel
        binding.webView.getSettings().setJavaScriptEnabled(true);
        var st: String
        st =
            "<!doctype html>\\n<html lang=\\\"en\\\" class=\\\"w-100 h-100\\\">\\n\\n<head>\\n <script>\\n var __PCA_SIGNATURES__ = '';\\n var __PCA_ICE_SERVERS__ = '[{\\\"username\\\":\\\"1588058023:dd\\\",\\\"credential\\\":\\\"l0eCC6OY1F/sIpXzWkyeREIdUBw=\\\",\\\"ttl\\\":86400,\\\"urls\\\":[\\\"turn:i-021409487c265a595.turn.pplink.net:3478\\\",\\\"turns:i-021409487c265a595.turn.pplink.net:5349\\\"]}]';\\n var __PCA_USER_KEY__ = 'u_a089b480fc07eac032631f05c65ce74c_1586827765386';\\n var __PCA_SOCKET_END_POINT__ = 'https://pagecall-api.pplink.net';\\n var __PCA_CLIENT_VERSION__ = '1.2.25';\\n var __PCA_CLIENT_NAME__ = 'pagecall';\\n var __PCA_REPLAY_TITLE__ = '';\\n var __PCA_CUSTOM_DATA__ = '{\\\"layout\\\":{\\\"language\\\":\\\"ko\\\",\\\"videoPosition\\\":\\\"fixed\\\",\\\"optionalTools\\\":{\\\"shape\\\":true,\\\"textbox\\\":false},\\\"chat\\\":false,\\\"memo\\\":false,\\\"participants\\\":{\\\"all\\\":null,\\\"max\\\":2},\\\"host\\\":false,\\\"userList\\\":false,\\\"pushToTalk\\\":false}}';\\n </script>\\n <meta charset=\\\"utf-8\\\">\\n <title>\\n Pagecall\\n </title>\\n <base href=\\\"\\\">\\n <meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\\\" />\\n <meta name=\\\"theme-color\\\" content=\\\"#673ab7\\\" />\\n <link rel=\\\"icon\\\" type=\\\"image/x-icon\\\" href=\\\"favicon.ico\\\" />\\n <link rel=\\\"manifest\\\" href=\\\"manifest.json\\\" />\\n <script>\\n var __PCA_USER_KEY__ = window['__PCA_USER_KEY__'] ? window['__PCA_USER_KEY__'] : '1';\\n var __PCA_SOCKET_END_POINT__ = window['__PCA_SOCKET_END_POINT__'] ? window['__PCA_SOCKET_END_POINT__'] : '1';\\n </script>\\n <script>\\n (function(d) {\\n var config = {\\n kitId: 'lzk1vde',\\n scriptTimeout: 3000,\\n async: true\\n },\\n h=d.documentElement,t=setTimeout(function(){h.className=h.className.replace(/\\\\bwf-loading\\\\b/g,\\\"\\\")+\\\" wf-inactive\\\";},config.scriptTimeout),tk=d.createElement(\\\"script\\\"),f=false,s=d.getElementsByTagName(\\\"script\\\")[0],a;h.className+=\\\" wf-loading\\\";tk.src='https://use.typekit.net/'+config.kitId+'.js';tk.async=true;tk.onload=tk.onreadystatechange=function(){a=this.readyState;if(f||a&&a!=\\\"complete\\\"&&a!=\\\"loaded\\\")return;f=true;clearTimeout(t);try{Typekit.load(config)}catch(e){}};s.parentNode.insertBefore(tk,s)\\n })(document);\\n </script>\\n<link rel=\\\"stylesheet\\\" href=\\\"https://pplink.net/template/pagecall/1.2.25/styles.0bfc63856cd2702a5c3c.css\\\"></head>\\n\\n<body class=\\\"w-100 h-100\\\">\\n <noscript>\\n You need to enable JavaScript to run this app.\\n </noscript>\\n <pagecall class=\\\"position-absolute w-100\\\">\\n <div id=\\\"spinner\\\">Loading...</div>\\n </pagecall>\\n<script type=\\\"text/javascript\\\" src=\\\"https://pplink.net/template/pagecall/1.2.25/runtime.0c7602c8f4f7b61b749c.js\\\"></script><script type=\\\"text/javascript\\\" src=\\\"https://pplink.net/template/pagecall/1.2.25/polyfills.d725a8cb58c7ff0bfb3f.js\\\"></script><script type=\\\"text/javascript\\\" src=\\\"https://pplink.net/template/pagecall/1.2.25/main.6ab436627d85fa16c2a4.js\\\"></script></body>\\n\\n</html>"
//        binding.webView.loadUrl(st);
        binding.webView.loadData(st, "text/html", "UTF-8")

        initViewModel()

    }

    private fun getJs() {

        Log.d("gg", "getJs()")
        try {
            val assetManager = context!!.assets
            val inputStream: InputStream = assetManager.open("button.js")
            val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
            val sb = StringBuilder()
            var line: String?
            val br = BufferedReader(inputStreamReader)

            line = br.readLine()
            while (line != null) {
//                Log.d("gg", "getJs()3" + line)
                sb.append(line)
                line = br.readLine()
            }

            Log.e("gg", " " + sb.toString())
            webViewModel.jsSave(sb.toString())
            br.close()
            inputStreamReader.close()
        } catch (e: IOException) {

        }


    }

    private fun initViewModel() {
//        val owner = this
        with(webViewModel) {

            getJs()
        }
        binding.webViewModel = webViewModel
    }
}