package com.example.guilh.mygrades2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    File[] dirFiles;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ArrayList<String> files = new ArrayList<>();
        File dir = getFilesDir();
        dirFiles = dir.listFiles();
        for (int i = 0; i < dirFiles.length; i++) {
            if(!dirFiles[i].isDirectory()) {
                files.add(dirFiles[i].getName());
            }
        }

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos",0);

        TextView t = (TextView) findViewById(R.id.txtDetails);

        filename = DeleteActivity.this.dirFiles[pos].getName();

        try{
            BufferedReader in = new BufferedReader(new FileReader(DeleteActivity.this.dirFiles[pos]));
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            in.close();
            t.setText(text);
        }catch(Exception ex){
            Toast.makeText(this,"Erro ao carregar arquivo: " + ex.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View view){
        Context context = getApplicationContext();
        context.deleteFile(filename);
        finish();
    }
}
