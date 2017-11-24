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
    private Controlador CB;
    private Button Entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Entrar = (Button)findViewById(R.id.Iniciar);
        Entrar.setOnClickListener(this);
        Log.e("redirect", "INICIEE");
    }

    @Override
    public void onClick(View v) {
        CB = new Controlador(this);
        switch (v.getId()){
            case R.id.Iniciar:
                CB.setTipo(1);
                CB.ejecutar();
                Log.e("redirect", "EJECUTEEEE");
                break;
        }
    }
    public void iniciar(){
        Intent button_uno = new Intent (Login.this, MapaEstablecimientos.class);
        startActivity(button_uno);
    }
}
