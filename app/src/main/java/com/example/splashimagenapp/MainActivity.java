package com.example.splashimagenapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private ProgressBar progressBar;
    private TextView txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);
        progressBar = findViewById(R.id.progressBar);
        txtEstado = findViewById(R.id.txtEstado);

        btnIniciar.setOnClickListener(v -> iniciarAplicacion());
    }

    private void iniciarAplicacion() {

        btnIniciar.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        txtEstado.setText("Cargando...");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                txtEstado.setText("¡Aplicación iniciada!");
                btnIniciar.setEnabled(true);
            });

            executor.shutdown();
        });
    }
}