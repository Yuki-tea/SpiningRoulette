package com.example.spiningroulette2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SettingValues extends AppCompatActivity {

    private int count;
    private LinearLayout formLayout;
    private EditText edtText;
    private TextView txtBack;
    private Button btnSet;
    private ArrayList<Integer> edtTextIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_values);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        count = getIntent().getIntExtra("inputNum", 0);
        formLayout = findViewById(R.id.form);
        txtBack = findViewById(R.id.txtBack);
        btnSet = findViewById(R.id.btnSet);
        edtTextIds = new ArrayList<>();
        generateEditTextFields();

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingValues.this, MainActivity.class);
                for(int i=0; i<count; i++){
                    int id = edtTextIds.get(i);
                    edtText = findViewById(id);
                    intent.putExtra("Sec" + (i+1), edtText.getText().toString());
                }
                intent.putExtra("inputNum", count);
                startActivity(intent);
            }
        });

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBack.setTextColor(getColor(R.color.black));
                Intent intent = new Intent(SettingValues.this, SettingNumber.class);
                startActivity(intent);
            }
        });

    }

    private void generateEditTextFields() {

        for (int i = 0; i < count; i++) {
            int id = View.generateViewId();
            edtText = new EditText(this);
            edtText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            edtText.setHint("Section " + (i + 1));
            edtText.setTextSize(20);
            edtText.setId(id);
            edtTextIds.add(id);
            formLayout.addView(edtText);
        }
    }

}

