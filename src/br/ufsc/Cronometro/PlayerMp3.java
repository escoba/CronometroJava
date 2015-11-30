/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author escoba
 */

public class PlayerMp3 extends Thread {
    
    private File file = null;
    private Player player = null;
    private FileInputStream fileinputstream = null;
    
    public PlayerMp3(File file) {
        
        this.file = file;
    }
    
    public void play() {
        
        try {
            fileinputstream = new FileInputStream(file);
            player = new Player(fileinputstream);
            player.play();
        }
        catch(JavaLayerException|FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    @Override
    public void run() {
        play();
    }
}