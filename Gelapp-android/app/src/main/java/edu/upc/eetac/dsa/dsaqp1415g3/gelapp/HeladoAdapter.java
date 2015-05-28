package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

import android.content.Context;
import android.graphics.Color;
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
        TextView tvCapa1;
        TextView tvCapa2;
        TextView tvCapa3;
        TextView tvCapa4;
        TextView tvCapa5;
        TextView tvVotos;
        TextView tvFechaCreacion;
        TextView ColorCapa1;
        TextView ColorCapa2;
        TextView ColorCapa3;
        TextView ColorCapa4;
        TextView ColorCapa5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row_helado, null);
            viewHolder = new ViewHolder();

            viewHolder.tvNombreHelado = (TextView) convertView.findViewById(R.id.tvNombreHelado);
            viewHolder.tvCapa1 = (TextView) convertView.findViewById(R.id.tvCapa1);
            viewHolder.tvCapa2 = (TextView) convertView.findViewById(R.id.tvCapa2);
            viewHolder.tvCapa3 = (TextView) convertView.findViewById(R.id.tvCapa3);
            viewHolder.tvCapa4 = (TextView) convertView.findViewById(R.id.tvCapa4);
            viewHolder.tvCapa5 = (TextView) convertView.findViewById(R.id.tvCapa5);
            viewHolder.tvVotos = (TextView) convertView.findViewById(R.id.tvVotos);
            viewHolder.tvFechaCreacion = (TextView) convertView.findViewById(R.id.tvFechaCreacion);
            viewHolder.ColorCapa1 = (TextView) convertView.findViewById(R.id.ColorCapa1);
            viewHolder.ColorCapa2 = (TextView) convertView.findViewById(R.id.ColorCapa2);
            viewHolder.ColorCapa3 = (TextView) convertView.findViewById(R.id.ColorCapa3);
            viewHolder.ColorCapa4 = (TextView) convertView.findViewById(R.id.ColorCapa4);
            viewHolder.ColorCapa5 = (TextView) convertView.findViewById(R.id.ColorCapa5);


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
        String votos = Integer.toString(data.get(position).getVotos());
        String creation_time = SimpleDateFormat.getInstance().format(data.get(position).getCreationTimestamp());


        viewHolder.tvNombreHelado.setText(nombreHelado);
        viewHolder.tvCapa1.setText(capa1);
        viewHolder.tvCapa2.setText(capa2);
        viewHolder.tvCapa3.setText(capa3);
        viewHolder.tvCapa4.setText(capa4);
        viewHolder.tvCapa5.setText(capa5);
        viewHolder.tvVotos.setText(votos);
        viewHolder.tvFechaCreacion.setText(creation_time);


        if (capa1.equals("caramelo"))
        {viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa1.equals("chocolate_blanco"))
        {viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa1.equals("chocolate_negro"))
        {viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa1.equals("sirope_fresa"))
        {viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa1.equals("multicolor"))
        {viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa3.equals("caramelo"))
        {viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa3.equals("chocolate_blanco"))
        {viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa3.equals("chocolate_negro"))
        {viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa3.equals("sirope_fresa"))
        {viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa3.equals("multicolor"))
        {viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa5.equals("caramelo"))
        {viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#FF8000"));}

        if (capa5.equals("chocolate_blanco"))
        {viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#E6E6E6"));}

        if (capa5.equals("chocolate_negro"))
        {viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#2A0A0A"));}

        if (capa5.equals("sirope_fresa"))
        {viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#FF0080"));}

        if (capa5.equals("multicolor"))
        {viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#00FFFF"));}


        if (capa2.equals("fresa"))
        {viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#FF0000"));}

        if (capa2.equals("nata"))
        {viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#F5F6CE"));}

        if (capa2.equals("vainilla"))
        {viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#F4FA58"));}

        if (capa2.equals("chocolate"))
        {viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#8A2908"));}

        if (capa2.equals("turron"))
        {viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#FFBF00"));}


        if (capa4.equals("fresa"))
        {viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#FF0000"));}

        if (capa4.equals("nata"))
        {viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#F5F6CE"));}

        if (capa4.equals("vainilla"))
        {viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#F4FA58"));}

        if (capa4.equals("chocolate"))
        {viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#8A2908"));}

        if (capa4.equals("turron"))
        {viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#FFBF00"));}



        return convertView;
    }


}