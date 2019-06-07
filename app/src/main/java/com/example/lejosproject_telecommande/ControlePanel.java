package com.example.lejosproject_telecommande;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ControlePanel extends AppCompatActivity {
    //Variable globale
    BluetoothConnectionService bluetoothConnectionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_panel);

        this.bluetoothConnectionService = BluetoothConnectionService.getInstance(this);
    }

    //Chaque méthode correspondent au onClick d'un des boutons de la vue télécommande
    //Elles appelent la méthode request qui permet d'envoyer une requête au robot

    public void on(View view){
        request((byte) 0);
    }

    public void off(View view){
        request((byte) 1);
    }

    public void right(View view){
        request((byte) 2);
    }

    public void left(View view){
        request((byte) 3);
    }

    public void forward(View view){
        request((byte) 4);
    }

    public void backward(View view){
        request((byte) 5);
    }

    public void up(View view){
        request((byte) 6);
    }

    public void down(View view){
        request((byte) 7);
    }

    public void stop(View view){
        request((byte) 9);
        bluetoothConnectionService.stopBluetooth();
    }

    public void request(byte message) {
        this.bluetoothConnectionService.send(message);
    }
}
