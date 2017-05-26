package com.ii.is.aplicacion.aplicacionmovil;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText user,pass;
    private Button Entrar;
    private Usuario User;
    private RadioButton RUsuario,REmpresario;
    private Connection c;
    private Statement stmt;
    private ControlBase CB;
    private RadioGroup Group;
    private int turista = 0;
    private boolean conectado = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = new Usuario();
        user = (EditText)findViewById(R.id.User);
        pass = (EditText)findViewById(R.id.Pass);
        Entrar = (Button)findViewById(R.id.Entrar);
        Entrar.setOnClickListener(this);
        RUsuario = (RadioButton)findViewById(R.id.Turista);
        REmpresario = (RadioButton)findViewById(R.id.Empresario);
        Group = (RadioGroup)findViewById(R.id.radioGroup);
        Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId == R.id.Turista) turista = 1;
                    else if (checkedId == R.id.Empresario) turista = 2;
            }
        });
    }
    public String getNombreUser(){
        return user.getText().toString();
    }
    public void setConectado(boolean c){
        conectado = c;
    }
    public String getContrasenaUser(){
        return pass.getText().toString();
    }
    public void setturista(String nombre,String contrasena){
        User.setnombre(nombre);
        User.setcontrasena(contrasena);
    }
    public void setturista(String nombre,String contrasena,String rut){
        User = new Usuario(nombre,contrasena,rut);
    }
    public String getturistanombre(){
        return User.getnombre();
    }
    public String getturismorut(){
        return User.getrut();
    }
    public void setturista(){
        User = new Usuario();
    }
    public void onClick(View v) {
        this.setturista(user.getText().toString(),pass.getText().toString());
        CB = new ControlBase(this);
        switch (v.getId()){
            case R.id.Entrar:
                if(turista == 1){
                    CB.setTipo(1);
                    CB.ejecutar();
                }
                else if (turista == 2) {
                    CB.setTipo(2);
                    CB.ejecutar();
                }
                break;
        }
    }
    public void comprobarConexion(){
        if(conectado){
            Intent button_uno = new Intent (Login.this, Principal.class);
            button_uno.putExtra("TIPO",turista);
            button_uno.putExtra("USER",User.getrut());
            button_uno.putExtra("NOMBRE",User.getnombre());
            startActivity(button_uno);
            Toast.makeText(getApplicationContext(),"Conecte de manera correcta", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Error en usuario o contraseña", Toast.LENGTH_LONG).show();
        }
    }
}

