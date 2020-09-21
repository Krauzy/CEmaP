package br.fipp.BuscaCep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import br.fipp.BuscaCep.R;


public class MainActivity extends AppCompatActivity {
    private EditText etCid,etLag;
    private Spinner spEst;
    private Button btcep;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCid = findViewById(R.id.txtCid);
        etLag = findViewById(R.id.txtLag);
        spEst = findViewById(R.id.spiEst);
        List<String> UF= Arrays.asList("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, UF);
        spEst.setAdapter(adapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spEst);
            popupWindow.setHeight(80);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        btcep = findViewById(R.id.btcep);
        this.context = this;
        btcep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etCid.getText().length() > 0 && etLag.getText().length() > 0 ) {
                    CEP cep = new CEP();
                    cep.setUF(spEst.getSelectedItem().toString());
                    cep.setLocalidade(etCid.getText().toString());
                    cep.setLogradouro(etLag.getText().toString());
                    Intent intent = new Intent(context, PlanilhaActivity.class);
                    intent.putExtra("cidade",etCid.getText().toString());
                    intent.putExtra("UF",spEst.getSelectedItem().toString());
                    intent.putExtra("lag",etLag.getText().toString());
                    startActivity(intent);
                }
                else
                    Toast.makeText(context, "Preencha os dados!", Toast.LENGTH_SHORT).show();
            }
        });
        SharedPreferences prefs=getSharedPreferences("config", MODE_PRIVATE);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_Sair:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}