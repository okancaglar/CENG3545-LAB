package com.lab.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button btngo;
    private EditText txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAddress = findViewById(R.id.address);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        btngo = findViewById(R.id.go);
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 webView.loadUrl("https://"+ txtAddress.getText());
            }
        });
        if (getIntent() != null && getIntent().getData()!=null) {
            txtAddress.setText(getIntent().getData().toString());
            webView.loadUrl(getIntent().getData().toString());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}