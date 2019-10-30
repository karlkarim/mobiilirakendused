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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LOGIN:";

    private EditText etMail,etPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.indeterminateBar);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);


    }

    @Override
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


    public void onLogin(View view) {

        String email= etMail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            etMail.setError(etMail.getText() + getResources().getString(R.string.req));
            etMail.requestFocus();
        }

        else if(TextUtils.isEmpty(email))
        {
            etPassword.setError(etPassword.getText() + getResources().getString(R.string.req));
            etPassword.requestFocus();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);

                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                                startActivity((new Intent(getApplicationContext(),ProfileActivity.class)));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
        }

    }

    public void onSignUp(View view) {
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    public void onForgot(View view) {
        String email= etMail.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            etMail.setError(etMail.getText() + getResources().getString(R.string.req));
            etMail.requestFocus();
        }else{
            progressBar.setVisibility(View.VISIBLE);

            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.emsent),Toast.LENGTH_SHORT).show();
                    }else{

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,getResources().getString(R.string.emfail),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}
