package com.example.appincade1.Actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appincade1.DBHelper;
import com.example.appincade1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class GestionarUsuarios extends AppCompatActivity {

    private RecyclerView userRecyclerView;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton addUserButton;
    private DBHelper dbHelper;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_usuarios);
        dbHelper = new DBHelper(this);
        userRecyclerView = findViewById(R.id.userRecyclerView);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        addUserButton = findViewById(R.id.addUserButton);

        userAdapter = new UserAdapter(dbHelper.obtenerTodosLosUsuarios());
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.setAdapter(userAdapter);

        addUserButton.setOnClickListener(v -> addUser());
    }

    private void addUser() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            dbHelper.añadirUsuario(username, password);
            userAdapter.updateUsers(dbHelper.obtenerTodosLosUsuarios());
            usernameEditText.setText("");
            passwordEditText.setText("");
            Toast.makeText(this, "Usuario añadido con éxito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private static class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

        private List<String> users;

        public UserAdapter(List<String> users) {
            this.users = users;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.userNameTextView.setText(users.get(position));
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        public void updateUsers(List<String> newUsers) {
            users = newUsers;
            notifyDataSetChanged();
        }

        static class UserViewHolder extends RecyclerView.ViewHolder {
            TextView userNameTextView;

            UserViewHolder(View itemView) {
                super(itemView);
                userNameTextView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}