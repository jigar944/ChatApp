

import java.net.*;
import java.io.*;

public class Server {

    ServerSocket server;
    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;

    //Constructor
    public Server(){

        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready and ready to accept connection");
            System.out.println("Waiting");
            socket = server.accept();

            //reader
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //writer
            writer = new PrintWriter(socket.getOutputStream());

            startReading();
            startWririting();


        } catch (Exception e) {
                e.printStackTrace();
        }

    }



    public void startReading() {

        //Thread reading
        Runnable read = () ->{
            System.out.println("Reader started");

                while(true){
                    try {
                        String readmesg = bufferedReader.readLine();
                    if(readmesg.equals("Close")){
                        System.out.println("Client wants to disconnect.");
                        break;
                    }
                    System.out.println("Client :"+readmesg);
                    } catch (Exception e) {
                        //TODO: handle exception
                        e.printStackTrace();
                    }
                    
                }
        };    

        new Thread(read).start();
    }

    public void startWririting() {
        
        //Thread writing
        Runnable write = () ->{

            while(true){

            try {
                System.out.println("writer started");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String mesg = reader.readLine();
                writer.println(mesg);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        };

        new Thread(write).start();

    }

    public static void main(String[] args) {
        System.out.println("This is Server... going to start server !!");
        new Server();
    }
    
}
