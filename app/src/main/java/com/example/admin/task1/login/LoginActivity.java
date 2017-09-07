package com.example.admin.task1.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.activity.MainActivity;
import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.response.LoginResponse;
import com.example.admin.task1.api.subscriber.LoginEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.User;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppActivity implements LoginEventSubscriber {
    private static final String TAG = "LoginActivity";

    LoginActivity mActivity;
    String email, password;

    List<User> userDetails = new ArrayList<>();

    private EditText editPassword;
    private AutoCompleteTextView mEmailView;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        mEmailView = (AutoCompleteTextView) findViewById(R.id.loginEmail);
        editPassword = (EditText) findViewById(R.id.loginPass);

        TextView tvSignup = (TextView) findViewById(R.id.newUser);
        tvSignup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                email = TextUtil.cleanupString(mEmailView.getText().toString().trim());
                password = TextUtil.cleanupString(editPassword.getText().toString().trim());

                if (!isEmailValid(email) || !isPasswordValid(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

                    if (!isEmailValid(email)) {
                        mEmailView.setError(getString(R.string.error_invalid_email));
                    }
                    if (!isPasswordValid(password)) {
                        editPassword.setError(getString(R.string.error_invalid_password));
                    }
                    if (TextUtils.isEmpty(email)) {
                        mEmailView.setError(getString(R.string.error_field_required));
                    }
                    if (TextUtils.isEmpty(password)) {
                        editPassword.setError(getString(R.string.error_field_required));
                    }

                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setEmail(email);
                    loginRequest.setPassword(password);

                    showProgress();
                    CommunicationManager.getInstance().postLoginDetails(loginRequest, mActivity);
                }
            }
        });
    }

    private boolean isEmailValid(String email) {

        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onLoginCompleted(LoginResponse loginResponse) {
        hideProgress();
        if (loginResponse.isSuccess()) {
            ToastUtil.showCenterToast(getApplicationContext(), loginResponse.getMessage());

            session.createLoginSession(loginResponse.getUser().getName(),loginResponse.getUser().getEmail());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), loginResponse.getMessage());
        }

    }

}

