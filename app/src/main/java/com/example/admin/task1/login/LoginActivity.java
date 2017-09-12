package com.example.admin.task1.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.home.MainActivity;
import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.response.LoginResponse;
import com.example.admin.task1.api.subscriber.LoginEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.User;
import com.example.admin.task1.utilities.SessionManager;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppActivity implements LoginEventSubscriber {

    @BindView(R.id.loginPass)               EditText editPassword;
    @BindView(R.id.loginEmail)              AutoCompleteTextView mEmailView;
    @BindView(R.id.newUser)                 TextView tvSignup;
    @BindView(R.id.email_sign_in_button)    Button mEmailSignInButton;


    private static final String TAG = "LoginActivity";

    LoginActivity mActivity;
    String email, password;

    List<User> userDetails = new ArrayList<>();

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());
    }

    @OnClick(R.id.newUser)
    public void Registration(View v) {
        Intent intent = new Intent(v.getContext(), RegistrationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.email_sign_in_button)
    public void Login(View view) {
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
            session.createLoginSession(loginResponse.getUser());

            ToastUtil.showCenterToast(getApplicationContext(),loginResponse.getUser().getName());
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            ToastUtil.showCenterToast(getApplicationContext(), loginResponse.getMessage());
        }

    }


}

