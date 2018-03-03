import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * @author Culanag & Cundangan
 *
 */
public class ServerSide {
    public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		String from = "Server: ";

		System.out.print("Port # to connect: ");
		int portNumber = input.nextInt();
		input.nextLine();

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            String outputLine;
			out.println("You are now chatting with Server. Say \"Bye\" to end chat.");
            while ((inputLine = in.readLine()) != null) {
                 System.out.println(inputLine);
                 System.out.print(from);
                 outputLine = input.nextLine();
                 out.println(from + outputLine);

               if (outputLine.equals("Bye."))
                  	System.exit(0);
            }
            clientSocket.close();
            serverSocket.close();
            input.close();
        } catch (IOException e) {
            System.out.println("Can't listen to port number " + portNumber);
        }
    }
}

