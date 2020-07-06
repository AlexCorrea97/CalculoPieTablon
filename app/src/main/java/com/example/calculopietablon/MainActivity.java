package com.example.calculopietablon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {
    RadioButton rdnPPP;
    RadioButton rdnPPM;
    RadioButton rdnMMM;
    EditText editPrecio;
    EditText editAncho;
    EditText editEspesor;
    EditText editLargo;
    Button btnCalcular;
    TextView tv1;
    TextView tv2;
    TextView unidad1;
    TextView unidad2;
    TextView unidad3;
    double resultado;
    double resultado2;
    double espesor;
    double ancho;
    double largo;
    double precioPieza;
    String resultdoSTR;
    String resultdoSTR2;
    MediaPlayer music;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdnMMM=findViewById(R.id.rbnMMM);
        rdnPPM=findViewById(R.id.rbnPPM);
        rdnPPP=findViewById(R.id.rbnPPP);
        editPrecio=findViewById(R.id.editprecio);
        editAncho=findViewById(R.id.editancho);
        editEspesor=findViewById(R.id.editespesor);
        editLargo=findViewById(R.id.editlargo);
        btnCalcular=findViewById(R.id.button);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        unidad1=findViewById(R.id.unidad1);
        unidad2=findViewById(R.id.unidad2);
        unidad3=findViewById(R.id.unidad3);
        //music=MediaPlayer.create(this,R.raw.music);
        editAncho.setText("");
        editEspesor.setText("");
        editLargo.setText("");
        tv1.setText("");
        tv2.setText("");
        rdnPPP.setChecked(true);
        /*int RECORD_AUDIO = checkSelfPermission(Manifest.permission.RECORD_AUDIO );
        int STORAGE = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE );
        if(RECORD_AUDIO != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "TODO MAL", Toast.LENGTH_SHORT).show();

        }
        if(STORAGE!= PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "TODO MAL", Toast.LENGTH_SHORT).show();

        }*/

        rdnPPP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(rdnMMM.isChecked()){
                unidad1.setText("m");
                unidad2.setText("m");
                unidad3.setText("m");

            }
            else if(rdnPPM.isChecked()){
                unidad1.setText("inch");
                unidad2.setText("inch");
                unidad3.setText("m");
            }
            else if(rdnPPP.isChecked()){
                unidad1.setText("inch");
                unidad2.setText("inch");
                unidad3.setText("ft");
            }
            }
        });
        rdnMMM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdnMMM.isChecked()){
                    unidad1.setText("m");
                    unidad2.setText("m");
                    unidad3.setText("m");

                }
                else if(rdnPPM.isChecked()){
                    unidad1.setText("inch");
                    unidad2.setText("inch");
                    unidad3.setText("m");
                }
                else if(rdnPPP.isChecked()){
                    unidad1.setText("inch");
                    unidad2.setText("inch");
                    unidad3.setText("ft");
                }
            }

        });
        rdnPPM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdnMMM.isChecked()){
                    unidad1.setText("m");
                    unidad2.setText("m");
                    unidad3.setText("m");

                }
                else if(rdnPPM.isChecked()){
                    unidad1.setText("inch");
                    unidad2.setText("inch");
                    unidad3.setText("m");
                }
                else if(rdnPPP.isChecked()){
                    unidad1.setText("inch");
                    unidad2.setText("inch");
                    unidad3.setText("ft");
                }
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // music.start();
                if(editAncho.getText().toString().equals("")||editEspesor.getText().toString().equals("")||editLargo.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Debes especificar el espesor, largo y ancho", Toast.LENGTH_SHORT).show();
                    tv1.setText("");
                    tv2.setText("");
                }

                else {
                    ancho=Double.valueOf(editAncho.getText().toString());
                    espesor=Double.valueOf(editEspesor.getText().toString());
                    largo=Double.valueOf(editLargo.getText().toString());
                    if (rdnPPP.isChecked()) {
                    resultado=(ancho*espesor*largo)/12;

                    } else if (rdnPPM.isChecked()) {
                        resultado=(ancho*espesor*largo)/3.657;

                    } else if (rdnMMM.isChecked()) {

                        resultado=((ancho/0.0254)*(espesor/0.0254)*largo)/3.657;

                    }

                    BigDecimal bigDecimal = new BigDecimal(resultado).setScale(3, RoundingMode.UP);
                    resultado= bigDecimal.doubleValue();
                    resultdoSTR= resultado+" Pie(s) Tablón";
                    tv1.setText(resultdoSTR);
                    if(editPrecio.getText().toString().equals("")){
                        precioPieza=0;
                    }
                    else{
                        precioPieza=Double.valueOf(editPrecio.getText().toString());
                        resultado2=precioPieza/(resultado);
                        BigDecimal bigDecimal2 = new BigDecimal(resultado2).setScale(3, RoundingMode.UP);
                        resultdoSTR2="El precio por Pie Tablon es: "+bigDecimal2.doubleValue();
                        tv2.setText(resultdoSTR2);
                    }

                }
                closeTeclado();
            }
        });


    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    private void closeTeclado() {
    View view=this.getCurrentFocus();
    if(view!=null){
        InputMethodManager him=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        him.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    }
}
