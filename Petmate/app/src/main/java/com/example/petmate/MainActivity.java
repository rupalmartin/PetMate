package com.example.petmate;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.app.ProgressDialog;



public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btnSignup;
    private EditText txtPassword;
    private FirebaseAuth firebaseAuthentication;
    private EditText txtEmailId;
    private ProgressDialog progressDialogBar;


    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtEmailId = (EditText) findViewById(R.id.txtEmail);

        btnSignup.setOnClickListener((View.OnClickListener) this);
        progressDialogBar = new ProgressDialog(this);

        firebaseAuthentication = FirebaseAuth.getInstance();
    }


    private void userRegistrationMethod()
    {

        String strEmail = txtEmailId.getText().toString().trim();
        String strPassword  = txtPassword.getText().toString().trim();

        Log.i("Email is:",strEmail);
        Log.i("Password is:",strPassword);


        if(TextUtils.isEmpty(strEmail))
        {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(strPassword))
        {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialogBar.setMessage("Registering Please Wait...");
        progressDialogBar.show();

        Log.i("Debug:","Here1");
        firebaseAuthentication.createUserWithEmailAndPassword(strEmail, strPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        Log.i("Debug:","Here2");
                        if(task.isSuccessful())
                        {
                            Log.i("Debug:","Here3");
                            Toast.makeText(MainActivity.this,"User Successfully registered",Toast.LENGTH_LONG).show();
                        }
                        else
                            {
                            Log.i("Debug:","Here4");

                            Toast.makeText(MainActivity.this,"Registration Error.Please try again.",Toast.LENGTH_LONG).show();
                        }
                        progressDialogBar.dismiss();
                        Log.i("Debug:","Here5");
                    }
                });
    }

    @Override
    public void onClick(View view) {

        userRegistrationMethod();
    }
}
