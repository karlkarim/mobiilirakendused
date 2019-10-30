package com.artjomzurbenko.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final  String TAG = "SIGNUP";

    private EditText et_first,et_last,et_mail,et_pass,et_confirm_pass;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        progressBar = findViewById(R.id.indeterminateBar);
        et_first = findViewById(R.id.etEnterFirstName);
        et_last =findViewById(R.id.etEnterLastName);
        et_mail =findViewById(R.id.etMail);
        et_pass =findViewById(R.id.enterPass);
        et_confirm_pass =findViewById(R.id.conPass);

    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null)
        {
            Log.d(TAG,"Signed in: " + currentUser.getEmail());
        }

    }

    public void onRegister(View view) {

        final String firstname = et_first.getText().toString().trim();
        final String lastname = et_last.getText().toString().trim();
        final String mail = et_mail.getText().toString().trim();
        final String password = et_pass.getText().toString().trim();
        String confirm_password = et_confirm_pass.getText().toString().trim();


        if(TextUtils.isEmpty(mail)) {
            et_mail.setError(et_mail.getText() + getResources().getString(R.string.req));
            et_mail.requestFocus();
        }else{
            if(password.length()<6){
                et_pass.setError(et_pass.getText()+getResources().getString(R.string.pasreq));
                et_pass.requestFocus();
            }else{
                if(password.equals(confirm_password)){
                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);

                                User userinfo = new User(firstname,lastname,mail,password);
                                FirebaseUser user =mAuth.getCurrentUser();
                                String userId=user.getUid();

                                databaseReference.child(userId).setValue(userinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignUpActivity.this, getResources().getString(R.string.signcom),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                                    }
                                });

                                Log.d(TAG,"createUserWithEmail: success");
                                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                updateUI(firebaseUser);


                                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.emsent),Toast.LENGTH_SHORT).show();
                            }else{

                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignUpActivity.this,getResources().getString(R.string.emfail),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        }

    }
}
