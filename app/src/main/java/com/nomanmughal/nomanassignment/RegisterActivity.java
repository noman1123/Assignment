package com.nomanmughal.nomanassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText registerFirstNameEditTrxt;
    EditText registerLastNameditText;
    EditText registerEmailEditTrxt;
    EditText registerPasswordEditText;
    EditText registerConfirmPasswordEditText;
    Button registerButtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFirstNameEditTrxt = findViewById(R.id.register_first_name_edittext);
        registerLastNameditText = findViewById(R.id.register_last_name_edittext);
        registerEmailEditTrxt = findViewById(R.id.register_email_edittext);
        registerPasswordEditText = findViewById(R.id.register_password_edittext);
        registerConfirmPasswordEditText = findViewById(R.id.register_confirm_password_edittext);

        registerButtn = findViewById(R.id.register_button);

        registerButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               performRegister();
            }
        });
    }

    private void performRegister() {

        registerFirstNameEditTrxt.setError(null);
        registerLastNameditText.setError(null);
        registerEmailEditTrxt.setError(null);
        registerPasswordEditText.setError(null);
        registerConfirmPasswordEditText.setError(null);

        String firstName = registerFirstNameEditTrxt.getText().toString().trim();
        String lastName = registerLastNameditText.getText().toString().trim();
        String email = registerEmailEditTrxt.getText().toString().trim();
        String password = registerPasswordEditText.getText().toString().trim();
        String confirmPassword = registerConfirmPasswordEditText.getText().toString().trim();


        if (TextUtils.isEmpty(firstName)) {
            registerFirstNameEditTrxt.setError(getString(R.string.first_name_error));
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            registerLastNameditText.setError(getString(R.string.last_name_error));
            return;
        }
        if (TextUtils.isEmpty(email)) {
            registerEmailEditTrxt.setError(getString(R.string.email_enter_error));
            return;
        }

        if (!isValidEmaillId(email)) {
            registerEmailEditTrxt.setError(getString(R.string.valid_email_check));
            return;
        }

        if (TextUtils.isEmpty(password)) {

            registerPasswordEditText.setError(getString(R.string.password_enter_error));
            return;
        }

        if(password.length()<5){

            registerPasswordEditText.setError(getString(R.string.password_length));
            return;

        }

        if(!password.equals(confirmPassword)){

            registerConfirmPasswordEditText.setError(getString(R.string.password_match));
            return;

        }

        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
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
