package com.example.admin.task1.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity/* implements NetworkStateReceiver.NetworkStateReceiverListener*/

{

    private ProgressDialog mProgress;
    private AppActivity mActivity;


   /* public void showProgress(@StringRes int resId){
        showProgress(getString(resId));
    }

    public void showProgress(String message){
        if(mProgress!=null){
            mProgress.setMessage(message);
            return;
        }
        mProgress = DialogUtil.showProgressDialog(this,message);
    }

    protected int getSourceFrom(){
        return getIntent().getIntExtra(KEY_SOURCE_FROM, Constants.INVALID);
    }

    public void hideProgress(){
        DialogUtil.hideProgressDialog(mProgress);
        mProgress=null;
    }*/


}
