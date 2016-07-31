package sies.Aptitude;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final TextView regLink = (TextView) findViewById(R.id.tvRegister);

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(Login.this, Register.class);
                Login.this.startActivity(regIntent);

            }
        });
    }

    public void onLoginClick(View v){
        if(v.getId() == R.id.btLogin){
            EditText u = (EditText)findViewById(R.id.etUname);
            String user = u.getText().toString();
            EditText p = (EditText)findViewById(R.id.etPass);
            String pass = p.getText().toString();

            String password = helper.searchPass(user);
            if(pass.equals(password)){
                Toast msg = Toast.makeText(Login.this , "Successfull" , Toast.LENGTH_SHORT);
                msg.show();
                Intent regIntent = new Intent(Login.this, Topics.class);
                Login.this.startActivity(regIntent);
            }
            else{
                Toast msg = Toast.makeText(Login.this , "Username and Password does not match" , Toast.LENGTH_SHORT);
                msg.show();
            }
        }
    }

}
