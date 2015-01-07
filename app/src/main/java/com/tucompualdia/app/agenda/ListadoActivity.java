package com.tucompualdia.app.agenda;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tucompualdia.app.com.tucompualdia.app.datacontexts.ApplicationDataContext;
import com.tucompualdia.app.entity.User;

public class ListadoActivity extends ListActivity {

/*    private ApplicationDataContext appDataContext;
    protected LayoutInflater inflater;
    private ApplicationDataContext dataContext;

    private MyAdapter myAdapter;*/

    private ApplicationDataContext dataContext;
    private MyAdapter adapter;
    protected LayoutInflater inflater;
    //private ArrayAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listado);

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        try {
            // Creamos un objeto ApplicationDataContext
            dataContext = new ApplicationDataContext(this);
            // Rellenamos el DAO de categorías ordenándolo por la columna name
            dataContext.UserSet.fill("Name");
            // Creamos el adapter que utilizará el DAO
            adapter = new MyAdapter();
            setListAdapter(adapter);

        } catch (Exception e) {
            Log.e("Androcode", "Error creando vista", e);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Adapter que utiliza el DAO para rellenar la lista
     *
     */
    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return dataContext.UserSet.size();
        }

        @Override
        public Object getItem(int position) {
            if (position < getCount()) {
                return dataContext.UserSet.get(position);
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
            ((TextView) itemView.findViewById(R.id.name)).setText(usuario.Name+" "+usuario.Surname);
            if (usuario.Username == null) {
                ((TextView) itemView.findViewById(R.id.username)).setText(R.string.userName);
            } else {
                ((TextView) itemView.findViewById(R.id.username)).setText(usuario.Username);
            }
            return itemView;
        }

    }

}
