package com.framgia.lupx.remote.config.hehe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtWelcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcomeMessage = (TextView)findViewById(R.id.txtWelcomeMessage);
        String mesage = AppConfigs.getInstance().getConfig().getString(RemoteKey.WELCOME_MESSAGE);
        txtWelcomeMessage.setText(mesage);
    }
}
