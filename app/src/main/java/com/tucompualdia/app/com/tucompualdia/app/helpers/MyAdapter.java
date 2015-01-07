package com.tucompualdia.app.com.tucompualdia.app.helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tucompualdia.app.agenda.R;
import com.tucompualdia.app.com.tucompualdia.app.datacontexts.ApplicationDataContext;
import com.tucompualdia.app.entity.User;

/**
 * Creado por Jairo Fernández para Tu compu al día 6/01/15.
 */
public class MyAdapter extends BaseAdapter {
    private ApplicationDataContext appDataContext;
    protected LayoutInflater inflater;

    @Override
    public int getCount() {
        return appDataContext.UserSet.size();
    }

    @Override
    public Object getItem(int position) {
        if (position < getCount()) {
            return appDataContext.UserSet.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        User usuario = (User) getItem(position);
        return usuario.getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {

            itemView = inflater.inflate(R.layout.activity_listado, null);
        }
        User usuario = (User) getItem(position);
        ((TextView) itemView.findViewById(R.id.name)).setText(usuario.Name);
        if (usuario.Username == null) {
            ((TextView) itemView.findViewById(R.id.username)).setText(R.string.userName);
        } else {
            ((TextView) itemView.findViewById(R.id.username)).setText(usuario.Username);
        }
        return itemView;
    }

}