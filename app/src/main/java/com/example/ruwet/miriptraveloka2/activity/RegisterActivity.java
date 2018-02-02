package com.example.ruwet.miriptraveloka2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.ruwet.miriptraveloka2.helper.inputValidation;
import com.example.ruwet.miriptraveloka2.model.user;
import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

//import com.example.ruwet.miriptraveloka2.R;


/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
//    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo@example.com:hello", "bar@example.com:world"
//    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
//    private UserLoginTask mAuthTask = null;

    // UI references.
//    private AutoCompleteTextView mEmailView;
//    private EditText mPasswordView;
//    private View mProgressView;
//    private View mLoginFormView;
    private ScrollView Registerform;
    private Button ButtonRegister;
    private final AppCompatActivity activity = RegisterActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutNama;
    private TextInputLayout textInputLayoutTelp;
    private TextInputLayout textInputLayoutRePassword;
    private user User;

    private AutoCompleteTextView textInputEditTextEmail;

    private EditText textInputEditTextPassword;
    private EditText textInputEditTextRePassword;
    private EditText textInputEditTextNama;
    private EditText textInputEditTextTelp;


    private inputValidation inputValidation;
    private databaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){

        Registerform = (ScrollView) findViewById(R.id.register_form);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.email_register_form);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.password_register_form);
        textInputLayoutNama = (TextInputLayout) findViewById(R.id.nama_register_form);
        textInputLayoutTelp = (TextInputLayout) findViewById(R.id.telp_register_form);
        textInputEditTextEmail = (AutoCompleteTextView) findViewById(R.id.emailRegister);
        textInputEditTextNama = (EditText) findViewById(R.id.nama);
        textInputEditTextTelp = (EditText) findViewById(R.id.telp);
        textInputEditTextPassword = (EditText) findViewById(R.id.passwordRegister);
        textInputEditTextRePassword = (EditText) findViewById(R.id.repassword) ;
        textInputLayoutRePassword = (TextInputLayout) findViewById(R.id.repassword_register_form) ;

        ButtonRegister = (Button) findViewById(R.id.sign_up_button);
    }

    private void initListeners() {

        ButtonRegister.setOnClickListener((this));
    }

    private void initObjects() {
        databaseHelper = new databaseHelper(activity);
        inputValidation = new inputValidation(activity);
        User = new user();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_button:
                postDataToSql();
                break;

        }

    }
    private void postDataToSql() {
        if (!inputValidation.isInputFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditEmail(textInputEditTextEmail, textInputLayoutEmail,getString(R.string.error_invalid_email))) {
            return;
        }
        if (!inputValidation.isInputFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_invalid_password))) {
            return;
        }
        if (!inputValidation.isInputEditMatches(textInputEditTextPassword, textInputEditTextRePassword,textInputLayoutRePassword,"password didnt match")) {
            return;
        }
        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString(),textInputEditTextPassword.getText().toString()) ) {
            User.setEmail(textInputEditTextEmail.getText().toString().trim());
            User.setPassword(textInputEditTextPassword.getText().toString().trim());
            User.setNama(textInputEditTextNama.getText().toString().trim());
            User.setTelp(textInputEditTextTelp.getText().toString().trim());
            SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", this.textInputEditTextEmail.getText().toString());
            //stores 3 new instances of sharedprefs. Both the user and password's keys are the same as the input.
            //Must be done this way because sharedprefs is stupid and inefficient. You cannot store Arrays easily
            //so I use strings instead.

            databaseHelper.addUser(User);
//
            Snackbar.make(Registerform,textInputEditTextNama.getText().toString().trim() ,Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
            Intent intent = new Intent(RegisterActivity.this, com.example.ruwet.miriptraveloka2.activity.LoginActivity.class);
            intent.putExtra("EMAIL",textInputEditTextEmail.getText().toString().trim());
            startActivity(intent);

        }
        else {
            Snackbar.make(Registerform, getString(R.string.error_invalid_email),Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextRePassword.setText(null);


    }
}

