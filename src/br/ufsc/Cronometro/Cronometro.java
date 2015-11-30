/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Timer;

/**
 *
 * @author escoba
 */

public class Cronometro {
    
    private final Timer timer;
    private int minutos;
    private int segundos;
    private Display tela;
    
    public Cronometro(Display tela) {
        this.tela = tela;
        timer = new Timer(1000, actionListener);
    }
     
    public void defineTempo(int minutos, int segundos) {
        this.minutos = minutos;
        this.segundos = segundos;
        tela.setTime(formatData(minutos, segundos));
        defineCor();
    }
    
    public void iniciar() {
        if(!timer.isRunning())
            timer.start();
    }
    
    public void parar() {
        timer.stop();
        if(minutos == 0 && segundos == 0)
            corneta();
    }
    
    public void corneta() {
        PlayerMp3 mp3 = new PlayerMp3(new File("/home/escoba/NetBeansProjects/Cronometro/mp3/corneta.mp3"));
        //PlayerMp3 mp3 = new PlayerMp3(new File("C:/TI/corneta.mp3"));
        Thread t = new Thread(mp3);
        t.start();
    }

    ActionListener actionListener = (ActionEvent e) -> {
        
        if(segundos == 0 && minutos != 0) {
            minutos--;
            segundos = 59;
        }
        else {
            segundos--;
            if(segundos == 0) {
                if(minutos == 0)
                    parar();
                else {
                    minutos--;
                    segundos = 59;
                }
            }
        }
        defineCor();
        tela.setTime(formatData(minutos, segundos));
    };
    
    private String formatData(int minutos, int segundos) {
        String min = minutos < 10 ? "0"+String.valueOf(minutos):String.valueOf(minutos);
        String seg = segundos < 10 ? "0"+String.valueOf(segundos):String.valueOf(segundos);
        return min+":"+seg;
    }
    
    private void defineCor() {
        if(minutos > 0)
            tela.setColor(2);
        else {
            if(segundos < 10 && segundos > 3)
                tela.setColor(1);
            else if (segundos < 4)
                tela.setColor(0);
        }
    }
}
