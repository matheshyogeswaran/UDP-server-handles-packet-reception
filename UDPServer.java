import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    public final static int SERVICE_PORT = 50002; // Use a different port number
    // Fixed the assignment operator

    public static void main(String[] args) {
        try {
            // 1. Create a DatagramSocket object
            DatagramSocket serverSocket = new DatagramSocket(SERVICE_PORT);
            System.out.println("created a datafram socket"+serverSocket.toString());
            // 2. Create Buffers for storing data in DatagramPacket object
            byte[] buffReceiveData = new byte[1024]; // Fixed variable assignment
            byte[] buffSendData = new byte[1024];

            // 3. Create DatagramPacket object for wrapping the incoming packet
            DatagramPacket packetIn = new DatagramPacket(buffReceiveData, buffReceiveData.length);
            System.out.println("create a datagram packet "+ packetIn.toString());
            // 4. Receive incoming datagram into DatagramPacket object.
            try {
                // This is a blocking system call.
                serverSocket.receive(packetIn); // Program blocks here
                System.out.println("received packet");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 5. Get the data from the received packet
            String strInData = new String(packetIn.getData());
            System.out.println("RECEIVED DATA: " + strInData);

            // 6. Find sender's address and port from the received packet
            InetAddress inAddress = packetIn.getAddress();
            int inPort = packetIn.getPort();

            // 7. Create datagram to send
            buffSendData = strInData.toUpperCase().getBytes(); // Fixed variable assignment
            DatagramPacket packetOut = new DatagramPacket(buffSendData, buffSendData.length, inAddress, inPort);
            System.out.println("datagram packet about to sent"+packetOut.toString());
            // 8. Send the response datagram
            try {
                serverSocket.send(packetOut);
                System.out.println("sent the packet out "+ packetOut.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 9. Close the DatagramSocket
            serverSocket.close();
            System.out.println("socket is clossed now");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
