package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;

/**
 * Created by marc on 13/05/15.
 */
public class HeladoAdapter extends BaseAdapter {

    ArrayList<Helado> data;
    LayoutInflater inflater;

    public HeladoAdapter(Context context, ArrayList<Helado> data) {
        super();
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Helado) getItem(position)).getHeladoid();
    }

    private static class ViewHolder {
        TextView tvNombreHelado;
        //TextView tvCreationTime;
        TextView tvCapa1;
        TextView tvCapa2;
        TextView tvCapa3;
        TextView tvCapa4;
        TextView tvCapa5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_helado, null);
            viewHolder = new ViewHolder();

            viewHolder.tvNombreHelado = (TextView) convertView.findViewById(R.id.tvNombreHelado);
            //viewHolder.tvCreationTime = (TextView) convertView.findViewById(R.id.tvCreationTime);
            viewHolder.tvCapa1 = (TextView) convertView.findViewById(R.id.tvCapa1);
            viewHolder.tvCapa2 = (TextView) convertView.findViewById(R.id.tvCapa2);
            viewHolder.tvCapa3 = (TextView) convertView.findViewById(R.id.tvCapa3);
            viewHolder.tvCapa4 = (TextView) convertView.findViewById(R.id.tvCapa4);
            viewHolder.tvCapa5 = (TextView) convertView.findViewById(R.id.tvCapa5);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String nombreHelado = data.get(position).getNombreHelado();
        String capa1 = data.get(position).getCapa1Topping();
        String capa2 = data.get(position).getCapa2Helado();
        String capa3 = data.get(position).getCapa3Topping();
        String capa4 = data.get(position).getCapa4Helado();
        String capa5 = data.get(position).getCapa5Topping();
        //String creation_time = SimpleDateFormat.getInstance().format(data.get(position).getLastModified());

        viewHolder.tvNombreHelado.setText(nombreHelado);
        viewHolder.tvCapa1.setText(capa1);
        viewHolder.tvCapa2.setText(capa2);
        viewHolder.tvCapa3.setText(capa3);
        viewHolder.tvCapa4.setText(capa4);
        viewHolder.tvCapa5.setText(capa5);
        //viewHolder.tvCreationTime.setText(creation_time);
        return convertView;
    }
}