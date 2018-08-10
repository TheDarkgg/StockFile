package com.example.evoliris.stockagebd;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AfficheInfo extends AppCompatActivity {

    private TextView afficheInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_info);

        afficheInfo = (TextView) findViewById(R.id.textView3);

        SharedPreferences userDetails = AfficheInfo.this.getSharedPreferences("userdetails", MODE_PRIVATE);
        String userName = userDetails.getString("username", "");
        String password = userDetails.getString("password", "");

        afficheInfo.setText("Username: "+userName+"\n"+"Passwod: "+password);


    }
}
