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
	JButton stepBtn;
	JButton sendBtn;
    JButton solveBtn;
	
	static int[][] TEST_VALUES_1 = {
		{00,12,11,00,26},
		{13,00,00,14,40},
		{00,15,00,10,35},
		{04,00,16,00,35},
		{25,41,38,32,00},
	};
	
	static int[][] TEST_VALUES_2 = {
		{0, 0, 0, 0, 10},
		{0, 0, 0, 0, 26},
		{0, 0, 0, 0, 42},
		{0, 0, 0, 0, 58},
		{28, 32, 36, 40, 0}
	};

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
        
        sendBtn = new JButton("Send");
        sendBtn.setSize(90,40);
        sendBtn.setLocation((getWidth()/5)-45,190);
        sendBtn.addActionListener(this);
        add(sendBtn);
        
        resetBtn = new JButton("Reset");
        resetBtn.setSize(90,40);
        resetBtn.setLocation((getWidth()/5 * 2)-45,190);
        resetBtn.addActionListener(this);
        add(resetBtn);
		
        solveBtn = new JButton("Solve");
        solveBtn.setSize(90,40);
        solveBtn.setLocation((getWidth()/5 * 3)-45,190);
        solveBtn.addActionListener(this);
        add(solveBtn);
        
        stepBtn = new JButton("Step");
        stepBtn.setSize(90,40);
        stepBtn.setLocation((getWidth()/5 * 4)-45,190);
        stepBtn.addActionListener(this);
        add(stepBtn);
		
		solveBtn.setEnabled(false);
		stepBtn.setEnabled(false);
		
		for (int i = 0; i < 5; i++){
			for (int j = 0; j < 5; j++) {
				int value = TEST_VALUES_1[j][i];
				if (value != 0)
					fields[i][j].setText(value+"");
			}
		}
			
		sendValues();
    }

    private void reset() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (!(i == j && i == 4))
                    fields[i][j].setText("");
        
			solveBtn.setEnabled(false);
			stepBtn.setEnabled(false);
			sendBtn.setEnabled(true);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					fields[i][j].setEditable(true);
				}
			}
    }
    
    public void receiveValues(int[][] inValues) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
				if (inValues[i][j] != 0) {
					fields[i][j].setText(""+inValues[i][j]);
				} else {
					fields[i][j].setText("");
				}
        
    }
	
	public void sendValues() {
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
							System.out.println(i+","+j);
                            fields[i][j].setText("");
                        }
                    }

            if (throwException)
                throw new Exception("E");
            
			solveBtn.setEnabled(true);
			stepBtn.setEnabled(true);
			sendBtn.setEnabled(false);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					fields[i][j].setEditable(false);
				}
			}
            Main.receiveValues(values);
        } catch (Exception e2) {
			e2.printStackTrace();
		}
	}
//    
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == resetBtn) {
//            reset();
//        } else if (e.getSource() == solveBtn) {
//			solveBtn.setEnabled(false);
//			stepBtn.setEnabled(false);
//            Main.startSolve();
//        } else if (e.getSource() == stepBtn) {
//			Main.doStep();
//        } else if (e.getSource() == sendBtn) {
//			sendValues();
//		}
    }
    
}
