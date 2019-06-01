package com.example.lejosproject_telecommande;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.UUID;

public class BluetoothConnectionService {
    private static BluetoothConnectionService instance;
    private String MAC;
    private BluetoothAdapter localAdapter;
    private BluetoothSocket socketRobot;
    private OutputStreamWriter out;
    private Context context;
    private PrintStream sender;

    private BluetoothConnectionService(Context context) {
        this.context = context;
    }

    public static BluetoothConnectionService getInstance(Context context) {
        if (instance == null) {
            instance = new BluetoothConnectionService(context);
        }
        return(instance);
    }

    public String enableBluetooth(){
        localAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!localAdapter.isEnabled()){
            localAdapter.enable();
            return "Erreur";
        }
        return "Success";
    }

    public String connectRobot(){
        String success;
        success = enableBluetooth();
        if (success.equals("Success")) {
            BluetoothDevice robot = localAdapter.getRemoteDevice(MAC);
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            try {
                socketRobot = robot.createRfcommSocketToServiceRecord(uuid);
                socketRobot.connect();
                out = new OutputStreamWriter(socketRobot.getOutputStream());
                success = "1";
            } catch (IOException e) {
                success = e.getMessage();
            }
        } else {
            success += " - Le bluetooth est désactivé";
        }
        return success;
    }

    public void setMAC(String MAC){
        this.MAC = MAC;
    }

    public void send(byte message) {
        try{
            out.write(message);
            out.flush();
        } catch (IOException ioe) {
            System.out.println("IO Exception");
        }
    }

    public void stopBluetooth() {
        try{
            socketRobot.close();
            out.close();
        } catch (IOException ioe) {
            System.out.println("IO Exception");
        }
    }
}
