package cemayan.com.matbil;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.IOException;


public class YemekhaneFragment extends Fragment {
    private  ProgressDialog mProgressDialog;
    private WebView myWebView;
    private  TextView txt1;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        myView = inflater.inflate(R.layout.yemekhane_layout,container,false);
        return myView;

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        myWebView = (WebView)view.findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        myWebView.setWebViewClient(new MyBrowser());

        myWebView.loadUrl("http://yemekhane.ogu.edu.tr/");



    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Yemekhane");

    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
