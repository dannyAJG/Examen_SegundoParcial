package com.example.examen_segundoparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBorrar= findViewById(R.id.btnBorrar);
        Button btnAgregar= findViewById(R.id.btnAgregar);
        Button btnEditar = findViewById(R.id.btnEditar);
        EditText id_et = findViewById(R.id.id_et);
        EditText titulo_et = findViewById(R.id.titulo_et);
        EditText descripcion_et = findViewById(R.id.descripcion_et);
        CRUDRECETAS CRUD = new CRUDRECETAS(this);
        ArrayList<String> listaRecetitas = new ArrayList<String>();
        ListView listaRecetas;
        listaRecetas = findViewById(R.id.listaRecetas);
        Cursor informacion=CRUD.mostrarRecetas();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String elNombre= titulo_et.getText().toString();
                String laDescripcion= descripcion_et.getText().toString();
                if (elNombre.isEmpty() || laDescripcion.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        CRUD.insertarReceta(elNombre, laDescripcion);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Ingrese los datos faltantes", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });



        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elId= id_et.getText().toString();
                Integer idInt = Integer.parseInt(elId);
                CRUD.eliminarReceta(idInt);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CRUD.actualizarReceta(Integer.parseInt(id_et.getText().toString()),titulo_et.getText().toString(),descripcion_et.getText().toString());
            }
        });
        while (informacion.moveToNext()){
            String titulo= informacion.getString(1);
            listaRecetitas.add(titulo);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaRecetitas);
        listaRecetas.setAdapter(adaptador);





    }
}