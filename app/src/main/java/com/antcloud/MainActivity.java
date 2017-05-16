package com.antcloud;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.antcloud.activity.AntSubPresenter;
import com.antcloud.activity.AntView;

public class MainActivity extends AntView {
    AntSubPresenter mAntSubPresenter = new AntSubPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAntSubPresenter.getData();
    }

    @Override
    public void showView() {
        Log.d("===>>>>showView", "YES");
        ((TextView) findViewById(R.id.snackbar_text)).setText("xx");
        Toast.makeText(this, "SHOWVIEW", Toast.LENGTH_SHORT).show();
    }
}
