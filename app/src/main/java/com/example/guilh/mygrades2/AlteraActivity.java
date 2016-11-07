package com.example.guilh.mygrades2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class AlteraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera);

        Intent i = getIntent();

        String filename = i.getStringExtra("filename");

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setText(filename);
    }

    public void altera(View view){

        Disciplina d = new Disciplina();

        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        TextView textView = (TextView) findViewById(R.id.textView);

        String filename = textView.getText().toString();
        d.p1 = Float.parseFloat(String.valueOf(editText.getText()));
        d.p2 = Float.parseFloat(String.valueOf(editText2.getText()));

        FileOutputStream output;

        try{
            output = openFileOutput(filename, Context.MODE_PRIVATE);
            output.write(("Nome da disciplina: " + filename + "\n").getBytes());
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
