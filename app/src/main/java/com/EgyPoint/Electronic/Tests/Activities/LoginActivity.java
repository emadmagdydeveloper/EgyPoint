package com.EgyPoint.Electronic.Tests.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.EgyPoint.Electronic.Tests.LoginMVP.PresenterImp;
import com.EgyPoint.Electronic.Tests.LoginMVP.Prsenter;
import com.EgyPoint.Electronic.Tests.LoginMVP.ViewData;
import com.EgyPoint.Electronic.Tests.Services.Preferense;
import com.EgyPoint.Electronic.Tests.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements ViewData {

    private DatabaseReference mRef;
    private EditText userName_et;
    private EditText password_et;
    private Button signUpBtn;
    private Prsenter prsenter;
    private String userId;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        prsenter = new PresenterImp(this,this);
        userName_et =  findViewById(R.id.userName);
        password_et = findViewById(R.id.password);
        signUpBtn=findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                prsenter.Login(userName_et.getText().toString(),password_et.getText().toString());
            }
        });


        mRef = FirebaseDatabase.getInstance().getReference();

        ///////////////////////////////////////////////////
        dialog = new ProgressDialog(this);
        dialog.setMessage("wait for login....");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
}

    @Override
    public void onLoggedInSuccess() {
        userId =userName_et.getText().toString();
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        dialog.show();
        CreateNewUsers();

    }

    private void CreateNewUsers() {
        Map<String ,String > map = new HashMap<>();
        map.put("id",userId);
        map.put("name",userId.substring(0,2));
        map.put("status","online");
        mRef.child("Users").child(userId).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    dialog.dismiss();
                    Preferense pref = new Preferense(LoginActivity.this);
                    pref.CreateSharedPref(userId);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userName",userId);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    @Override
    public void onFailed(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setUserNameError() {
        userName_et.setError("empty field");
    }

    @Override
    public void setPasswordError() {
        password_et.setError("empty field");

    }

    @Override
    public void onNetworkError() {
        Toast.makeText(this, "Check internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        Intent i=new Intent();
        i.setAction(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

        super.onBackPressed();
    }
}

