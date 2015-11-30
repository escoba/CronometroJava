/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author escoba
 */

public class TCPIPClientSocket extends Thread {
    
    private Socket socket;
    private ObjectOutputStream objectoutputstream;
    private ObjectInputStream objectinputstream;
    private List<TCPIPClientSocket> tcpipclientsocketlist;
    private boolean on = true;
    private Cronometro cronometro;
    
    // construtor para uso do client
    public TCPIPClientSocket(String ip, int port) {
        
        try {
            this.socket = new Socket(ip, port);
            objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
            objectinputstream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connection successful");
        }
        catch(UnknownHostException uhe) {
            on = false;
            System.err.println(uhe);
        }
        catch(IOException ioe) {
            on = false;
            System.err.println(ioe);
        }
    }
    
    // construtor para uso do server
    public TCPIPClientSocket(Socket socket, List<TCPIPClientSocket> tcpipclientsocketlist, 
            Cronometro cronometro) {
        
        try {
            this.socket = socket;
            objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
            objectinputstream = new ObjectInputStream(socket.getInputStream());
            this.tcpipclientsocketlist = tcpipclientsocketlist;
            this.cronometro = cronometro;
        }
        catch(UnknownHostException uhe) {
            on = false;
            System.err.println(uhe);
        }
        catch(IOException ioe) {
            on = false;
            System.err.println(ioe);
        }
    }
    
    public void closeConnection() {
        
        try {
            on = false;
            objectinputstream.close();
            objectoutputstream.close();
            socket.close();
        }
        catch(IOException ioe) {
            System.err.println(ioe);
        }
    }
        
    public void sendMessage(String send_menssage) {

        try {
            objectoutputstream.writeObject(send_menssage);
        }
        catch(IOException ioe) {
            on = false;
            System.err.println(ioe);
            closeConnection();
        }
    }
    
    public void sendBroadcastMessage(String send_menssage) {
        tcpipclientsocketlist.stream().forEach((list) -> {
            list.sendMessage(send_menssage);
        });
    }
    
    public String receiveMessage() {
        
        String receive_message = null;
        try {
            receive_message = (String) objectinputstream.readObject();
        } 
        catch (IOException | ClassNotFoundException ex) {
            on = false;
            System.err.println(ex);
            closeConnection();
        }
        return receive_message;
    }
    
    //thread useda apenas pelo server
    @Override
    public void run() {
        
        while(on) {
            String message = receiveMessage();
            //sendBroadcastMessage(message);
            if(message.equals("1")) { //definir tempo de 1 minuto
                cronometro.defineTempo(1, 0);
                System.out.println(message);
            }
            if(message.equals("2")) { //definir tempo de 2 minuto
                cronometro.defineTempo(2, 0);
                System.out.println(message);
            }
            if(message.equals("3")) { //definir tempo de 3 minuto
                cronometro.defineTempo(3, 0);
                System.out.println(message);
            }
            if(message.equals("4")) { //start
                cronometro.iniciar();
                System.out.println(message);
            }
            if(message.equals("5")) { //stop
                cronometro.parar();
                System.out.println(message);
            }
        }
    }
}