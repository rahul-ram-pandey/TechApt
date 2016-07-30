package sies.Aptitude;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);



    }

    public void onRegisterClick(View v)
    {
        if(v.getId() == R.id.btnregister)
        {
            EditText name = (EditText)findViewById(R.id.etName);
            EditText username = (EditText)findViewById(R.id.etUname);
            EditText email = (EditText)findViewById(R.id.etEmail);
            EditText password = (EditText)findViewById(R.id.etPass);
            EditText phone = (EditText)findViewById(R.id.etPhone);

            String namestr = name.getText().toString();
            String usernamestr = username.getText().toString();
            String emailstr = email.getText().toString();
            String passwordstr = password.getText().toString();
            String phonestr = phone.getText().toString();

            if(namestr.length()<1)
            {
                Toast msg = Toast.makeText(Register.this , "Enter Name" , Toast.LENGTH_SHORT);
                msg.show();
            }
            else if(usernamestr.length()<1)
            {
                Toast msg = Toast.makeText(Register.this , "Enter a User Name" , Toast.LENGTH_SHORT);
                msg.show();
            }
           else if(emailstr.length()<1)
            {
                Toast msg = Toast.makeText(Register.this , "Enter E-mail" , Toast.LENGTH_SHORT);
                msg.show();
            }
            else if(passwordstr.length()<8)
            {
                Toast msg = Toast.makeText(Register.this , "Password should be of at least 8 digit" , Toast.LENGTH_SHORT);
                msg.show();
            }
            else if((phonestr.length()<10)&&(phonestr.length()>10)) {
                Toast msg = Toast.makeText(Register.this, "Incorrect Phone", Toast.LENGTH_SHORT);
                msg.show();
            }
            else{
                if(helper.duplicate(usernamestr)>1){
                    Toast msg = Toast.makeText(Register.this, "Username already exist", Toast.LENGTH_SHORT);
                    msg.show();
                }
                else {
                    contact c = new contact();
                    c.setName(namestr);
                    c.setUsername(usernamestr);
                    c.setEmail(emailstr);
                    c.setPassword(passwordstr);
                    c.setPhone(phonestr);

                    helper.insertContact(c);
                    Toast msg = Toast.makeText(Register.this, "Registeration Successful !!", Toast.LENGTH_SHORT);
                    msg.show();
                    Intent regIntent = new Intent(Register.this, Login.class);
                    Register.this.startActivity(regIntent);
                }
                }
        }
    }
}
