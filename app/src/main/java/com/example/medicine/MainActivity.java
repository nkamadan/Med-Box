package com.example.medicine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    Button register, signup;
    EditText username, pass;
    TextView dont;
    ProgressDialog prgdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logosign);
        register = findViewById(R.id.register);
        signup = findViewById(R.id.signup);
        username = findViewById(R.id.usersign);
        pass = findViewById(R.id.passwordsign);
        dont = findViewById(R.id.donthaveaccount);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("abc");
        //myRef.child(String.valueOf(hadi.numara)).setValue(hadi);
    }

    public void loginButton(View v){

        //DATABASE STUFF AND AUTHENTICATION OF THE USERS;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("Users");
        final String uname = username.getText().toString().trim();
        final String pswrd = pass.getText().toString().trim();
        final Query checkUser = reference.orderByChild("username").equalTo(uname); //Checking database if there is a username = uname
        Bundle bundle = new Bundle();
        bundle.putString("edttext", "From Activity");
        // set Fragmentclass Arguments
        Intent i = new Intent(this, FragmentActivity.class);
        i.putExtra("username", uname);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){ //Eger Query'miz bos degilse yani bulmussak
                    String passwordDB = dataSnapshot.child(uname).child("password").getValue(String.class);
                    //Bize verilen dataSnapshot'ta yani girilen username ile bulunan kullanicinin passwordunu aliyoruz.
                    if(passwordDB.equals(pswrd)){ //Username and password is registered in our DB, LOGIN SUCCESSFULL
                        String name = dataSnapshot.child(uname).child("name").getValue(String.class);
                        String surname = dataSnapshot.child(uname).child("surname").getValue(String.class);
                        String id = dataSnapshot.child(uname).child("userID").getValue().toString();
                        //PROGRESS DIALOG
                        prgdialog = new ProgressDialog(MainActivity.this);
                        prgdialog.setTitle("Loading...");
                        prgdialog.setMessage("Please wait...");
                        prgdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        prgdialog.show();

                        Runnable progress = new Runnable() {
                            @Override
                            public void run() {
                                prgdialog.cancel();
                            }
                        };
                        Handler dialogcanceller = new Handler();
                        dialogcanceller.postDelayed(progress,1000);
                        //PROGRESS DIALOG
                        //NEW ACTIVITY
                        startActivity(i);
                        
                    }

                    else{
                        alertDialog();
                        Log.println(Log.ERROR, "sad", "asd");
                    }

                }
                else{
                    alertDialog();

                    Log.println(Log.ERROR, "asfaw", "qwgq");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DATABASE ERROR:    ", pswrd);
            }
        });
    }

    public void signButton(View v){

        Intent i = new Intent(this, signup.class);
        startActivity(i);

    }

    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Username or Password is not correct!");
        dialog.setTitle("Login Error!");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}