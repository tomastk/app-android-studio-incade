package com.example.appincade1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText numero1Input;
    private EditText numero2Input;
    private Button calcularButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main); // Mueve esto al principio
        numero1Input = findViewById(R.id.numero1_input);
        numero2Input = findViewById(R.id.numero2_input);
        calcularButton = findViewById(R.id.calcular_button);

        calcularButton.setOnClickListener(e -> {
            double numero1 = Double.valueOf(numero1Input.getText().toString());
            double numero2 = Double.valueOf(numero2Input.getText().toString());
            double result = numero1 + numero2;
            Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
        });

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}