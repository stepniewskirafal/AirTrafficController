package pl.rstepniewski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler {
    private final Socket socket;
    private final PrintWriter sender;
    private final BufferedReader receiver;
    public ConnectionHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.sender = new PrintWriter(this.socket.getOutputStream(), true);
        this.receiver = new BufferedReader( new InputStreamReader(this.socket.getInputStream()));
    }

    public void send(String message) {
        sender.println(message);
    }

    public String receive() {
        String receivedMessage;
        try{
            receivedMessage = receiver.readLine();
        } catch (IOException e) {
            receivedMessage = "";
            throw new RuntimeException(e);
        }
        return receivedMessage;
    }
}
