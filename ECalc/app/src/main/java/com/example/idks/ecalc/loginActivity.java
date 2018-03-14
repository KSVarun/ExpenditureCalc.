package com.example.idks.ecalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by idks on 3/14/2018.
 */

public class loginActivity extends Activity{

    FirebaseAuth auth;
    EditText phone,otp;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;
    String verificationCode;
    ProgressBar pb;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        phone = (EditText) findViewById(R.id.phone_number);
        otp = (EditText) findViewById(R.id.OTP);
        pb = (ProgressBar) findViewById(R.id.progressBar3);

        auth = FirebaseAuth.getInstance();

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                pb.setVisibility(View.VISIBLE);
                signIN(phoneAuthCredential);
               /* startActivity(new Intent(loginActivity.this,MainActivity.class));
                finish();*/
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(getApplicationContext(),"Code sent",Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void send_OTP(View v){
        String number = phone.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, TimeUnit.SECONDS,this,mCallBack
        );

    }


    public void signIN(PhoneAuthCredential cred){

        auth.signInWithCredential(cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(loginActivity.this,MainActivity.class));
                    finish();
                }
                pb.setVisibility(View.GONE);
            }
        });

    }

    public void verify(View v){
        pb.setVisibility(View.VISIBLE);
        String inputCode = otp.getText().toString();
        verifyPhoneNumber(verificationCode,inputCode);
    }

    public void verifyPhoneNumber(String vc,String ic){

        PhoneAuthCredential cred = PhoneAuthProvider.getCredential(vc,ic);
        signIN(cred);
    }

}
