package com.example.stanvi.dbms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminActivity extends AppCompatActivity {
    EditText adminid,password;
    Button proceed;
    CoordinatorLayout coordinatorLayout;
    SharedPreferences adminsp;
   public static String ADMIN_PASSWORD,ADMIN_FILE,ADMIN_ID;

   SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        sp=getSharedPreferences(ADMIN_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(ADMIN_PASSWORD,"admin");
        editor.commit();
        ADMIN_ID="ADMIN";
        coordinatorLayout= (CoordinatorLayout) findViewById(R.id.admincoordinator);
        adminid= (EditText) findViewById(R.id.adminidbox);
        password= (EditText) findViewById(R.id.adminpassword);
         proceed= (Button) findViewById(R.id.adminProceed);


        adminsp=getSharedPreferences(ADMIN_FILE,Context.MODE_PRIVATE);
        if(adminsp.getString("Password_ENTERED",null)!=null)
            password.setText(adminsp.getString("Password_ENTERED",null));
//        else
//            Snackbar.make(coordinatorLayout, "PASSWORD NULL", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminid.getText().toString().contentEquals(ADMIN_ID))
                {   if(password.getText().toString().contentEquals(sp.getString(ADMIN_PASSWORD,"admin")))
                    {
                        SharedPreferences.Editor editor1 = adminsp.edit();
                        editor1.putString("Password_ENTERED",password.getText().toString());
                        editor1.commit();
                        Intent intent = new Intent(AdminActivity.this, NewAccountActivity.class);
                        startActivity(intent);

                    }
                    else
                    Snackbar.make(coordinatorLayout, "PASSWORD INCORRECT "+password.getText().toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                else
                {

                   //password incorrect
                    Snackbar.make(coordinatorLayout, "USERNAME INCORRECT"+adminid.getText().toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.passwordChange) {


        }
        if(id==R.id.adminlogout)
        {   SharedPreferences.Editor editor1 = adminsp.edit();
            editor1.putString("Password_ENTERED",null);
            editor1.commit();
            Intent intent = new Intent(AdminActivity.this, AdminActivity.class);
            startActivity(intent);
        }

        return true;
    }
}

