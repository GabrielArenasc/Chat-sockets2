/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelView;

/**
 *
 * @author FLIA ARENAS CARMONA
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import View.chat.*;

public class WhatsAppG {
    private PrintWriter out;
    private BufferedReader in;
    

    public void WhatsApp() {
        // Establecer una conexión de servidor en segundo plano para recibir mensajes
        new Thread(new Server()).start();
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    private void receiveMessage(String message) {
        System.out.println("Otro equipo: " + message);
    }

    private class Server implements Runnable {
        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(4); // Puerto de escucha
                Socket clientSocket = serverSocket.accept();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    receiveMessage(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
}
