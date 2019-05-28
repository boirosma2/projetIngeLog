package com.example.lejosproject_telecommande;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class ControlePanel extends AppCompatActivity {

    BluetoothConnectionService bluetoothConnectionService;
    //TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controle_panel);

        this.bluetoothConnectionService = BluetoothConnectionService.getInstance(this);
        //this.result = findViewById(R.id.resultButton);
    }

    public void on(View view) throws IOException{
        request((byte) 0);
    }

    public void off(View view) throws IOException{
        request((byte) 1);
    }

    public void right(View view) throws IOException{
        request((byte) 11);
    }

    public void left(View view) throws IOException{
        request((byte) 12);
    }

    public void forward(View view) throws IOException{
        request((byte) 13);
    }

    public void backward(View view) throws IOException{
        request((byte) 14);
    }

    public void up(View view) throws IOException{
        request((byte) 15);
    }

    public void down(View view) throws IOException{
        request((byte) 16);
    }

    public void request(Byte message) throws IOException {
        //Integer nb = message.intValue();
        //this.result.setText(String.valueOf(nb));
        this.bluetoothConnectionService.send(message);
    }
}
