package nutrifit;

import javax.swing.*;

import nutrifit.ReadWriteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ExerciseLoggingPage extends JFrame implements ActionListener {
	private JButton back;
    private JTextArea tout;
    private JComboBox texercise;
    private JLabel exercise;
    private JLabel intensity;
    private JComboBox tintensity;
    private JButton sub;
    private JLabel date;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    private JLabel successOrFail;
    private JTextField tduration; 
    private JLabel duration;
    
    ReadWriteController dataStorage = new ReadWriteController();
	
    //dataStorage.createNewTable();
    
    private String possibleExercises[]
            = { "Walking","Runnning","Swimming","Biking"};
    
    private String possibleIntensities[] = { "Low","Medium","High"};
    
    public ExerciseLoggingPage() throws IOException {

        setTitle("Exercise Logging Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);

        String s1 = ""; //dataStorage.debugDumpDatabase();
        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        tout.setText(s1);

        back = new JButton("back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(270, 450);
        back.addActionListener(this);
        c.add(back);

        /*edit = new JButton("edit");
        edit.setFont(new Font("Arial", Font.PLAIN, 15));
        edit.setSize(100, 20);
        edit.setLocation(270, 450);
        edit.addActionListener(this);
        c.add(edit);*/

        setVisible(true);
        
        exercise = new JLabel("Enter Exercise");
        exercise.setFont(new Font("Arial", Font.PLAIN, 15));
        exercise.setSize(1000, 20);
        exercise.setLocation(100, 150);
        c.add(exercise);
        
   
        
        texercise = new JComboBox(possibleExercises);
        texercise.setFont(new Font("Arial", Font.PLAIN, 15));
        texercise.setSize(110, 25);
        texercise.setLocation(100, 175);
        c.add(texercise);
        
        intensity = new JLabel("Enter Exercise Intensity");
        intensity.setFont(new Font("Arial", Font.PLAIN, 15));
        intensity.setSize(1000, 20);
        intensity.setLocation(100, 225);
        c.add(intensity);
        
        tintensity = new JComboBox(possibleIntensities);
        tintensity.setFont(new Font("Arial", Font.PLAIN, 15));
        tintensity.setSize(110, 25);
        tintensity.setLocation(100, 250);
        c.add(tintensity);
        

        

        
        date = new JLabel("Enter Date of Exercise (DD MM YYYY)");
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(1000, 20);
        date.setLocation(100, 300);
        c.add(date);
        
        day = new JTextField();
        day.setFont(new Font("Arial", Font.PLAIN, 15));
        day.setSize(25, 20);
        day.setLocation(100, 325);
        c.add(day);
        
        month = new JTextField();
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(25, 20);
        month.setLocation(130, 325);
        c.add(month);
        
        year = new JTextField();
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(50, 20);
        year.setLocation(160, 325);
        c.add(year);
        
        duration = new JLabel("Enter Duration of Exercise (in minutes)");
        duration.setFont(new Font("Arial", Font.PLAIN, 15));
        duration.setSize(1000, 20);
        duration.setLocation(100, 375);
        c.add(duration);
        
        tduration = new JTextField();
        tduration.setFont(new Font("Arial", Font.PLAIN, 15));
        tduration.setSize(100, 20);
        tduration.setLocation(100, 400);
        c.add(tduration);
        

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);
        
        successOrFail = new JLabel("");
        successOrFail.setFont(new Font("Arial", Font.PLAIN, 20));
        successOrFail.setSize(1000, 20);
        successOrFail.setLocation(100, 500);
        c.add(successOrFail);
           
          
           
           
   }

		
		


		
   @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == back){
                setVisible(false);
                try {
					new HomePage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }else if (e.getSource() == sub) {
            		
            	
            	
            	

            	String exerciseChosen = (String)texercise.getSelectedItem();
            	String intensityChosen = (String)tintensity.getSelectedItem();
            	//System.out.println();
            	int durationOfExercise;
            	
            	durationOfExercise = Integer.valueOf(tduration.getText());
            	
            	int caloriesBurned =0;
            	if (intensityChosen.equals("Low")) {
            		caloriesBurned=2*durationOfExercise;
            	}
            	else if (intensityChosen.equals("Medium")) {
            		caloriesBurned=5*durationOfExercise;
            	}
            	else if (intensityChosen.equals("High")) {
            		caloriesBurned=10*durationOfExercise;
            	}
            	
            	int dayInt = Integer.valueOf(day.getText());
            	int monthInt = Integer.valueOf(month.getText()) - 1;
            	int YearInt = Integer.valueOf(year.getText()) - 1900;
            	
            	Date d1 = new Date(YearInt,monthInt,dayInt); 
            	
            	try {
					dataStorage.storeExercise(caloriesBurned ,d1);
					successOrFail.setText("Exercise Log Successfully Entered");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					successOrFail.setText("ERROR");
				}
            	String output = dataStorage.debugDumpDatabase();
            	tout.setText(output);




        }
    }
   /*public static void main(String[] args) throws IOException, InterruptedException, Exception {  
		ExerciseLoggingPage test = new ExerciseLoggingPage();
   } */
}


