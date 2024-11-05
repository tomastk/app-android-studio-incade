package com.example.appincade1.Actividades;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appincade1.R;
import com.example.appincade1.modelos.Usuario;

public class UsuarioLogueado extends AppCompatActivity {

    TextView userLabel;
    TextView lastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_usuario_logueado);

        userLabel = findViewById(R.id.section_title);
        lastDate = findViewById(R.id.last_date);
        String usuario = getIntent().getStringExtra("usuario");
        String fechaLogueo = getIntent().getStringExtra("fechaLogueo");

        userLabel.setText("¡Hola " + usuario + "!");
        lastDate.setText("Último inicio: " + fechaLogueo);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}