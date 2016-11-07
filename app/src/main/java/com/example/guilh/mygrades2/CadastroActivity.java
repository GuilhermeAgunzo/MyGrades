package com.example.guilh.mygrades2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void save(View view){
        Disciplina d = new Disciplina();
        try{

            EditText campoNome = (EditText) findViewById(R.id.txtDisc);
            EditText campoP1 = (EditText) findViewById(R.id.valueP1);
            EditText campoP2 = (EditText) findViewById(R.id.valueP2);

            d.nome = String.valueOf(campoNome.getText());
            d.p1 = Float.parseFloat(String.valueOf(campoP1.getText()));
            d.p2 = Float.parseFloat(String.valueOf(campoP2.getText()));
        }catch(Exception e){
            Toast.makeText(this,R.string.warning,Toast.LENGTH_LONG).show();

        }
        //Gravar em arquivo

        String filename = d.nome;
        FileOutputStream output;

        try{
            output = openFileOutput(filename, Context.MODE_PRIVATE);
            output.write(("Nome da disciplina: " + d.nome + "\n").getBytes());
            output.write(("Nota P1: " + d.p1 + "\n").getBytes());
            output.write(("Nota P2: " + d.p2 + "\n").getBytes());
            output.close();

        }catch(Exception ex){
            Toast.makeText(this,"Erro ao gravar arquivo: " + ex.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();

    }
}
