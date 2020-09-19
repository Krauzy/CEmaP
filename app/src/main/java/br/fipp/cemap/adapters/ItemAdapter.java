package br.fipp.cemap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import br.fipp.cemap.R;
import br.fipp.cemap.tools.Adress;

public class ItemAdapter extends ArrayAdapter<Adress> {
    private int id_layout;
    public ItemAdapter(Context context, int resource, List<Adress> list) {
        super(context, resource, list);
        this.id_layout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(this.id_layout, parent, false);
        }
        TextView txtRua = convertView.findViewById(R.id.txtItemRua);
        TextView txtBairro = convertView.findViewById(R.id.txtItemBairro);
        TextView txtEstado = convertView.findViewById(R.id.txtItemEstado);
        TextView txtUF = convertView.findViewById(R.id.txtItemUF);
        TextView txtCEP = convertView.findViewById(R.id.txtItemCEP);
        Adress ad = this.getItem(position);
        txtRua.setText(ad.getLogradouro());
        txtBairro.setText(ad.getBairro());
        txtEstado.setText(ad.getLocalidade());
        txtUF.setText(ad.getUf());
        txtCEP.setText(ad.getCep());
        return convertView;
    }
}
