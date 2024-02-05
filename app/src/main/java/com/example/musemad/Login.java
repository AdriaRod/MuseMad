package com.example.musemad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class Login extends AppCompatActivity {

    Button b_invitado;
    Button btn_login;
    EditText email, passwd;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //instancias
        mAuth = FirebaseAuth.getInstance();

        //declaraciones
        b_invitado = findViewById(R.id.invitado);
        btn_login = findViewById(R.id.aceptar);
        email = findViewById(R.id.email);
        passwd = findViewById(R.id.passw);


        //uso del glide
        Glide.with(this).load(R.drawable.fondologin)
                .transform(new BlurTransformation(25))
                .into((ImageView) findViewById(R.id.backgroundImage));

        //funcion del boton invitado
        b_invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogButtonClicked();
            }
        });

        //firebase LOGIN
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passwdUser = passwd.getText().toString().trim();

                if(emailUser.isEmpty() && passwdUser.isEmpty()){
                    Toast.makeText(Login.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailUser, passwdUser);
                }
            }
        });





    } //fin del onCreate

    private void loginUser(String emailUser, String passwdUser) {

        mAuth.signInWithEmailAndPassword(emailUser, passwdUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Toast.makeText(Login.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openMain(View v) {
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void openSignup(View v) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    public void showAlertDialogButtonClicked(){

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);

        builder.setTitle("Titulo");
        builder.setMessage("Mensaje");
        //builder.setIcon();
        builder.setCancelable(false);

        builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}//fin de las class Login
