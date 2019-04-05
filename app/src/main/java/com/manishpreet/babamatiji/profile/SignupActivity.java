package com.manishpreet.babamatiji.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.manishpreet.babamatiji.MainActivity;
import com.manishpreet.babamatiji.R;

public class SignupActivity extends AppCompatActivity
        implements View.OnClickListener {


    EditText eUserName;
    EditText ePassword;
    EditText eAddress;
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

        eUserName =findViewById(R.id.edt_username);
        ePassword =findViewById(R.id.edt_password);
        eAddress =findViewById(R.id.edt_adress);
        eEmail =findViewById(R.id.edt_email);
        eContactno =findViewById(R.id.edt_contact);

        btnReg=findViewById(R.id.btnREGISTER);

        btnReg.setOnClickListener(this);
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
        final String address = eAddress.getText().toString().trim();
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

        if (address.isEmpty())
        {
            eAddress.setError("Enter Address");
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

                    final User user = new User(userName, address, contact, email,password,firebaseUser.getUid());
                    saveToDatabase();
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

    private void saveToDatabase() {
    }
}



