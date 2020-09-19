package br.fipp.cemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.fipp.cemap.tools.Adress;
import br.fipp.cemap.tools.WSTask;

public class MainActivity extends AppCompatActivity {
    public static String uf;
    private EditText txtCidade;
    private Spinner snpEstado;
    private EditText txtRua;
    private Button btBuscar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCidade = findViewById(R.id.txtCidade);
        txtRua = findViewById(R.id.txtItemRua);
        snpEstado = findViewById(R.id.spnEstado);
        btBuscar = findViewById(R.id.btBuscar);
        listView = findViewById(R.id.listView);
        //listView.setVisibility(View.INVISIBLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_estados_array, R.layout.support_simple_spinner_dropdown_item);
        snpEstado.setAdapter(adapter);
        snpEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                CharSequence c =  (CharSequence) parent.getItemAtPosition(pos);
                MainActivity.uf = c.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API();
            }
        });
    }

    private void API() {
        //listView.setVisibility(View.VISIBLE);
        String cidade = txtCidade.getText().toString();
        String rua = txtRua.getText().toString();
        String uf = MainActivity.uf;
        WSTask task = new WSTask();
        try {
            Toast.makeText(this, "adsadasd", Toast.LENGTH_LONG);
            String JSON = task.execute("https://viacep.com.br/ws/" + uf +"/" + cidade + "/" + rua + "/json/").get();
            //Adress adress = new Adress(JSON, this);
            Toast.makeText(this, JSON, Toast.LENGTH_LONG);

        } catch (ExecutionException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        } catch (InterruptedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
        }
    }
}