package com.tucompualdia.app.entity;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.RequiredFieldValidation;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;
import com.tucompualdia.app.agenda.R;

import java.util.Date;

@Table(name = "tUser")
public class User extends Entity {

    @TableField(name="name",datatype = Entity.DATATYPE_TEXT, required = true, maxLength = 100)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public String Name = "";

    @TableField(name="surname",datatype = Entity.DATATYPE_TEXT, maxLength = 100)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public String Surname = "";

    @TableField(name="birthday",datatype = Entity.DATATYPE_DATE, required = true)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public Date Birthday = new Date();

    @TableField(name="code",datatype = Entity.DATATYPE_INTEGER, required = true)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public int Code = 0;

    @TableField(name="username",datatype = Entity.DATATYPE_TEXT, required = true, maxLength = 100)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public String Username = "";

    @TableField(name="password",datatype = Entity.DATATYPE_TEXT, required = true, maxLength = 100, encripted = true)
    @RequiredFieldValidation(messageResourceId = R.string.validationMessage)
    public String Password = "";

    @TableField(name="active",datatype = Entity.DATATYPE_BOOLEAN)
    public boolean Active = false;

    @Override
    public String toString(){
        return Name + "( " + Username + " )";
    }

}
