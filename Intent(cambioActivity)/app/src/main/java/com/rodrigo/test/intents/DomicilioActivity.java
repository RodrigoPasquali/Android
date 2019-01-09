package com.rodrigo.test.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class DomicilioActivity extends AppCompatActivity {
    private EditText etProvincia;
    private EditText etLocalidad;
    private EditText etCalle;
    private EditText etPiso;
    private EditText etDepartamento;
    private RadioButton rbEdificio;
    private RadioButton rbCasa;
    private String provincia;
    private String localidad;
    private String calle;
    private String piso;
    private String departamento;
    private LinearLayout llEdificio;
    private Button btnEnviarDom;
    private String vivienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domicilio);

        getElementosLayout();
        vivienda = "";
        setDatosDepartamento();
        enviarDatos();
    }

    private void getElementosLayout(){
        etProvincia = findViewById(R.id.etProvincia);
        etLocalidad = findViewById(R.id.etLocalidad);
        etCalle = findViewById(R.id.etCalle);
        etPiso = findViewById(R.id.etPiso);
        etDepartamento = findViewById(R.id.etDepartamento);
        rbEdificio = findViewById(R.id.rbEdificio);
        rbCasa = findViewById(R.id.rbCasa);
        llEdificio = findViewById(R.id.llEdificio);
        btnEnviarDom = findViewById(R.id.btnEnviarDom);
    }

    private void getInformacionLayout(){
        provincia = etProvincia.getText().toString();
        localidad = etLocalidad.getText().toString();
        calle = etCalle.getText().toString();
        piso = etPiso.getText().toString();
        departamento = etDepartamento.getText().toString();
    }

    /*
        El metodo espera a que se clickee en un radio button, si esto ocurre realiza algo, en este caso
        si el radio button seleccionado es el de edificio, entonces se hace visible los datos adicionales(piso y departamento).
     */
    private void setDatosDepartamento(){
        rbEdificio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                llEdificio.setVisibility(LinearLayout.VISIBLE);
                vivienda = "edificio";
                return false;
            }
        });

        rbCasa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                llEdificio.setVisibility(LinearLayout.INVISIBLE);
                vivienda = "casa";
                return false;
            }
        });
    }

    private void enviarDatos(){
        btnEnviarDom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DomicilioActivity.this, com.rodrigo.test.intents.MainActivity.class);
                getInformacionLayout();

                if (provincia.isEmpty() || localidad.isEmpty() || calle.isEmpty() || vivienda.isEmpty()) {
                    Toast.makeText(DomicilioActivity.this, "Por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
                } else {
                    intent.putExtra("provincia", provincia);
                    intent.putExtra("localidad", localidad);
                    intent.putExtra("calle", calle);
                    intent.putExtra("vivienda", vivienda);

                    if (rbEdificio.isChecked() == true) {
                        departamento = etDepartamento.getText().toString();
                        piso = etPiso.getText().toString();
                        if (departamento.isEmpty() || piso.isEmpty()){
                            Toast.makeText(DomicilioActivity.this, "Por favor ingrese datos del edificio.", Toast.LENGTH_SHORT).show();
                        } else {
                            intent.putExtra("departamento", departamento);
                            intent.putExtra("piso", piso);
                            setResult(RESULT_OK, intent);
                            DomicilioActivity.this.finish();
                        }
                    } else {
                        setResult(RESULT_OK, intent);
                        DomicilioActivity.this.finish();
                    }
                }
            }
        });
    }
}
