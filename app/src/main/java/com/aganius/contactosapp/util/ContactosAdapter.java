package com.aganius.contactosapp.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aganius.contactosapp.R;
import com.aganius.contactosapp.logica.Contacto;
import com.aganius.contactosapp.modelo.DatabaseHandler;

import java.util.ArrayList;

/**
 * Created by Aganius on 04/05/2015.
 */
public class ContactosAdapter extends BaseAdapter {

    ArrayList<Contacto> contactos = new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    DatabaseHandler databaseHandler;


    public ContactosAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);        // only context can also be used
        this.databaseHandler = new DatabaseHandler(context);
        this.contactos = databaseHandler.obtenerTodosContactos();

    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Contacto getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contacto_item, null);
            mViewHolder = new MyViewHolder();
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        mViewHolder.setIvFavorito(detail(convertView, R.id.favorito, contactos.get(position).getFavorito()));
        mViewHolder.setTvNombre(detail(convertView, R.id.nombre, contactos.get(position).getNombre()));
        mViewHolder.setTvTelefono(detail(convertView, R.id.telefono, contactos.get(position).getTelefono()));
        mViewHolder.setTvEmail(detail(convertView, R.id.email, contactos.get(position).getEmail()));

        return convertView;
    }

    private TextView detail(View v, int resId, String text) {
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

    private ImageView detail(View v, int resId, Boolean favorito) {
        ImageView iv = (ImageView) v.findViewById(resId);
        iv.setImageResource(R.drawable.ic_action_star);

        return iv;
    }

    private class MyViewHolder {
        private ImageView ivFavorito;
        private TextView tvNombre;
        private TextView tvTelefono;
        private TextView tvEmail;


        public ImageView getIvFavorito() {
            return ivFavorito;
        }

        public void setIvFavorito(ImageView ivFavorito) {
            this.ivFavorito = ivFavorito;
        }

        public TextView getTvNombre() {
            return tvNombre;
        }

        public void setTvNombre(TextView tvNombre) {
            this.tvNombre = tvNombre;
        }

        public TextView getTvTelefono() {
            return tvTelefono;
        }

        public void setTvTelefono(TextView tvTelefono) {
            this.tvTelefono = tvTelefono;
        }

        public TextView getTvEmail() {
            return tvEmail;
        }

        public void setTvEmail(TextView tvEmail) {
            this.tvEmail = tvEmail;
        }
    }

}