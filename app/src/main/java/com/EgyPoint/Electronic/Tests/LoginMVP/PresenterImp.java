package com.EgyPoint.Electronic.Tests.LoginMVP;

import android.content.Context;

public class PresenterImp implements Prsenter,Interactor.OnCompleteListener {
    ViewData viewData;
    Interactor interactor;
    Context context;


    public PresenterImp(ViewData viewData,Context context) {
        this.viewData = viewData;
        this.context = context;
        interactor =  new InteractorImp();

    }

    @Override
    public void Login(String user_name, String user_password) {
        interactor.Login(user_name,user_password,context,this);
    }

    @Override
    public void onLoggedInSuccess() {

        if (viewData!=null)
        {
            viewData.onLoggedInSuccess();
        }
    }

    @Override
    public void onFailed(String error) {
        if (viewData!=null)
        {
            viewData.onFailed(error);
        }
    }

    @Override
    public void onNetworkError() {
        if (viewData!=null)
        {
            viewData.onNetworkError();
        }
    }

    @Override
    public void setUserNameError() {

        if (viewData!=null)
        {
            viewData.setUserNameError();
        }
    }

    @Override
    public void setPasswordError() {
        if (viewData!=null)
        {
            viewData.setPasswordError();
        }
    }
}
