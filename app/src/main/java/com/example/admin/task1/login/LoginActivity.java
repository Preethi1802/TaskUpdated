package com.example.admin.task1.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.MainThread;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.task1.R;
import com.example.admin.task1.api.request.FirebaseLoginRequest;
import com.example.admin.task1.api.request.LoginRequest;
import com.example.admin.task1.api.response.FirebaseLoginResponse;
import com.example.admin.task1.api.response.LoginResponse;
import com.example.admin.task1.api.subscriber.FirebaseLoginEventSubscriber;
import com.example.admin.task1.api.subscriber.LoginEventSubscriber;
import com.example.admin.task1.api.util.CommunicationManager;
import com.example.admin.task1.app.AppActivity;
import com.example.admin.task1.utilities.SessionManager;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.common.Scopes;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.thapovan.android.commonutils.text.TextUtil;
import com.thapovan.android.commonutils.toast.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppActivity implements LoginEventSubscriber, FirebaseLoginEventSubscriber {

    @BindView(R.id.loginPass)               EditText editPassword;
    @BindView(R.id.loginEmail)              AutoCompleteTextView mEmailView;
    @BindView(R.id.newUser)                 TextView tvSignup;
    @BindView(R.id.email_sign_in_button)    Button mEmailSignInButton;
    @BindView(R.id.root)                    View mRootView;

    private static final String TAG = "LoginActivity";

    LoginActivity mActivity;
    String email, password;

    SessionManager session;

    IdpResponse response;

    private static final int RC_SIGN_IN = 101;
    private static final String UNCHANGED_CONFIG_VALUE = "CHANGE-ME";
    private static final String FIREBASE_TOS_URL = "https://firebase.google.com/terms/";
    private static final String FIREBASE_PRIVACY_POLICY_URL = "https://firebase.google.com/terms/analytics/#7_privacy";

    public static void start(final Activity activity) {
        final Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        session = new SessionManager(getApplicationContext());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startSignedInActivity(null);
            finish();
            return;
        }
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

    @OnClick(R.id.firebase_login)
    public void onBtnFirebaseLogin() {
        initFireBaseLogin();
    }

    public void initFireBaseLogin() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setTheme(getSelectedTheme())
                        .setLogo(getSelectedLogo())
                        .setAvailableProviders(getSelectedProviders())
                        .setTosUrl(getSelectedTosUrl())
                        .setPrivacyPolicyUrl(getSelectedPrivacyPolicyUrl())
                        .setIsSmartLockEnabled(true)
                        .setAllowNewEmailAccounts(true)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handleSignInResponse(resultCode, data);
            return;
        }
        showSnackbar(R.string.unknown_response);
    }

    @MainThread
    private void handleSignInResponse(int resultCode, Intent data) {
        IdpResponse response = IdpResponse.fromResultIntent(data);

        // Successfully signed in
        if (resultCode == RESULT_OK) {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                Log.i("FirebaseTAG","Hiiii");
                FirebaseLoginRequest firebaseLoginRequest = new FirebaseLoginRequest();
                firebaseLoginRequest.setEmail(currentUser.getEmail());
                firebaseLoginRequest.setName(currentUser.getDisplayName());

                Iterator<String> providerIter = currentUser.getProviders().iterator();
                while (providerIter.hasNext()) {
                    String provider = providerIter.next();
                    if (GoogleAuthProvider.PROVIDER_ID.equals(provider)) {
                        firebaseLoginRequest.setProvider("google");
                    } else if (FacebookAuthProvider.PROVIDER_ID.equals(provider)) {
                        firebaseLoginRequest.setProvider("facebook");
                    } else if (EmailAuthProvider.PROVIDER_ID.equals(provider)) {
                        firebaseLoginRequest.setProvider("firebase-email");
                    } else {
                        firebaseLoginRequest.setProvider(provider);
                    }
                }

                Log.i("FirebaseTAG",""+currentUser.getProviders().size());

                firebaseLoginRequest.setProvider_id(currentUser.getUid());

                CommunicationManager.getInstance().postFirebaseLoginDetails(firebaseLoginRequest,mActivity);

            }

        } else {
            // Sign in failed
            if (response == null) {
                // User pressed back button
                showSnackbar(R.string.sign_in_cancelled);
                return;
            }

            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                showSnackbar(R.string.no_internet_connection);
                return;
            }

            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                showSnackbar(R.string.unknown_error);
                return;
            }
        }
        //  showSnackbar(R.string.unknown_sign_in_response);
    }


    private void startSignedInActivity(IdpResponse response) {
        startActivity(
                UserProfileActivity.createIntent(
                        this,
                        response,
                        new UserProfileActivity.SignedInConfig(
                                getSelectedLogo(),
                                getSelectedTheme(),
                                getSelectedProviders(),
                                getSelectedTosUrl(),
                                false,
                                false)));
    }

    @MainThread
    @StyleRes
    private int getSelectedTheme() {
        return R.style.AppTheme;
//        return AuthUI.getDefaultTheme();
    }

    @MainThread
    @DrawableRes
    private int getSelectedLogo() {
        return R.drawable.logo;
//        return AuthUI.NO_LOGO;
    }

    @MainThread
    private String getSelectedTosUrl() {
        return FIREBASE_TOS_URL;
    }

    @MainThread
    private String getSelectedPrivacyPolicyUrl() {
        return FIREBASE_PRIVACY_POLICY_URL;
    }

    @MainThread
    private List<AuthUI.IdpConfig> getSelectedProviders() {
        List<AuthUI.IdpConfig> selectedProviders = new ArrayList<>();

        if (true) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER)
                            .setPermissions(getGooglePermissions())
                            .build());
        }

        if (!isFacebookMisconfigured()) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER)
                            .setPermissions(getFacebookPermissions())
                            .build());
        }

        /*if (!isTwitterMisconfigured()) {
            selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build());
        }*/

        if (true) {
            selectedProviders.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
        }

        if (true) {
            selectedProviders.add(
                    new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build());
        }

        return selectedProviders;
    }


    @MainThread
    private boolean isGoogleMisconfigured() {
        return UNCHANGED_CONFIG_VALUE.equals(getString(R.string.default_web_client_id));
    }

    @MainThread
    private boolean isFacebookMisconfigured() {
        return UNCHANGED_CONFIG_VALUE.equals(getString(R.string.facebook_application_id));
    }

    @MainThread
    private boolean isTwitterMisconfigured() {
        List<String> twitterConfigs = Arrays.asList(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret)
        );

        return twitterConfigs.contains(UNCHANGED_CONFIG_VALUE);
    }

    @MainThread
    private List<String> getFacebookPermissions() {
        List<String> result = new ArrayList<>();
//        if (true) {
//            result.add("public_profile");
//        }
        if (true) {
            result.add("user_friends");
        }
        if (true) {
            result.add("user_photos");
        }
        return result;
    }

    @MainThread
    private List<String> getGooglePermissions() {
        List<String> result = new ArrayList<>();
        if (true) {
            result.add("https://www.googleapis.com/auth/youtube.readonly");
        }
        if (true) {
            result.add(Scopes.DRIVE_FILE);
        }
        return result;
    }
    @MainThread
    private void showSnackbar(@StringRes int errorMessageRes) {
           Snackbar.make(mRootView, errorMessageRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onLoginCompleted(LoginResponse loginResponse) {
        hideProgress();
        if (loginResponse.isSuccess()) {

            ToastUtil.showCenterToast(getApplicationContext(), loginResponse.getMessage());
            session.createLoginSession(loginResponse.getUser());
            finish();
           // MainActivity.start(LoginActivity.this);


        } else {
            ToastUtil.showCenterToast(getApplicationContext(), loginResponse.getMessage());
        }
    }

    @Override
    public void onFirebaseLoginCompleted(FirebaseLoginResponse firebaseLoginResponse) {

        if (firebaseLoginResponse.isSuccess()) {

            session.createLoginSession(firebaseLoginResponse.getUser());
            finish();
          //  MainActivity.start(LoginActivity.this);

        }
        else {
            ToastUtil.showCenterToast(getApplicationContext(), firebaseLoginResponse.getMessage());
        }
    }
}