package com.jamc0011.miscontactos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contacto> contactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactos= new ArrayList<Contacto>();

        contactos.add(new Contacto("Anahi Salgado", "7777909", "anahi@gmail.com"));
        contactos.add(new Contacto("Pedro Sanchez", "223443", "pedro@gmail.com"));
        contactos.add(new Contacto("Mireya Martinez", "7544349", "mireya@gmail.com"));
        contactos.add(new Contacto("Juan Lopez", "7723459", "juan@gmail.com"));

        //arreglo para que solo tenga los nombres de contactos
        ArrayList<String> nombresContacto = new ArrayList<>();
        for (Contacto contacto : contactos) {
            nombresContacto.add(contacto.getNombre());
        }
        //ya que solo queremos que el ListView tenga nombres
        ListView lstContactos = (ListView) findViewById(R.id.lstContactos);
        lstContactos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresContacto));

        //Ahora pasamos a crear un intent explicito que reaccione al pinchar en la lista
        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetalleContacto.class);
                //meter intent que lleve parametros
                intent.putExtra(getResources().getString(R.string.pnombre), contactos.get(position).getNombre());
                intent.putExtra(getResources().getString(R.string.ptelefono), contactos.get(position).getTelefono());
                intent.putExtra(getResources().getString(R.string.pemail), contactos.get(position).getEmail());
                startActivity(intent);
                finish(); //se puso despues para ahorro de memoria del metodo onKeyDown
            }
        });
    }
}
