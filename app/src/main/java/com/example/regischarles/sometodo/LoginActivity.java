package com.example.regischarles.sometodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
EditText emailAddress ,password;
DatabaseHelper helper;
SessionManage sessionManage;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailAddress=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        sessionManage=new SessionManage(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_register:
                startActivity(new Intent(this,Register.class));
        }
        return  true;
    }

    @Override
    public void onClick(View v) {
        String status;
        helper=new DatabaseHelper(getApplicationContext(),null,null,4);
        if(emailAddress.getText()!=null && password.getText()!=null){

           status= helper.checkUser(emailAddress.getText().toString().trim(),password.getText().toString().trim());
           if(status.equals("success")){
               startActivity(new Intent(getApplicationContext(),Todo.class));
               String username=helper.getUserName(emailAddress.getText().toString(),password.getText().toString());
               sessionManage.createSession( password.getText().toString(),emailAddress.getText().toString(),username);

           }
           else {
               Toast.makeText(getApplicationContext(),"Login failed "+status,Toast.LENGTH_LONG).show();
           }


        }
        else{
            Toast.makeText(getApplicationContext(),"enter email address and password ",Toast.LENGTH_LONG).show();
        }
    }
    public void signUp(View v){
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}
