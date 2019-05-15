package com.manishpreet.babamatiji.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.manishpreet.babamatiji.MainActivity;
import com.manishpreet.babamatiji.Prefrences;
import com.manishpreet.babamatiji.R;

public class SignupActivity extends AppCompatActivity
        implements View.OnClickListener {

    TextView already;
    EditText eUserName;
    EditText ePassword;
    EditText eEmail;
    EditText eContactno;
    Button btnReg;
    FirebaseAuth auth;
    ProgressDialog dialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        already=findViewById(R.id.already);
        eUserName =findViewById(R.id.edt_username);
        ePassword =findViewById(R.id.edt_password);
        eEmail =findViewById(R.id.edt_email);
        eContactno =findViewById(R.id.edt_contact);
        btnReg=findViewById(R.id.btnREGISTER);
        btnReg.setOnClickListener(this);
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnReg)
        {
            registerUser();
        }




    }

    private void registerUser() {

        final String userName = eUserName.getText().toString().trim();
        final String password = ePassword.getText().toString().trim();
        final String email = eEmail.getText().toString().trim();
        final String contact = eContactno.getText().toString().trim();


        //validation
        if(userName.isEmpty())
        {
            eUserName.setError("Enter Username");
            return;
        }

        if(password.isEmpty())
        {
            ePassword.setError("Enter Password");
            return;
        }

        if (email.isEmpty())
        {
            eEmail.setError("Enter Email");
            return;
        }

        if (contact.isEmpty() || (contact.length() != 10))
        {
            eContactno.setError("Enter a Valid Contact Number");
            return;
        }

        dialog.setMessage("Creating Account, Please Wait...");
        dialog.show();



        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser=task.getResult().getUser();

                    User user = new User(userName, contact, email,password,firebaseUser.getUid());
                    saveToDatabase(user);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void saveToDatabase(final User user) {
        FirebaseFirestore.getInstance().collection("users").document(user.uid).set(user)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Prefrences.saveUser(getApplicationContext(),user);
                dialog.dismiss();
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}



