package com.nomanmughal.nomanassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditTrxt;
    EditText passwordEditText;
    Button loginButton;
    Button registerButtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditTrxt = findViewById(R.id.email_edittext);
        passwordEditText = findViewById(R.id.password_edittext);
        loginButton = findViewById(R.id.login_button);
        registerButtn = findViewById(R.id.login_to_register_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performLogin();
            }
        });

        registerButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toRegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toRegisterIntent);
                finish();
            }
        });
    }

    private void performLogin() {

        emailEditTrxt.setError(null);
        passwordEditText.setError(null);

        String email = emailEditTrxt.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditTrxt.setError(getString(R.string.email_enter_error));
            return;
        }

        if (!isValidEmaillId(email)) {
            emailEditTrxt.setError(getString(R.string.valid_email_check));
            return;
        }

        if (TextUtils.isEmpty(password)) {

            passwordEditText.setError(getString(R.string.password_enter_error));
            return;
        }

        if(password.length()<5){

            passwordEditText.setError(getString(R.string.password_length));
            return;

        }

        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
