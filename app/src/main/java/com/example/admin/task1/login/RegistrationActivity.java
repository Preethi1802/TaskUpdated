package com.example.admin.task1.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.task1.R;
import com.example.admin.task1.api.request.RegistrationRequest;
import com.example.admin.task1.api.response.RegistrationResponse;
import com.example.admin.task1.api.subscriber.RegistrationEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.model.User;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppActivity implements RegistrationEventSubscriber {

    @BindView(R.id.reg_user_name)           EditText editName;
    @BindView(R.id.reg_mobNum)              EditText editMobileNum;
    @BindView(R.id.reg_password)            EditText editPassword;
    @BindView(R.id.reg_confirm_password)    EditText editConfrimPassword;
    @BindView(R.id.reg_email)               AutoCompleteTextView etEmail;
    @BindView(R.id.email_reg_button)        Button btn_Register;


    private static final String TAG = "RegistrationAct";

    RegistrationActivity mActivity;
    String name, email, password, confirmPassword, mobileNumber;

    List<User> userDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.email_reg_button)
    public void onClick(View v) {
        name = TextUtil.cleanupString(editName.getText().toString().trim());
        mobileNumber = TextUtil.cleanupString(editMobileNum.getText().toString().trim());
        email = TextUtil.cleanupString(etEmail.getText().toString().trim());
        password = TextUtil.cleanupString(editPassword.getText().toString().trim());
        confirmPassword = TextUtil.cleanupString(editConfrimPassword.getText().toString().trim());

        if (!isEmailValid(email) || !isMobileNumberValid(mobileNumber) || !isPasswordValid(password)
                || !isPasswordValid(confirmPassword) || !password.equals(confirmPassword) || (TextUtils.isEmpty(name))
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(mobileNumber) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(confirmPassword)) {


            if (!isEmailValid(email)) {
                etEmail.setError(getString(R.string.error_invalid_email));
            }
            if (!isMobileNumberValid(mobileNumber)) {
                editMobileNum.setError(getString(R.string.error_invalid_mobNumber));
            }
            if (!isPasswordValid(password)) {
                editPassword.setError(getString(R.string.error_invalid_password));
            }
            if (!isPasswordValid(confirmPassword)) {
                editConfrimPassword.setError(getString(R.string.error_invalid_password));
            }
            if (!password.equals(confirmPassword)) {
                editConfrimPassword.setError(getString(R.string.error_confirm_password_doesnt_match));
            }
            if (TextUtils.isEmpty(name)) {
                editName.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(email)) {
                etEmail.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(mobileNumber)) {
                editMobileNum.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(password)) {
                editPassword.setError(getString(R.string.error_field_required));
            }
            if (TextUtils.isEmpty(confirmPassword)) {
                editConfrimPassword.setError(getString(R.string.error_field_required));
            }

        } else {
            RegistrationRequest registrationRequest = new RegistrationRequest();
            registrationRequest.setName(name);
            registrationRequest.setEmail(email);
            registrationRequest.setMobileNumber(mobileNumber);
            registrationRequest.setPassword(password);
            registrationRequest.setPassword_confirmation(confirmPassword);

            showProgress();
            CommunicationManager.getInstance().postRegistrationDetails(registrationRequest, mActivity);
        }

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String pass) {
        return pass.length() > 4;
    }

    private boolean isMobileNumberValid(String mobNumber) {
        return mobNumber.length() == 10;
    }


    @Override
    public void onRegistrationCompleted(RegistrationResponse registrationResponse) {
        hideProgress();
        if (registrationResponse.isSuccess()) {

            ToastUtil.showCenterToast(getApplicationContext(), registrationResponse.getMessage());
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {

            ToastUtil.showCenterToast(getApplicationContext(), registrationResponse.getMessage());
        }
    }
}
