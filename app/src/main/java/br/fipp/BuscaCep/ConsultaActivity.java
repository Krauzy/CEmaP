package br.fipp.BuscaCep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import br.fipp.BuscaCep.R;

public class ConsultaActivity extends AppCompatActivity {

    private TextView uf,cep,localidade,bairro,log,ibge,ddd,siafi;
    private Button btnmapa;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        uf = findViewById(R.id.UF);
        cep = findViewById(R.id.CEP);
        localidade = findViewById(R.id.Loca);
        bairro = findViewById(R.id.bairro);
        log = findViewById(R.id.log);
        ibge = findViewById(R.id.ib);
        ddd = findViewById(R.id.ddd);
        siafi = findViewById(R.id.sia);
        btnmapa = findViewById(R.id.btnMapa);
        localidade.setText(getIntent().getStringExtra("localidade"));
        uf.setText(getIntent().getStringExtra("UF"));
        log.setText(getIntent().getStringExtra("lag"));
        bairro.setText(getIntent().getStringExtra("bairro"));
        cep.setText(getIntent().getStringExtra("cep"));
        ddd.setText(getIntent().getStringExtra("ddd"));
        ibge.setText(getIntent().getStringExtra("ibge"));
        siafi.setText(getIntent().getStringExtra("siafi"));
        context = this;
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcessaWsTask task = new AcessaWsTask();
                String http="https://maps.googleapis.com/maps/api/geocode/json?address=" + log.getText() + "," + localidade.getText() + "," + uf.getText() + "&key=AIzaSyDzSUq3DHkGoteKs8OrbutrQn_vTmT6gso";
                try {
                    String json=task.execute(http).get();
                    JSONObject o = new JSONObject(json);
                    JSONArray a = o.getJSONArray("results");
                    o = a.getJSONObject(0);
                    o = o.getJSONObject("geometry").getJSONObject("location");
                    Intent it = new Intent(context, MapActivity.class);
                    it.putExtra("lat", o.getDouble("lat"));
                    it.putExtra("lng", o.getDouble("lng"));
                    startActivity(it);
                }
                catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
            }
        });
    }
/*
    private void AcessaAPIGEO() {
        Log.println(Log.WARN, "WARN", "entrou");

    }
*/
}