package com.rodrigo.test.intents;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnPrincipales;
    private Button btnDomicilio;
    private Button btnPaises;
    private TextView tvDatosPrincipales;
    private TextView tvDatosDomicilio;
    private final int PRINCIPAL = 1;
    private final int DOMICILIO = 2;
    private final int PAISES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            Se asocia un layout a la Activity
         */
        setContentView(R.layout.activity_main);

        getElementosLayout();

        accionBtnDomicilio();
        accionBtnPrincipales();
        accionBtnPaises();
    }

    /*
        Se relaciones a variables del Activity con los elementos de sus respectivo layout
     */
    private void getElementosLayout(){
        btnPrincipales = findViewById(R.id.btnPrincipales);
        btnDomicilio = findViewById(R.id.btnDomicilio);
        btnPaises = findViewById(R.id.btnPaises);
        tvDatosPrincipales = findViewById(R.id.tvDatosPrincipales);
        tvDatosDomicilio = findViewById(R.id.tvDatosDomicilio);
    }

    private void accionBtnPrincipales(){
        /*
            Con este metodo, se esta a la espera de que se clicke el boton.
         */
        btnPrincipales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Cuando se clickea el boton, se crea el Intent que lleva la info desde la pantalla actual, a la siguiente(MainActivity a PrincipalActivity)
                 */
                Intent intent = new Intent(MainActivity.this, com.rodrigo.test.intents.PrincipalActivity.class);

                /*
                Comienza la actividad que se llama en el intent, esperando que le devuelva datos.
                 */
                startActivityForResult(intent, PRINCIPAL);
            }
        });
    }

    private void accionBtnDomicilio(){
        btnDomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.rodrigo.test.intents.DomicilioActivity.class);
                startActivityForResult(intent, DOMICILIO);
            }
        });
    }

    private void accionBtnPaises(){
        btnPaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.rodrigo.test.intents.PaisFavorito.class);
                startActivityForResult(intent, PAISES);
            }
        });
    }

    /*
        Se pasan los datos ingresados por el usuario en datos principales, para poder ser colocado en una cadena
        que luego sera mostrada en pantalla.
     */
    private String generarCadenaDeSalidaDatosPrincipales(Intent data){
        String textoSalida = "Datos Principales ingresados: \r \n" +
                "-NOMBRE: " + data.getStringExtra("nombre") + "    -F. NACIMIENTO: " + data.getStringExtra("fechaNacimiento") + "\r \n" +
                "-DNI: " + data.getStringExtra("dni") + "   -GENERO: " + data.getStringExtra("genero");

        return textoSalida;
    }

    private String generarCadenaDeSalidaDatosDomicilio(Intent data){
        String textoSalida = "Error";
        if (data.getStringExtra("vivienda").equals("edificio")){
            textoSalida = "Datos Domicilio ingresados: \r \n" +
                    "-PROVINCIA: " + data.getStringExtra("provincia") + "    -LOCALIDAD: " + data.getStringExtra("localidad") + "\r \n" +
                    "-CALLE: " + data.getStringExtra("calle") + "   -TIPO VIVIENDA: " + data.getStringExtra("vivienda") + "\r \n" +
                    "-PISO: " + data.getStringExtra("piso") + "   -DEPARTAMENTO: " + data.getStringExtra("departamento");
        } else {
            textoSalida = "Datos Domicilio ingresados: \r \n" +
                    "-PROVINCIA: " + data.getStringExtra("provincia") + "    -LOCALIDAD: " + data.getStringExtra("localidad") + "\r \n" +
                    "-CALLE: " + data.getStringExtra("dni") + "   -TIPO VIVIENDA: " + data.getStringExtra("vivienda");
        }

        return textoSalida;
    }

    /*
        Se obtiene los datos de las Activity que interactuan con esta(MainActivity)
        -requestCode --> es el numero de la Activity que esta retornando datos (en este caso PRINCIPAL = 1, y DOMICILIO = 2).
        -resultCode --> puede ser RESULT_OK o RESULT_CANCEL.
        -data --> datos traidos por el intent de la otra activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
            -RESULT_OK --> fue puesto cuando se envian los datos exitosamente pulsando el boton de enviar datos
            -RESULT_CANCEL --> cuando se presiona la tecla "Atras", aunque puede ser configurado
         */
        if(requestCode == PRINCIPAL && resultCode == RESULT_OK){
            /*
                Toast devuelve un mensaje en pantalla para indicar algo al usuario
             */
            Toast toast = Toast.makeText(MainActivity.this, "Datos Principales recibidos correctamente.", Toast.LENGTH_LONG);

            String cadenaSalida = generarCadenaDeSalidaDatosPrincipales(data);

            tvDatosPrincipales.setText(cadenaSalida);
            tvDatosPrincipales.setVisibility(tvDatosPrincipales.VISIBLE);
        }else if(requestCode == DOMICILIO && resultCode == RESULT_OK){
            Toast toast = Toast.makeText(MainActivity.this, "Datos Domicilio recibidos correctamente.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0 , 0 );
            toast.show();

            String cadenaSalida = generarCadenaDeSalidaDatosDomicilio(data);

            tvDatosDomicilio.setText(cadenaSalida);
            tvDatosDomicilio.setVisibility(tvDatosDomicilio.VISIBLE);
        }
    }
}
