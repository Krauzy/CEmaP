package br.fipp.BuscaCep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.fipp.BuscaCep.R;

public class CepAdapter extends ArrayAdapter<CEP>
{   private int layout;
    public CepAdapter(Context context, int resource, List<CEP> ceps)
    {
        super(context, resource, ceps);
        layout=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView txtRua = convertView.findViewById(R.id.txtRua);
        TextView txtBairro = convertView.findViewById(R.id.txtBairro);
        TextView txtCidade = convertView.findViewById(R.id.txtCidade);
        TextView txtUF = convertView.findViewById(R.id.txtUF);
        TextView txtCEP = convertView.findViewById(R.id.txtCEP);
        CEP cep = this.getItem(position);
        String log = cep.getLogradouro();
        if(log.length() > 23)
            log = log.substring(0, 23) + "...";
        txtRua.setText(log);
        txtBairro.setText(cep.getBairro());
        txtCidade.setText(cep.getLocalidade());
        txtUF.setText(cep.getUF());
        txtCEP.setText(cep.getCep());
        return convertView;
    }
}
