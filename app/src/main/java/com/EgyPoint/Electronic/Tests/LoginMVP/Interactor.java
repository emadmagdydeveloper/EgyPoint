package com.EgyPoint.Electronic.Tests.LoginMVP;


import android.content.Context;

/**
 * Created by Delta on 15/02/2018.
 */

public interface Interactor {
    interface OnCompleteListener
    {
        void onLoggedInSuccess();
        void onFailed(String error);
        void onNetworkError();
        void setUserNameError();
        void setPasswordError();
    }

    void Login(String user_name, String user_password, Context context, Interactor.OnCompleteListener listener);

}
