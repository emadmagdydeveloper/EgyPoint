package com.EgyPoint.Electronic.Tests.LoginMVP;

/**
 * Created by Delta on 15/02/2018.
 */

public interface ViewData {
    void onLoggedInSuccess();
    void onFailed(String error);
    void setUserNameError();
    void setPasswordError();
    void onNetworkError();
}
