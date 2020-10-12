package com.ipos.iposmanage.activities;

import android.os.Bundle;

import com.ipos.iposmanage.app.Constants;
import com.ipos.iposmanage.app.R;
import com.ipos.iposmanage.fragment.WebViewFragment;

public class WebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_no_homebar);
        String url = getIntent().getStringExtra(Constants.KEY_DATA);
        WebViewFragment mWebViewFragment = WebViewFragment.newInstance(url);
        executeFragmentTransaction(mWebViewFragment, R.id.content, false, false);
    }
}
