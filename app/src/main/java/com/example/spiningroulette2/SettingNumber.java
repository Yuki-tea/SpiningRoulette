package com.example.spiningroulette2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettingNumber extends AppCompatActivity {
    private RadioGroup options;
    private Button btnSelect;
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        options = findViewById(R.id.options);
        btnSelect = findViewById(R.id.btnSelect);



        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int checkedId = options.getCheckedRadioButtonId();
                    String selected = ((RadioButton) findViewById(checkedId)).getText().toString();
                    count = selected.charAt(0) - '0';
                    Intent intent = new Intent(SettingNumber.this, SettingValues.class);
                    intent.putExtra("inputNum", count);
                    startActivity(intent);
            }
        });
    }
}