package com.epizy.notify247.notify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    Button login_btn;
    EditText username;
    EditText password;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login_btn=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.Register_dialogue);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new intent to open the {@link PhrasesActivity}
                Intent registrationIntent = new Intent(MainActivity.this, Registration.class);

                // Start the new activity
                startActivity(registrationIntent);
            }
        });


    }


    public void OnLogin(View view) throws NoSuchAlgorithmException, DigestException {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);



      //  backgroundWorker.execute(type, username, password);
    }
}
