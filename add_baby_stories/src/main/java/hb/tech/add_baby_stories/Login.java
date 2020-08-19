package hb.tech.add_baby_stories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
EditText email , pass ;
Button signin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                       boolean zz =  authResult.getUser().getEmail().equals("hb.tech.business@gmail.com");
                       if (zz){
                           startActivity(new Intent(Login.this,baby_stories.class));
                           finish();
                       }
                    }
                });
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("hb.tech.business@gmail.com"))
                {
                    FirebaseAuth.getInstance().sendPasswordResetEmail("hb.tech.business@gmail.com").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Login.this, "check Your Email Boss", Toast.LENGTH_SHORT).show();
                        }
                    });

            }
            }
        });

    }
    private void initViews(){
        email = findViewById(R.id.email_text);
        pass = findViewById(R.id.password_text);
        signin = findViewById(R.id.button);

    }
}
