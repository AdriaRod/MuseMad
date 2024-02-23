package com.adga.musemad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.SignInMethodQueryResult;
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

        // Inicializar Firebase Auth y Firestore
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.email);
        passwd = findViewById(R.id.passw);
        btn_register = findViewById(R.id.aceptar);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passwdUser = passwd.getText().toString().trim();

                if (TextUtils.isEmpty(emailUser) || TextUtils.isEmpty(passwdUser)) {
                    Toast.makeText(Register.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else if (passwdUser.length() < 6) {
                    Toast.makeText(Register.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(emailUser, passwdUser);
                }
            }
        });
    }

    private void registerUser(String emailUser, String passwdUser) {

        mAuth.createUserWithEmailAndPassword(emailUser, passwdUser)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Registro exitoso, guardar información en Firestore
                            String id = mAuth.getCurrentUser().getUid();
                            Map<String, Object> userData = new HashMap<>();
                            userData.put("email", emailUser);

                            mFirestore.collection("users").document(id)
                                    .set(userData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            // Redirigir al usuario a la pantalla principal
                                            startActivity(new Intent(Register.this, MainActivity.class));
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Register.this, "Error al guardar información", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Error al registrar el usuario
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Register.this, "El correo ya está registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Register.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    // Método para abrir la actividad de inicio de sesión
    public void openLogin(View v) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}