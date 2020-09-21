package br.fipp.BuscaCep;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlanilhaActivity extends AppCompatActivity {
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planilha);
        listview=findViewById(R.id.listview);
        List<CEP> ceps = this.getAPI();
        CepAdapter adapter = new CepAdapter(this,R.layout.item_lista, ceps);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CEP c = (CEP)listview.getItemAtPosition(i);
                initListView(c);
            }
        });

    }

    private void initListView(CEP c) {
        Intent it = new Intent(this,ConsultaActivity.class);
        it.putExtra("localidade",c.getLocalidade());
        it.putExtra("UF",c.getUF());
        it.putExtra("lag",c.getLogradouro());
        it.putExtra("bairro",c.getBairro());
        it.putExtra("cep",c.getCep());
        it.putExtra("ddd",c.getDdd());
        it.putExtra("gia",c.getGia());
        it.putExtra("ibge",c.getIbge());
        it.putExtra("siafi",c.getSiafi());
        startActivity(it);
    }


    private List<CEP> getAPI() {
        List<CEP> lista = new ArrayList<>();
        String UF = getIntent().getStringExtra("UF");
        String cidade = getIntent().getStringExtra("cidade");
        String lag = getIntent().getStringExtra("lag");
        AcessaWsTask task= new AcessaWsTask();
        try {
            String url = task.execute("https://viacep.com.br/ws/" + UF + "/" + cidade + "/" + lag + "/json/").get();
            JSONArray JSON = new JSONArray(url);
            for(int i = 0; i < JSON.length(); i++) {
                JSONObject o = JSON.getJSONObject(i);
                lista.add(new CEP(o.getString("cep"),
                        o.getString("logradouro"),
                        o.getString("bairro"),
                        o.getString("localidade"),
                        o.getString("uf"),
                        o.getString("ddd"),
                        o.getString("ibge"),
                        o.getString("gia"),
                        o.getString("siafi"))
                );
            }
        }
        catch (Exception e) {
            Log.e("ERROR: ", e.toString());
        }
        return lista;
    }
}