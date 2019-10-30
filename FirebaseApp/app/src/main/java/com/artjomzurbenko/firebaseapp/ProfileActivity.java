package com.artjomzurbenko.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private  static  final  String TAG = "PROFILE: ";

    private ListView profile;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;

    private String userId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.signout){

            mAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.lstView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) Log.d(TAG,"onAuthStateChanged: signed in"+user.getUid());
                else Log.d(TAG,"onAuthStateChanged: signed out");
            }
        };

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 showData(dataSnapshot);
            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this,getResources().getString(R.string.nodata),Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null)
        {mAuth.removeAuthStateListener(authStateListener);}
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null)
        {
            Log.d(TAG,"Signed in: " + currentUser.getEmail());
        }

    }


    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User userDetails = new User();
            userDetails.setFirstname((ds.child(userId).getValue(User.class)).getFirstname());
            userDetails.setLastname((ds.child(userId).getValue(User.class)).getLastname());
            userDetails.setEmail((ds.child(userId).getValue(User.class)).getEmail());
            userDetails.setPassword((ds.child(userId).getValue(User.class)).getPassword());


            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(userDetails.getFirstname());
            arrayList.add(userDetails.getLastname());
            arrayList.add(userDetails.getEmail());
            arrayList.add(userDetails.getPassword());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

            profile.setAdapter(adapter);
        }

    }
}
