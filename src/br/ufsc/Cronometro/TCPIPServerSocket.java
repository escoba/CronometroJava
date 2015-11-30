/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author escoba
 */

public class TCPIPServerSocket {
    
    private ServerSocket serversocket;
    private List<TCPIPClientSocket> tcpipclientsocketlist;
    private Cronometro cronometro;
    
    public TCPIPServerSocket(Cronometro cronometro, int port) {
        try {
            this.cronometro = cronometro;
            serversocket = new ServerSocket(port);
            tcpipclientsocketlist = new ArrayList<>();
            System.out.println("Server started on port "+port+".");
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    public void acceptSocket() {
        
        while(true) {
            try {
                Socket socket = serversocket.accept();
                TCPIPClientSocket tcpipclientsocket = 
                        new TCPIPClientSocket(socket, tcpipclientsocketlist, cronometro);
                tcpipclientsocketlist.add(tcpipclientsocket);
                new Thread(tcpipclientsocket).start();
                System.out.println("Connection accepted by IP address "+socket.getInetAddress()+".");
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}