package www.runa.puebaiii;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PerfilUsuario extends AppCompatActivity {

    Button btnVerDaatosFormulario,btnGenerarPuntos,button_Guardar,btnBusquedaLibre;
    ////
    EditText editLugarV, editLatitud, editLongitud;
    ////
    ConexFom bdformulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);



        btnVerDaatosFormulario = (Button)findViewById(R.id.btnVerDaatosFormulario);
        btnBusquedaLibre = (Button)findViewById(R.id.btnBusquedaLibre);
        button_Guardar = (Button)findViewById(R.id.btnGuardar);
        btnGenerarPuntos = (Button)findViewById(R.id.btnGenerarPuntos);
     ////////////////////////////////////////////////////////////////////////////////////////////
        editLugarV = (EditText)findViewById(R.id.editLugarV);
        editLatitud = (EditText)findViewById(R.id.editLatitud);
        editLongitud = (EditText)findViewById(R.id.editLongitud);

     ////////////////////////////////////////////////////////////////////////////////////////////
        bdformulario = new ConexFom(this);

        ////////////////////////////////////////////////////////////////////////////////////////////
        btnGenerarPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GuardarPuntos.class);
                startActivity(intent);
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////

     ////////////////////////////////////////////////////////////////////////////////////////////
        btnVerDaatosFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

     ////////////////////////////////////////////////////////////////////////////////////////////
        btnBusquedaLibre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
     ////////////////////////////////////////////////////////////////////////////////////////////

        verDatos();
        AgregarDatos();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private void AgregarDatos() {
        button_Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String LugarV = editLugarV.getText().toString();
                String Longitud = editLongitud.getText().toString();
                String Latitud = editLatitud.getText().toString();

                if(validacionDatos())
                {

                    boolean AGREGARDATOS=bdformulario.AgregarDatos(LugarV,Longitud,Latitud);
                    if(AGREGARDATOS)
                    {
                        Toast.makeText(getApplicationContext(),"Datos Ingresados de forma EXITOSA",Toast.LENGTH_LONG).show();
                        editLugarV.setText("");


                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Datos no ingresados",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validacionDatos()
    {
        if(editLugarV.getText().toString().isEmpty())
        {
            editLugarV.setError("Ingrese sus  Comentarios");
            editLugarV.requestFocus();
            return false;
        }

        if(editLongitud.getText().toString().isEmpty())
        {
            editLongitud.setError("Coordenadas Longitud no ingresadas");
            editLongitud.requestFocus();

            return false;
        }
        if(editLatitud.getText().toString().isEmpty())
        {
            editLatitud.setError("Coordenadas Latitud no ingresadas");
            editLatitud.requestFocus();

            return false;
        }
        else
        {
            return true;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    public void MostrarMensajes(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private void verDatos() {
        btnVerDaatosFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor ver = bdformulario.verDatos();
                if(ver.getCount()==0)
                {
                    MostrarMensajes("Nop","No se encontro nada");
                }
                StringBuffer buffer=new StringBuffer();
                while (ver.moveToNext())
                {
                    buffer.append("id: "+ver.getString(0)+"\n");
                    buffer.append("Comentarios: "+ver.getString(1)+"\n");
                    buffer.append("Longitud: "+ver.getString(2)+"\n");
                    buffer.append("Latitud: "+ver.getString(3)+"\n");


                }
                MostrarMensajes("Datos",buffer.toString());
            }
        });
    }


}