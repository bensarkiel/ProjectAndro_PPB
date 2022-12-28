package com.example.CineTix.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CineTix.user.MenuUser;
import com.example.CineTix.user.model.Akun;
import com.example.CineTix.user.model.Userprofile;
import com.example.CineTix.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Daftar extends AppCompatActivity {

    private TextView sign_in;
    private EditText name,email,password,dob,mobile,address;
    private Button register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String Nama,TanggalLahir,Email,Password,NomorTelepon,Alamat;
    private String Balance = String.valueOf(0);
    private String Tipe = String.valueOf(1);
    private String UserProfilePic = "None";
    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar);

        setupUI();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Daftar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        dob.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Daftar.this, Login.class));
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    String Email = email.getText().toString().trim();
                    String Password = password.getText().toString().trim();

                    progressDialog.setTitle("Daftar Akun");
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                SendUserData();
                                progressDialog.dismiss();
                                Toast.makeText(Daftar.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Daftar.this, MenuUser.class));
                                finish();
                            }else {
                                progressDialog.dismiss();
                                String message = task.getException().getMessage();
                                Toast.makeText(Daftar.this,"Registration Failed" + message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void setupUI(){
        sign_in = (TextView)findViewById(R.id.user_sign_in);

        name = (EditText)findViewById(R.id.user_name);
        dob = (EditText)findViewById(R.id.user_dob);
        email = (EditText)findViewById(R.id.user_email);
        password = (EditText)findViewById(R.id.user_password);
        mobile = (EditText)findViewById(R.id.user_mobile);
        address = (EditText)findViewById(R.id.user_address);

        register = (Button)findViewById(R.id.user_registration_bt);

        //profileImage = (ImageView) findViewById(R.id.r_imageView);
    }

    public boolean validate(){
        boolean result = false;
        Nama = name.getText().toString();
        TanggalLahir = dob.getText().toString();
        Email = email.getText().toString();
        Password = password.getText().toString();
        NomorTelepon = mobile.getText().toString();
        Alamat = address.getText().toString();

        if (TextUtils.isEmpty(Nama)){
            name.setError("Enter name");
        }else if (TextUtils.isEmpty(Email)){
            email.setError("Enter email");
        }else if (TextUtils.isEmpty(Password)){
            password.setError("Enter password");
        }else if (TextUtils.isEmpty(NomorTelepon) || mobile.getText().toString().length() < 10 || mobile.getText().toString().length() > 10){
            mobile.setError("Enter a valid phone number");
        }else if (TextUtils.isEmpty(Alamat)){
            address.setError("Enter address");
        }else if (TextUtils.isEmpty(TanggalLahir)){
             dob.setError("Select date");
        }else {
            result = true;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Daftar.this,Login.class));
    }

    private void SendUserData(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Info").child(firebaseAuth.getUid());
        Userprofile userProfile = new Userprofile(Nama,TanggalLahir,Email,NomorTelepon,Alamat,Tipe,UserProfilePic);
        databaseReference.setValue(userProfile);

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Account Info").child(firebaseAuth.getUid());
        Akun account = new Akun(Nama,NomorTelepon,TanggalLahir,NomorTelepon,Balance,Password);
        databaseReference1.setValue(account);
    }
}