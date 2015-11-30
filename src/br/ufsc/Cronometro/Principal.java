/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

/**
 *
 * @author escoba
 */

public class Principal {
    
    public static void main(String args[]) {
        
        Display tela = new Display();
        Cronometro cronometro = new Cronometro(tela);
        //cronometro.defineTempo(0, 2);
        //cronometro.iniciar();
        tela.setVisible(true);
        TCPIPServerSocket tcp = new TCPIPServerSocket(cronometro, 9999);
        tcp.acceptSocket();
    }
}
