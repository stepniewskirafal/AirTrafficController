package pl.rstepniewski.aircraft;

import pl.rstepniewski.ConnectionHandler;
import pl.rstepniewski.propertiesUtil.PropertiesUtil;

import java.io.IOException;
import java.net.Socket;

public class Aircraft implements Runnable{

    private final String SERVER_ADDRESS = PropertiesUtil.getString("SERVER_ADDRESS");
    private final int SERVER_PORT = PropertiesUtil.getInt("SERVER_PORT");
    private ConnectionHandler radioCommunicator;
    @Override
    public void run() {
        try {
            radioCommunicator = new ConnectionHandler(new Socket(SERVER_ADDRESS, SERVER_PORT));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
