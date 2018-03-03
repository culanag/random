import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 
 * @author Culanag & Cundangan
 *
 */
public class ClientSide {
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String from = "Client: ";		
		System.out.print("Host name/IP address: ");
        String hostName = input.nextLine();
        
        System.out.print("Port #: ");
		int portNumber = input.nextInt();
		input.nextLine();		

        try {
        	Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                System.out.print(from);
                String message = input.nextLine();
                out.println(from + message);
           }
            input.close();
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Couldn't find the host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't establish connection to " + hostName + " at " + portNumber);
            System.exit(1);
        }
    }
}