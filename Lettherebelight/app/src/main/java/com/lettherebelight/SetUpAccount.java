package com.lettherebelight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetUpAccount extends AppCompatActivity implements View.OnClickListener {

    private EditText editTxtFullName, editTxtCompanyName;
    private RadioButton radioBtnApprentice, radioBtnJourneyman, radioBtnMaster;
    private Button btnUpdateAccount;
    private CircleImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_account);

        editTxtFullName = findViewById(R.id.editTxtFullName);
        editTxtCompanyName = findViewById(R.id.editTxtCompanyName);
        radioBtnApprentice = findViewById(R.id.radioBtnApprentice);
        radioBtnJourneyman = findViewById(R.id.radioBtnJourneyman);
        radioBtnMaster = findViewById(R.id.radioBtnMaster);
        btnUpdateAccount = findViewById(R.id.btnUpdateAccount);
        profilePic = findViewById(R.id.profile_image);
        profilePic.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.profile_image:{
                //Intent intent

            }
        }
    }
}