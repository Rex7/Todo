package com.example.regischarles.sometodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText name,email,password,confirmPassword;
    Button register;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.Name);
        email=findViewById(R.id.emailId_register);
        password=findViewById(R.id.password_register);
        confirmPassword=findViewById(R.id.confirmPassword_register);
        register=findViewById(R.id.register);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
      helper=new DatabaseHelper(this,null,null,3);
      User user=new User();
      user.setEmail(email.getText().toString());
      user.setName(name.getText().toString());
      user.setPassword(password.getText().toString());
    long val=  helper.registerUser(user);
    if(val>0){
startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }
    else{
        Toast.makeText(getApplicationContext(),"User not registered",Toast.LENGTH_LONG).show();
    }
    }
}
