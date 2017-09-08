package com.example.admin.task1.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.task1.R;
import com.example.admin.task1.activity.MainActivity;
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

public class RegistrationActivity extends AppActivity implements RegistrationEventSubscriber {
    private static final String TAG = "RegistrationAct";

    RegistrationActivity mActivity;
    String name, email, password, confirmPassword, mobileNumber;

    List<User> userDetails = new ArrayList<>();

    private EditText editName, editMobileNum, editPassword, editConfrimPassword;
    private AutoCompleteTextView etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etEmail = (AutoCompleteTextView) findViewById(R.id.reg_email);
        editPassword = (EditText) findViewById(R.id.reg_password);
        editConfrimPassword = (EditText) findViewById(R.id.reg_confirm_password);
        editMobileNum = (EditText) findViewById(R.id.reg_mobNum);
        editName = (EditText) findViewById(R.id.reg_user_name);

        Button btn_Register = (Button) findViewById(R.id.email_reg_button);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
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
        });
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
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else {
            ToastUtil.showCenterToast(getApplicationContext(), registrationResponse.getMessage());
            Log.i(TAG, "false");

        }
    }
}
