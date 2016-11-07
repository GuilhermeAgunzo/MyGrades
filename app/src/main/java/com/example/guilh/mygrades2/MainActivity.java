package com.example.guilh.mygrades2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    File[] dirFiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> files = new ArrayList<>();
        File dir = getFilesDir();
        dirFiles = dir.listFiles();
        for (int i = 0; i < dirFiles.length; i++) {
            if(!dirFiles[i].isDirectory()) {
                files.add(dirFiles[i].getName());
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        ListView list = (ListView)findViewById(R.id.listDisc);
        list.setAdapter(aa);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String filename = MainActivity.this.dirFiles[i].getName();
                AlertDialog.Builder fileDialog = new AlertDialog.Builder(MainActivity.this);
                fileDialog.setTitle(filename);
                try{
                    BufferedReader in = new BufferedReader(new FileReader(MainActivity.this.dirFiles[i]));
                    StringBuilder text = new StringBuilder();
                    String line;
                    while ((line = in.readLine()) != null) {
                        text.append(line);
                        text.append('\n');
                    }
                    in.close();
                    fileDialog.setMessage(text);
                }catch(Exception ex){
                    fileDialog.setMessage("Erro ao carregar arquivo: "+ex.getLocalizedMessage());
                }
                fileDialog.setNeutralButton("Fechar", null);
                fileDialog.show();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),DeleteActivity.class);
                intent.putExtra("pos",i);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> files = new ArrayList<>();
        File dir = getFilesDir();
        dirFiles = dir.listFiles();
        for (int i = 0; i < dirFiles.length; i++) {
            if(!dirFiles[i].isDirectory()) {
                files.add(dirFiles[i].getName());
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        ListView list = (ListView)findViewById(R.id.listDisc);
        list.setAdapter(aa);
    }

    public void initCadastro(View view){
        Intent i = new Intent(getApplicationContext(),CadastroActivity.class);
        startActivity(i);
    }
}
