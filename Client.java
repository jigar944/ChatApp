
import java.net.*;
import java.io.*;

public class Client {

    Socket socket;
    BufferedReader bufferedReader;
    PrintWriter writer;

    public Client(){

        try {
            System.out.println("Sending request to server !");
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connection Accepted");

            //reader
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //writer
            writer = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            //TODO: handle exception
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
                        System.out.println("Server wants to disconnect.");
                        break;
                    }
                    System.out.println("Server :"+readmesg);
                        
                    } catch (Exception e) {
                        //TODO: handle exception
                        e.printStackTrace();
                    }
                    

                }
        };    

        new Thread(read).start();
    }

    public void startWriting() {

      

        
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
        System.out.println("This is Client.. Ready to connect !");
        new Client();
    }
    
}
