package incop.ark.lyte.adaboo.gofood;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import im.delight.android.webview.AdvancedWebView;

/**
 * Created by adaboo on 9/4/17.
 */

public class GoFood extends AppCompatActivity implements AdvancedWebView.Listener{

    private AdvancedWebView maini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gofood);

        //main = (WebView) findViewById(R.id.gofood);

        //WebSettings webSettings = main.getSettings();

        //webSettings.setJavaScriptEnabled(true);

       // if (Build.VERSION.SDK_INT >= 19) {
       //     main.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        //}
       // else {
        //    main.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
       // }
        maini = (AdvancedWebView) findViewById(R.id.gofood);
        maini.setListener(this, this);
        maini.loadUrl("http://www.gofoodpng.biz/");


       // main.loadUrl("http://www.gofoodpng.biz/");

        //main.setWebViewClient(new WebViewClient());


        //main.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

    }


    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        maini.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        maini.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        maini.onDestroy();
        // ...
        super.onDestroy();
    }





    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}



