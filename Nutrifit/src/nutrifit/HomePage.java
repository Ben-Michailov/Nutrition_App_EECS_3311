package nutrifit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HomePage extends JFrame implements ActionListener {
        private JButton edit;
        private JButton back;
        private JButton logMeal;
        private JTextArea tout;
        private JButton logExercise;
        private JButton visualizeData;
        
        public HomePage() throws IOException {

            setTitle("Home Page");
            setBounds(300, 90, 900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            Datebase datebase = new Datebase();
            String[] sl = datebase.readUser();
            String s1 = "";
            tout = new JTextArea();
            tout.setFont(new Font("Arial", Font.PLAIN, 15));
            tout.setSize(300, 400);
            tout.setLocation(500, 100);
            tout.setLineWrap(true);
            tout.setEditable(false);
            c.add(tout);
            for (String s : sl) {
                s1=s1 + s +"\n";
            }
            tout.setText(s1);

            back = new JButton("back");
            back.setFont(new Font("Arial", Font.PLAIN, 15));
            back.setSize(100, 20);
            back.setLocation(150, 450);
            back.addActionListener(this);
            c.add(back);

            edit = new JButton("edit");
            edit.setFont(new Font("Arial", Font.PLAIN, 15));
            edit.setSize(100, 20);
            edit.setLocation(270, 450);
            edit.addActionListener(this);
            c.add(edit);
            
            logMeal = new JButton("log meal");
            logMeal.setFont(new Font("Arial", Font.PLAIN, 15));
            logMeal.setSize(100, 20);
            logMeal.setLocation(150, 500);
            logMeal.addActionListener(this);
            c.add(logMeal);
            
            logExercise = new JButton("log exercise");
            logExercise.setFont(new Font("Arial", Font.PLAIN, 15));
            logExercise.setSize(150, 20);
            logExercise.setLocation(270, 500);
            logExercise.addActionListener(this);
            c.add(logExercise);
            
            visualizeData = new JButton("visualize data");
            visualizeData.setFont(new Font("Arial", Font.PLAIN, 15));
            visualizeData.setSize(150, 20);
            visualizeData.setLocation(150, 400);
            visualizeData.addActionListener(this);
            c.add(visualizeData);

            setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == back){
                setVisible(false);
                new welcomePage();
            }
            else if(e.getSource() == logMeal){
            	setVisible(false);
                try {
					new MealLoggingPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if(e.getSource() == logExercise){
            	setVisible(false);
                try {
					new ExerciseLoggingPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else if(e.getSource() == visualizeData){
            	setVisible(false);
                try {
					new DataVisualizationPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else{
                setVisible(false);
                new editPage();
            }
    }
}
