package edu.upc.eetac.dsa.dsaqp1415g3.gelapp;

/**
 * Created by marc on 8/06/15.
 */
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upc.eetac.dsa.dsaqp1415g3.gelapp.api.Helado;

/**
 * Created by marc on 13/05/15.
 */
public class HeladoRankingAdapter extends BaseAdapter {

    ArrayList<Helado> data;
    LayoutInflater inflater;
    private int contador = 1;


    public HeladoRankingAdapter(Context context, ArrayList<Helado> data) {
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
        TextView tvAutor;
        TextView tvVotos;
        TextView ColorCapa1;
        TextView ColorCapa2;
        TextView ColorCapa3;
        TextView ColorCapa4;
        TextView ColorCapa5;
        TextView tvCont;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.ranking_row_helado, null);
            viewHolder = new ViewHolder();

            viewHolder.tvNombreHelado = (TextView) convertView.findViewById(R.id.tvNombreHelado);
            viewHolder.tvAutor = (TextView) convertView.findViewById(R.id.tvAutor);
            viewHolder.tvVotos = (TextView) convertView.findViewById(R.id.tvVotos);
            viewHolder.ColorCapa1 = (TextView) convertView.findViewById(R.id.ColorCapa1);
            viewHolder.ColorCapa2 = (TextView) convertView.findViewById(R.id.ColorCapa2);
            viewHolder.ColorCapa3 = (TextView) convertView.findViewById(R.id.ColorCapa3);
            viewHolder.ColorCapa4 = (TextView) convertView.findViewById(R.id.ColorCapa4);
            viewHolder.ColorCapa5 = (TextView) convertView.findViewById(R.id.ColorCapa5);
            viewHolder.tvCont = (TextView) convertView.findViewById(R.id.tvCont);

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
        String autor = (data.get(position).getAutor());
        String votos = Integer.toString(data.get(position).getVotos());
        String cont = Integer.toString(position+1);


        viewHolder.tvNombreHelado.setText(nombreHelado);
        viewHolder.tvAutor.setText(autor);
        viewHolder.tvVotos.setText(votos);
        viewHolder.tvCont.setText(cont);


        //Pinto el primero de oro
        if (position == 0) {
            LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.layout1);
            LinearLayout lin2 = (LinearLayout) convertView.findViewById(R.id.layout2);
            LinearLayout lin3 = (LinearLayout) convertView.findViewById(R.id.layout3);
            lin1.setBackgroundColor(Color.parseColor("#FFD700"));
            lin2.setBackgroundColor(Color.parseColor("#FFD700"));
            lin3.setBackgroundColor(Color.parseColor("#FFD700"));
        }

        //Pinto el segundo de plata
        else if (position == 1) {
            LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.layout1);
            LinearLayout lin2 = (LinearLayout) convertView.findViewById(R.id.layout2);
            LinearLayout lin3 = (LinearLayout) convertView.findViewById(R.id.layout3);
            lin1.setBackgroundColor(Color.parseColor("#C0C0C0"));
            lin2.setBackgroundColor(Color.parseColor("#C0C0C0"));
            lin3.setBackgroundColor(Color.parseColor("#C0C0C0"));
        }

        //Pinto el tercero de bronce
        else if (position == 2) {
            LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.layout1);
            LinearLayout lin2 = (LinearLayout) convertView.findViewById(R.id.layout2);
            LinearLayout lin3 = (LinearLayout) convertView.findViewById(R.id.layout3);
            lin1.setBackgroundColor(Color.parseColor("#8C7853"));
            lin2.setBackgroundColor(Color.parseColor("#8C7853"));
            lin3.setBackgroundColor(Color.parseColor("#8C7853"));
        }

        else{

            LinearLayout lin1 = (LinearLayout) convertView.findViewById(R.id.layout1);
            LinearLayout lin2 = (LinearLayout) convertView.findViewById(R.id.layout2);
            LinearLayout lin3 = (LinearLayout) convertView.findViewById(R.id.layout3);
            lin1.setBackgroundColor(Color.parseColor("#75ffab00"));
            lin2.setBackgroundColor(Color.parseColor("#75ffab00"));
            lin3.setBackgroundColor(Color.parseColor("#75ffab00"));

        }



        //Pinto la franja de capas de cada helado
        if (capa1.equals("caramelo")) {
            viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#FF8000"));
        }

        if (capa1.equals("chocolate_blanco")) {
            viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#E6E6E6"));
        }

        if (capa1.equals("chocolate_negro")) {
            viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#2A0A0A"));
        }

        if (capa1.equals("sirope_fresa")) {
            viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#FF0080"));
        }

        if (capa1.equals("multicolor")) {
            viewHolder.ColorCapa1.setBackgroundColor(Color.parseColor("#00FFFF"));
        }


        if (capa3.equals("caramelo")) {
            viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#FF8000"));
        }

        if (capa3.equals("chocolate_blanco")) {
            viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#E6E6E6"));
        }

        if (capa3.equals("chocolate_negro")) {
            viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#2A0A0A"));
        }

        if (capa3.equals("sirope_fresa")) {
            viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#FF0080"));
        }

        if (capa3.equals("multicolor")) {
            viewHolder.ColorCapa3.setBackgroundColor(Color.parseColor("#00FFFF"));
        }


        if (capa5.equals("caramelo")) {
            viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#FF8000"));
        }

        if (capa5.equals("chocolate_blanco")) {
            viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#E6E6E6"));
        }

        if (capa5.equals("chocolate_negro")) {
            viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#2A0A0A"));
        }

        if (capa5.equals("sirope_fresa")) {
            viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#FF0080"));
        }

        if (capa5.equals("multicolor")) {
            viewHolder.ColorCapa5.setBackgroundColor(Color.parseColor("#00FFFF"));
        }


        if (capa2.equals("fresa")) {
            viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        if (capa2.equals("nata")) {
            viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#F5F6CE"));
        }

        if (capa2.equals("vainilla")) {
            viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#F4FA58"));
        }

        if (capa2.equals("chocolate")) {
            viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#8A2908"));
        }

        if (capa2.equals("turron")) {
            viewHolder.ColorCapa2.setBackgroundColor(Color.parseColor("#FFBF00"));
        }


        if (capa4.equals("fresa")) {
            viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#FF0000"));
        }

        if (capa4.equals("nata")) {
            viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#F5F6CE"));
        }

        if (capa4.equals("vainilla")) {
            viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#F4FA58"));
        }

        if (capa4.equals("chocolate")) {
            viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#8A2908"));
        }

        if (capa4.equals("turron")) {
            viewHolder.ColorCapa4.setBackgroundColor(Color.parseColor("#FFBF00"));
        }


        return convertView;
    }

}


