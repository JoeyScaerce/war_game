package server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;


    public GameServer() {
        System.out.println("Game Server");
        this.numPlayers = 0;
        this.maxPlayers = 2;

        try {
            ss = new ServerSocket(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptConnection() {
        try {
            System.out.println("waiting for connections...");

            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutput out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("player id:" + numPlayers + " has connection");
            }

            System.out.println("No more player slots");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnection();

    }
}
