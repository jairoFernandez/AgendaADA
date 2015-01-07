package com.tucompualdia.app.com.tucompualdia.app.datacontexts;

import android.content.Context;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;
import com.mobandme.ada.exceptions.AdaFrameworkException;
import com.tucompualdia.app.entity.User;

/**
 * Creado por Jairo Fernández para Tu compu al día 5/01/15.
 */
public class ApplicationDataContext extends ObjectContext  {

    public ObjectSet<User> UserSet;
    public ApplicationDataContext(Context pContext) throws AdaFrameworkException {
        super(pContext);
        this.UserSet = new ObjectSet<User>(User.class, this);
    }
}
