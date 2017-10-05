/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumassolver;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author movillaf
 */
public class Window extends JFrame implements ActionListener {
    
    JTextField[][] fields;
    JButton resetBtn;
    JButton solveBtn;

    public Color createColor(javafx.scene.paint.Color inColor) {
        float hue = (float) inColor.getHue();
        float saturation = (float) inColor.getSaturation();
        float brightness = (float) inColor.getBrightness();
        
        return Color.getHSBColor(hue/360f, saturation, brightness);
    }
    
    public Window() {
        
        setTitle("Sumas");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,270);
        
        init();
        
        setVisible(true);
    }
    
    private void init() {
        fields = new JTextField[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String label = "";
                if (i == j && j == 4)
                    label = "R.";
                
                JTextField field = new JTextField(label);
                field.setSize(80, 30);
                field.setLocation(10+(85*i), 10+(35*j));
                field.setBackground( (i == 4 || j == 4)? createColor(javafx.scene.paint.Color.BURLYWOOD) : createColor(javafx.scene.paint.Color.ANTIQUEWHITE) );
                if (i == j && j == 4)
                    field.setEditable(false);
                fields[i][j] = field;
                add(fields[i][j]);
            }
        }
        solveBtn = new JButton("Solve");
        solveBtn.setSize(90,40);
        solveBtn.setLocation(220,190);
        solveBtn.addActionListener(this);
        add(solveBtn);
        
        resetBtn = new JButton("Reset");
        resetBtn.setSize(90,40);
        resetBtn.setLocation(120,190);
        resetBtn.addActionListener(this);
        add(resetBtn);
    }

    private void reset() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (!(i == j && i == 4))
                    fields[i][j].setText("");
        
    }
    
    private void startSolve() {
        try {
            int[][] values = new int[5][5];
            boolean throwException = false;
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    if ((i == j && i == 4)) {
                        values[i][j] = 0;
                    } else {
                        String text = fields[i][j].getText();
                        if (text.isEmpty())
                            text = "0";
                        try {
                            values[i][j] = Integer.parseInt(text);
                        } catch (Exception e1) {
                            throwException = true;
                            fields[i][j].setText("");
                        }
                    }

            if (throwException)
                throw new Exception("E");
            
            Main.startSolve(values);
        } catch (Exception e2) { }
    }
    
    public void receiveValues(int[][] inValues) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                fields[i][j].setText(""+inValues[i][j]);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetBtn) {
            reset();
        } else if (e.getSource() == solveBtn) {
            startSolve();
        }
    }
    
}
