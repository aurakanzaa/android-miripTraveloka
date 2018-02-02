package com.example.ruwet.miriptraveloka2.activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
//
//import com.example.ruwet.miriptraveloka2.R;
import com.example.ruwet.miriptraveloka2.helper.inputValidation;
import com.example.ruwet.miriptraveloka2.sql.databaseHelper;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

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


    private ScrollView loginform;
    private Button ButtonLogin;
    private final AppCompatActivity activity = LoginActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private AutoCompleteTextView textInputEditTextEmail;
    private EditText textInputEditTextPassword;


   private inputValidation inputValidation;
   private databaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        initViews();
        initListeners();
        initObjects();
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

        String username = preferences.getString("email", "");
    }


    private void initViews() {

        loginform = (ScrollView) findViewById(R.id.login_form);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.email_login_form);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.password_login_form);
        textInputEditTextEmail = (AutoCompleteTextView) findViewById(R.id.email);
        textInputEditTextPassword = (EditText) findViewById(R.id.password);
        ButtonLogin = (Button) findViewById(R.id.sign_in_button);

    }

    private void initListeners() {

        ButtonLogin.setOnClickListener((this));
    }



    private void initObjects() {
        databaseHelper = new databaseHelper(activity);
        inputValidation = new inputValidation(activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                verifySQLite();
                break;
        }
    }

    private void verifySQLite() {
        if (!inputValidation.isInputFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_invalid_email))) {
            return;
        }
        if (!inputValidation.isInputFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_invalid_password))) {
            return;
        }
        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString(), textInputEditTextPassword.getText().toString())) {

//            databaseHelper.SimpanData(textInputEditTextEmail.getText().toString(),textInputEditTextPassword.getText().toString());
//            finish();


            SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", this.textInputEditTextEmail.getText().toString());
            editor.putString("password", this.textInputEditTextPassword.getText().toString());
            editor.commit();
            Intent accoutsIntent = new Intent(activity, MainActivity.class);
            accoutsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accoutsIntent);


        } else {
            Snackbar.make(loginform, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);


    }
}
