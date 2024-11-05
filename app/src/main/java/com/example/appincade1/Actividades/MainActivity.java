package com.example.appincade1.Actividades;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appincade1.DBHelper;
import com.example.appincade1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioInput;
    private DBHelper dbHelper;
    private EditText contraseñaInput;
    private Button loginButton;
    private TextView result;
    private String usuario_verdadero, contraseña_verdadera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        usuarioInput = findViewById(R.id.usuario_input);
        contraseñaInput = findViewById(R.id.contraseña_input);
        loginButton = findViewById(R.id.login_button);
        result = findViewById(R.id.result_message);
        contraseña_verdadera = "root";

        loginButton.setOnClickListener(e -> {
            String usuario = usuarioInput.getText().toString().trim();
            String contraseña = contraseñaInput.getText().toString().trim();
            String contraseñaObtenida = dbHelper.obtenerContraseñaCorrecta(usuario);

            if ((contraseñaObtenida != null && contraseñaObtenida.equals(contraseña)) || comprobarUsuarioRoot(usuario, contraseña)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
                String fechaLogueo = sdf.format(new Date());
                result.setText("Has iniciado sesión con el usuario " + usuario + " y la contraseña: " + contraseña);
                Intent intent = new Intent(MainActivity.this, GestionarUsuarios.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("fechaLogueo", fechaLogueo);
                startActivity(intent);
            } else {
                result.setText(R.string.wrong_user_password);
            }
        });

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean comprobarUsuarioRoot(String usuario, String contraseña) {
        return usuario.equals("root") && contraseña.equals("password");
    }
}