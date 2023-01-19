import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.*;


public class Server {

    public static void main(String[] args) {


        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("Client connected");
                InputStream inputStream=Server.class.getClassLoader().getResourceAsStream("islands_in_the_stream.txt");
                StringBuilder sb = new StringBuilder();
                for (int ch; (ch = inputStream.read()) != -1; ) {
                    sb.append((char) ch);
                }

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                writer.println(sb.toString());
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}