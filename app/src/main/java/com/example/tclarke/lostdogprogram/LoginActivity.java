package com.example.tclarke.lostdogprogram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends ActionBarActivity {
    private EditText passwordAgainET;
    private EditText passwordET;
    private EditText usernameET;
    private EditText emailET;
    private Button loginBTN;
    private boolean signUpSelect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ParseObject.registerSubclass(LostDog.class);
        Parse.initialize(this, "NtFAPxv8LmzhKUQ16CgYi62twBNHypYLFfAcLQKc", "U7qhHqNJtJ8jAINa66ldiFCLAyk8BKQIQkwgTgij");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
        if (ParseUser.getCurrentUser() != null) {
            // Start an intent for the logged in activity
           startActivity(new Intent(this, MainMenuActivity.class));
        }


        usernameET = (EditText) findViewById(R.id.untxt);
        passwordET = (EditText) findViewById(R.id.pwtxt);
        passwordAgainET = (EditText) findViewById(R.id.pw2txt);
        emailET = (EditText) findViewById(R.id.emailtxt);
        loginBTN = (Button) findViewById(R.id.loginBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signUp(View view) {

        passwordAgainET.setVisibility(View.VISIBLE);
        emailET.setVisibility(View.VISIBLE);
        signUpSelect = true;
            }

    public void checkSignUp(View view){
        if(signUpSelect == true){
            signUpWithDetails();
        }
        else{
            login();
        }
    }


    public void signUpWithDetails(){
        System.out.println("signupSELECTED");
        String username = usernameET.getText().toString().trim();
        String email  = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String password2 = passwordAgainET.getText().toString().trim();

        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder("Error: ");
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append("Please Enter Username");
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append("Join Error");
            }
            validationError = true;
            validationErrorMessage.append("Please Enter Password");
        }
        if (!password.equals(password2)) {
            if (validationError) {
                validationErrorMessage.append("Join Error");
            }
            validationError = true;
            validationErrorMessage.append("Passwords Do Not Match");
        }
        validationErrorMessage.append("End");

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Signing You Up...");
        dialog.show();

        // Set up a new Parse user
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // Call the Parse signup method
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }




    public  void login(){
        System.out.println("loginSELECTED");
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();


        // Validate the sign up data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder("Error: ");
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append("Please Enter Username");
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append("Join Error");
            }
            validationError = true;
            validationErrorMessage.append("Please Enter Password");
        }

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Logging In...");
        dialog.show();
        // Call the Parse login method
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }

}
