package com.manishpreet.babamatiji.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.manishpreet.babamatiji.MainActivity;
import com.manishpreet.babamatiji.Prefrences;
import com.manishpreet.babamatiji.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


TextView txt_signup, tvLogin;
EditText edtPassword, edtEmail;

FirebaseAuth auth;
ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


           //initialization
           tvLogin = findViewById(R.id.tv_login);
           edtEmail = findViewById(R.id.edt_email_address);
           edtPassword = findViewById(R.id.edt_pass);
           txt_signup=findViewById(R.id.txt_signup);

           auth= FirebaseAuth.getInstance();
           dialog=new ProgressDialog(this);


           tvLogin.setOnClickListener(this);
           txt_signup.setOnClickListener(this);

       }


    @Override
    public void onClick(View v) {

        if (v == tvLogin)
        {
            loginUser();
        }

        if (v == txt_signup)
        {
            Intent intent=new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        }



    }

    private void loginUser() {

        String password = edtPassword.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();




        if (email.isEmpty())
        {
            edtEmail.setError("Enter Email");
            return;
        }



        if(password.isEmpty())
        {
            edtPassword.setError("Enter Password");
            return;
        }

        dialog.setMessage("Logging In...");
        dialog.show();


        auth.signInWithEmailAndPassword(email,password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    getUserfromDatabase(task.getResult().getUser().getUid());

                }
            }
        });



    }

    private void getUserfromDatabase(String uid) {
        FirebaseFirestore.getInstance().collection("usres")
                .document(uid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful())
                        {
                            User user=task.getResult().toObject(User.class);
                            Prefrences.saveUser(LoginActivity.this,user);
                            Toast.makeText(LoginActivity.this, "User Login Successfull", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                         }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
