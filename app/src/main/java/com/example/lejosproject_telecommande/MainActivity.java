package com.example.lejosproject_telecommande;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText inputMACReseau;
    TextView resultMAC;
    BluetoothConnectionService blService;
    private static final Pattern pattern = Pattern.compile("^([0-9A-Z]{2}:){5}([0-9A-Z]{2})$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inputMACReseau = findViewById(R.id.editMACRobot);
        this.resultMAC = findViewById(R.id.resultIP);
        this.blService = BluetoothConnectionService.getInstance(this);

        final Button connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String MAC;
                String success;
                resultMAC.setText("");
                MAC = inputMACReseau.getText().toString();
                //blService.setMAC("00:16:53:56:5F:C2");
                if(pattern.matcher(MAC).matches()) {
                    blService.setMAC(MAC);
                    success = blService.connectRobot();
                    if (success.equals("1")) {
                        Intent intent = new Intent(MainActivity.this, ControlePanel.class);
                        startActivity(intent);
                    } else {
                        resultMAC.setText(success);
                    }
                } else {
                    resultMAC.setText("Adresse MAC incorrect");
                }
            }
        });
    }
}
