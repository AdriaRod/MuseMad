package com.example.musemad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Register extends AppCompatActivity {

    private EditText email, passwd;
    private Button btn_register;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Glide.with(this).load(R.drawable.fondologin)
                .transform(new BlurTransformation(25))
                .into((ImageView) findViewById(R.id.backgroundImage));

        //codigo para desarrollar el registro en Firebase

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        passwd = findViewById(R.id.passw);
        btn_register = findViewById(R.id.aceptar);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passwdUser = passwd.getText().toString().trim();

                if(emailUser.isEmpty() && passwdUser.isEmpty()){
                    Toast.makeText(Register.this, "Complete los datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(emailUser, passwdUser);
                }
            }
        });

    }

    private void registerUser(String emailUser, String passwdUser) {

        mAuth.createUserWithEmailAndPassword(emailUser, passwdUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id =  mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("email", emailUser);


                mFirestore.collection("user").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        startActivity(new Intent(Register.this, MainActivity.class));
                        Toast.makeText(Register.this, "USUARIO REGISTRADO CON EXITO", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openLogin(View v) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}