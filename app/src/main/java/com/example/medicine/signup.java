package com.example.medicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class signup extends AppCompatActivity {
    ImageView logo;
    EditText user, pass;
    Button signup;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    boolean isSuccess =false;
    ProgressDialog prgdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        logo = findViewById(R.id.logosign);
        user = findViewById(R.id.usersign);
        pass = findViewById(R.id.passwordsign);
        signup = findViewById(R.id.createAccount);
    }

    public void createButton(View v){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User newUser = new User();
                if(dataSnapshot.exists()){
                    String username = user.getText().toString();
                    String password = pass.getText().toString();
                    newUser.username = username;
                    newUser.password = password;
                    //newUser.userID = ID;
                    reference.child(username).setValue(newUser); //Reference'imiz suan Users'ta. Users'a childlari username olarak ekliyoruz.
                    isSuccess = true;
                }

                else{
                    String username = user.getText().toString();
                    String password = pass.getText().toString();
                    newUser.username = username;
                    newUser.password = password;
                    //newUser.userID = ID;
                    reference.child(username).setValue(newUser); //Reference'imiz suan Users'ta. Users'a childlari username olarak ekliyoruz.
                    isSuccess = true;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isSuccess = false;
            }
        });



        //PROGRESS DIALOG
        prgdialog = new ProgressDialog(signup.this);
        prgdialog.setTitle("Loading...");
        prgdialog.setMessage("Please wait...");
        prgdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prgdialog.show();
        Toast success = Toast.makeText(getApplicationContext(), "Registered successfully!",Toast.LENGTH_SHORT);
        success.show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

        Runnable progress = new Runnable() {
            @Override
            public void run() {
                prgdialog.cancel();
            }
        };
//        Handler dialogcanceller = new Handler();
//        if(isSuccess == true){
//            dialogcanceller.postDelayed(progress,500);
//            Toast success = Toast.makeText(getApplicationContext(), "Registered successfully!",Toast.LENGTH_SHORT);
//            success.show();
//            //prgdialog.dismiss();
//        }
//        else{
//
//        }
        //PROGRESS DIALOG
    }

}