package com.adga.musemad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adga.musemad.MainActivity;
import com.adga.musemad.R;
import com.adga.musemad.Register;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Login extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button loginButton;
    TextView signupButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.passw);
        loginButton = findViewById(R.id.aceptar);
        signupButton = findViewById(R.id.register);

        // Cargar la imagen de fondo
        Glide.with(this).load(R.drawable.fondologin)
                .transform(new BlurTransformation(25))
                .into((ImageView) findViewById(R.id.backgroundImage));

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores ingresados por el usuario
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validar si los campos están vacíos
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    // Mostrar un Toast indicando que se deben completar los campos
                    Toast.makeText(Login.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();

                    // Cambiar el color de los campos a rojo
                    if (TextUtils.isEmpty(email)) {
                        emailEditText.setError("Campo obligatorio");
                    }
                    if (TextUtils.isEmpty(password)) {
                        passwordEditText.setError("Campo obligatorio");
                    }
                } else {
                    // Si los campos no están vacíos, intentar iniciar sesión
                    loginUser(email, password);
                }
            }
        });
    }

    public void accionInvitado(View v){

        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public void openSignup(View v) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}