package nutrifit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import nutrifit.patterns.User;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HomePage extends JFrame implements ActionListener {
        private JButton edit;
        private JButton back;
        private JButton logMeal;
        private JTextArea tout;
        private JTextArea tout2;
        private JButton logExercise;
        private JButton visualizeData;
        private JButton bmr;
        private JButton weightLoss;
     
        
        /**
         * Home Page frame set
         *
         */
        public HomePage() throws IOException {

            setTitle("Home Page");
            setBounds(300, 90, 900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);

            Container c = getContentPane();
            c.setLayout(null);

            
            
            tout = new JTextArea();
            tout.setFont(new Font("Arial", Font.PLAIN, 15));
            tout.setSize(300, 400);
            tout.setLocation(500, 100);
            tout.setLineWrap(true);
            tout.setEditable(false);
            c.add(tout);
            UserProfile usertable = new UserProfile();
            
            tout.setText(usertable.readTable().toString());

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
            
            bmr = new JButton("BMR");
            bmr.setFont(new Font("Arial", Font.PLAIN, 15));
            bmr.setSize(100, 20);
            bmr.setLocation(150, 300);
            bmr.addActionListener(this);
            c.add(bmr);
            
            
            weightLoss = new JButton("Weight Loss Calculator");
            weightLoss.setFont(new Font("Arial", Font.PLAIN, 15));
            weightLoss.setSize(240, 20);
            weightLoss.setLocation(150, 340);
            weightLoss.addActionListener(this);
            c.add(weightLoss);
            
            tout2 = new JTextArea();
            tout2.setFont(new Font("Arial", Font.PLAIN, 15));
            tout2.setSize(150, 20);
            tout2.setLocation(270, 300);
            tout2.setLineWrap(true);
            tout2.setEditable(false);
            c.add(tout2);
            
          

            setVisible(true);
    }
	 /**
         * button listener
         *
         */
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
            else if(e.getSource() == bmr) {
            	UserProfile usertable = new UserProfile();
		User user = usertable.readTable();
		tout2.setText(Double.toString(user.BMRcalculator()));
            }
            else if(e.getSource() == weightLoss) {
            	UserProfile usertable = new UserProfile();
            	User user = usertable.readTable();
            	new WeightLossGui(user);
            }
            else{
                setVisible(false);
                new editPage();
            }
    }
}
