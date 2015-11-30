/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufsc.Cronometro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author escoba
 */

public class Display extends JFrame {
    
    private final JLabel jlabel_tempo;
    private final JLabel jlabel_desenvolvido;
    private final JPanel jpanel_tempo;
    private final JPanel jpanel_desenvolvido;
    private final JLabel jLabel_brasao;
    
    public Display() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(d);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        GridBagLayout gb_tempo = new GridBagLayout();
        
        jlabel_tempo = new JLabel("00:00");
        jlabel_tempo.setFont(new Font("", Font.BOLD, 350));
        jlabel_tempo.setForeground(Color.blue);
        
        jlabel_desenvolvido = new JLabel("  Tecnologia da Informação - Joinville");
        jlabel_desenvolvido.setFont(new Font("", Font.BOLD, 50));
        jlabel_desenvolvido.setForeground(Color.black);
        
        jLabel_brasao = new JLabel(new ImageIcon("/home/escoba/NetBeansProjects/Cronometro/images/brasao.jpg"));
        //jLabel_brasao = new JLabel(new ImageIcon("C:/TI/brasao.jpg"));
        
        
        jpanel_tempo = new JPanel();
        jpanel_tempo.add(jlabel_tempo);
        jpanel_tempo.setLayout(gb_tempo);
        
        jpanel_desenvolvido = new JPanel();
        jpanel_desenvolvido.add(jLabel_brasao);
        jpanel_desenvolvido.add(jlabel_desenvolvido);
        jpanel_desenvolvido.setLayout(gb_tempo);
        
        getContentPane().add("Center", jpanel_tempo);
        getContentPane().add("South", jpanel_desenvolvido);        
    }
    
    public void setTime(String tempo) {
        jlabel_tempo.setText(tempo);
    }
    
    public void setColor(int cor) {
        switch(cor) {
            case 0: jlabel_tempo.setForeground(Color.red); break;
            case 1: jlabel_tempo.setForeground(Color.orange); break;
            case 2: jlabel_tempo.setForeground(Color.blue); break;
        }
    }
}
