package com.example.evoliris.stockagebd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button valider;
    private CheckBox remember;

    private SharedPreferences userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = (EditText) findViewById(R.id.editText);
        this.password = (EditText) findViewById(R.id.editText2);
        this.valider = (Button) findViewById(R.id.button);
        this.remember = (CheckBox) findViewById(R.id.checkBox);

        this.valider.setOnClickListener(this);
        this.remember.setOnClickListener(this);

        this.userDetails = MainActivity.this.getSharedPreferences("userdetails", MODE_PRIVATE);
        verificationRemember();
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.button:  saveViewInformation(); openViewInformation(); break;
            case R.id.checkBox: rememberInformation(); break;
        }
    }

    private void saveViewInformation() {
        //TODO sauver infos et le passer à la vue.
        SharedPreferences.Editor edit = this.userDetails.edit();
        edit.putString("username", this.username.getText().toString().trim());
        edit.putString("password", this.password.getText().toString().trim());
        edit.apply();
    }

    public void openViewInformation(){
        //Lance un nouvelle vue et affiche les infos users
        String remember = this.userDetails.getString("remember", "");

        if(!this.remember.isChecked()){
            //userDetails.edit().clear().apply();
            username.setText("");
            password.setText("");
        }

        Intent intent = new Intent(MainActivity.this, AfficheInfo.class);
        startActivity(intent);

    }

    private void rememberInformation(){
        SharedPreferences.Editor edit = userDetails.edit();
        if(this.remember.isChecked()){
            edit.putString("remember", "1");
        }else{
            edit.putString("remember", "0");
        }
        edit.apply();
        saveViewInformation();
    }

    public void verificationRemember(){
        String remember = this.userDetails.getString("remember", "");
        String userName = this.userDetails.getString("username", "");
        String password = this.userDetails.getString("password", "");

        if(remember.equals("1")) {
            this.username.setText(userName);
            this.password.setText(password);
            this.remember.setChecked(true);
        }

    }

    public boolean verifUserPwdComplete(){
        if(!this.username.getText().toString().trim().equals("")&&
                !this.password.getText().toString().trim().equals("")){
            return true;
        }
        return false;
    }
}
