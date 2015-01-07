package com.tucompualdia.app.agenda;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobandme.ada.exceptions.AdaFrameworkException;
import com.mobandme.ada.validators.ValidationResult;
import com.tucompualdia.app.com.tucompualdia.app.datacontexts.ApplicationDataContext;
import com.tucompualdia.app.entity.User;

import java.util.Date;
import java.util.List;


public class FormActivity extends ActionBarActivity {

    private ApplicationDataContext dataContext;
    EditText name;
    EditText lastname;
    EditText password;
    EditText username;
    EditText code;
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        name = (EditText)findViewById(R.id.editTextFirstName);
        lastname = (EditText)findViewById(R.id.editTextLastName);
        username = (EditText)findViewById(R.id.editTextUserName);
        password = (EditText)findViewById(R.id.editTextPassword);
        code = (EditText)findViewById(R.id.editTextCode);

        try {
            dataContext = new ApplicationDataContext(this);
        } catch (AdaFrameworkException e) {
            e.printStackTrace();
        }

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    populateDatabase();
                } catch (AdaFrameworkException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
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

    private void populateDatabase() throws AdaFrameworkException {
        if(dataContext != null){
            User usuario = new User();
            usuario.Active = true;
            usuario.Birthday = new Date(1987,3,9);
            usuario.Code = Integer.parseInt(code.getText().toString());
            usuario.Name = name.getText().toString();
            usuario.Surname = lastname.getText().toString();
            usuario.Username = username.getText().toString();
            usuario.Password = password.getText().toString();

            if(!usuario.validate(this)){
                List<ValidationResult> validationResults = usuario.getValidationResult();
                String message = "";
                for(ValidationResult result : validationResults){
                    message += "\r\n * " + result.getMessage();
                }

                Toast.makeText(getApplicationContext(),String.format("Campos incorrectos: %s", message),Toast.LENGTH_LONG).show();

            } else {
                dataContext.UserSet.add(usuario);
                dataContext.UserSet.save();
                Toast.makeText(getApplicationContext(), "Guardando...", Toast.LENGTH_SHORT).show();
            }
           }
    }
}
