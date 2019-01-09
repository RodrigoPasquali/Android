package com.rodrigo.test.intents;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PrincipalActivity extends AppCompatActivity {
    private Button btnEnviarPrin;
    private Button btnCancelar;
    private EditText etNombre;
    private EditText etDNI;
    private EditText etFechaNacimiento;
    private RadioButton rbHombre;
    private RadioButton rbMujer;
    private String nombre;
    private String dni;
    private String genero;
    private String fechaNacimiento;
    private final Calendar calendario = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        getElementosLayout();
        generarCalendario(etFechaNacimiento, calendario);

        enviarDatos();
        cancelar();
    }

    private void getElementosLayout(){
        btnEnviarPrin = findViewById(R.id.btnEnviarPrin);
        btnCancelar = findViewById(R.id.btnCancelar);
        etNombre = findViewById(R.id.etNombre);
        etDNI = findViewById(R.id.etDNI);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
    }

    private void getInformacionLayout(){
        nombre = etNombre.getText().toString();
        dni = etDNI.getText().toString();
        fechaNacimiento = etFechaNacimiento.getText().toString();
    }

    /*
        Genera el calendario que se visualiza al momento de clickear el campo para introducir la
        fecha de nacimiento
     */
    private void generarCalendario(final EditText et, final Calendar calendario){
        final DatePickerDialog.OnDateSetListener dpd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, monthOfYear);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizaCampo(et, calendario);
            }

        };

        et.setOnTouchListener(new View.OnTouchListener() {
            @Override

            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(PrincipalActivity.this, dpd, calendario
                            .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                            calendario.get(Calendar.DAY_OF_MONTH)).show();
                }
                return false;
            }
        });
    }

    /*
        Una vez que se elige la fecha del calendario, se coloca en el campo dicha fecha, con el formato
        seleccionado
     */
    private void actualizaCampo(EditText et, Calendar c) {
        /*
            Formato fecha dia/mes/año
         */
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        et.setText(sdf.format(c.getTime()));
    }

    /*
        Se valida que radio button fue presionado, en caso de que no haya sido asi, el genero se llena con vacio.
     */
    private void determinarGenero(){
        if(rbHombre.isChecked()){
            genero = "hombre";
        }else if (rbMujer.isChecked()){
            genero = "mujer";
        }else{
            genero = "";
        }
    }

    /*
        Cuando se clickea el boton CANCELAR, lanza un cartel de mensaje, donde advierte que se pierden
        los datos en caso de CANCELAR, para ello posee 2 botonos ( -OK --> vuelve a MainActivity
                                                                   -CANCELAR --> cierra el mensaje).
     */
    private void cancelar(){
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PrincipalActivity.this);
                builder.setMessage("Si CANCELA se perderan todos los datos ingresados");
                builder.setTitle("CANCELAR");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        PrincipalActivity.this.finish();
                    }
                });

                builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    /*
        Al momento de presionar el boton "Enviar Datos", si :
        -Todos los campos completos --> envia la informacion a la MainActivity
        -Falta completar algun campo --> se visualiza un mensaje en pantalla para que se completen
                                         todos los campos faltantes(mediante un Toast)
     */
    private void enviarDatos(){
        btnEnviarPrin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInformacionLayout();
                determinarGenero();

                /*
                    Validacion para saber si algun campo esta vacio
                 */
                if(nombre.isEmpty() || dni.isEmpty() || fechaNacimiento.isEmpty() || genero.isEmpty()){
                    Toast.makeText(PrincipalActivity.this, "Por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent =  new Intent(PrincipalActivity.this, com.rodrigo.test.intents.MainActivity.class);

                    /*
                        Los inten.putExtra(nombreConElQueSePasaElDato, nombreDeLaVariableActual) se utilizan para
                        llevar la data de esta activity a otra.
                     */
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("dni", dni);
                    intent.putExtra("genero", genero);
                    intent.putExtra("fechaNacimiento", fechaNacimiento);

                    /*
                        Se coloca el resultado(podria ser RESULT_CANCEL pero no tendria sentido) y se pasa los datos
                        a traves del intent.
                     */
                    setResult(RESULT_OK, intent);

                    /*
                        Se cierra la ventan actual para volver a la anterior.
                     */
                    PrincipalActivity.this.finish();
                }
            }
        });
    }
}
