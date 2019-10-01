import java.io.IOException;

//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;

import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class console_test_restapi {
	
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        
		//get the localhost IP address, if server is running on some other IP, you need to use that
        //InetAddress host = InetAddress.getLocalHost();
        String host = "myhost";
        
        int port = 80;
        String path = "mypath";
        String mystr = String.format("GET %s HTTP/1.1\r\nHost: %s\r\nUser-Agent: curl/7.58.0\r\nAccept: */*\r\n\r\n", path, host);
        
		
		Socket socket = null;
        //ObjectOutputStream oos = null;
        //ObjectInputStream ois = null;
        
        //establish socket connection to server
        socket = new Socket(host, port);
        
        //write to socket using ObjectOutputStream
        //oos = new ObjectOutputStream(socket.getOutputStream());
        //oos.writeObject(mystr);
        
        //OutputStream outstream = socket.getOutputStream();
        //outstream.write(mystr);
        
        System.out.println("Sending HTTP GET:");
        DataOutputStream wr = new DataOutputStream(socket.getOutputStream());
        wr.writeBytes(mystr);
        wr.flush();
            
        //read the server response message
        //ois = new ObjectInputStream(socket.getInputStream());
        //String message = (String) ois.readObject();
        //System.out.println("Message: " + message);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while ((inputLine = in.readLine()) != null) {
        	response.append(inputLine);
        }
        
        //print response
        System.out.println("Getting response:");
        System.out.println(response.toString());
        
        //close resources
        wr.close();
        in.close();
        socket.close();
        
        System.out.println("DONE");
        //Thread.sleep(100);
        
    }//main
}//class
