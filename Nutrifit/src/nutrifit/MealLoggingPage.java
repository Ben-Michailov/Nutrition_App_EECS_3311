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

public class MealLoggingPage extends JFrame implements ActionListener {
	private JButton back;
    private JTextArea tout;
    private JComboBox tfood;
    private JLabel food;
    private JTextField tamount;
    private JLabel amount;
    private JComboBox meals;
    private JLabel meal;
    private JButton sub;
    private JLabel date;
    private JTextField day;
    private JTextField month;
    private JTextField year;
    private JLabel successOrFail;
    
    ReadWriteController dataStorage = ReadWriteController.getInstance();
	
    //dataStorage.createNewTable();
    
    private String possibleMeals[]
            = { "breakfast","lunch","dinner","snack"};
    
    private String possibleFoods[] = dataStorage.foodNames();
    
    public MealLoggingPage() throws IOException {

        setTitle("Meal Logging Page");
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
        
        food = new JLabel("Enter Food Eaten");
        food.setFont(new Font("Arial", Font.PLAIN, 15));
        food.setSize(1000, 20);
        food.setLocation(100, 150);
        c.add(food);
        
   
        
        tfood = new JComboBox(possibleFoods);
        tfood.setFont(new Font("Arial", Font.PLAIN, 15));
        tfood.setSize(400, 20);
        tfood.setLocation(100, 175);
        c.add(tfood);
        
        amount = new JLabel("Enter Amount Eaten (in grams)");
        amount.setFont(new Font("Arial", Font.PLAIN, 15));
        amount.setSize(1000, 20);
        amount.setLocation(100, 225);
        c.add(amount);
        
        tamount = new JTextField();
        tamount.setFont(new Font("Arial", Font.PLAIN, 15));
        tamount.setSize(100, 20);
        tamount.setLocation(100, 250);
        c.add(tamount);
        
        meal = new JLabel("Enter Meal");
        meal.setFont(new Font("Arial", Font.PLAIN, 15));
        meal.setSize(1000, 20);
        meal.setLocation(100, 300);
        c.add(meal);
        
        meals = new JComboBox(possibleMeals);
        meals.setFont(new Font("Arial", Font.PLAIN, 15));
        meals.setSize(100, 20);
        meals.setLocation(100, 325);
        c.add(meals);
        
        date = new JLabel("Enter Date of Meal (DD MM YYYY)");
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(1000, 20);
        date.setLocation(100, 375);
        c.add(date);
        
        day = new JTextField();
        day.setFont(new Font("Arial", Font.PLAIN, 15));
        day.setSize(25, 20);
        day.setLocation(100, 400);
        c.add(day);
        
        month = new JTextField();
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(25, 20);
        month.setLocation(130, 400);
        c.add(month);
        
        year = new JTextField();
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(50, 20);
        year.setLocation(160, 400);
        c.add(year);
        

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

		
		/*public static void main(String[] args) throws IOException, InterruptedException, Exception {  
			MealLoggingPage test = new MealLoggingPage();
	    } */ 


		
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
            		
            	//System.out.println(tfood.getText()+" "+(String)meals.getSelectedItem() +" " + tamount.getText());
            	
            	
            	
            	ReadWriteController.MealType selectedMeal = ReadWriteController.MealType.SNACK;
            	String selectedMealString = (String)meals.getSelectedItem();
            	
            	if (selectedMealString.equals("breakfast")) {
            		selectedMeal = ReadWriteController.MealType.BREAKFAST;
            	}
            	else if (selectedMealString.equals("lunch")) {
            		selectedMeal = ReadWriteController.MealType.LUNCH;
            	}
            	else if (selectedMealString.equals("dinner")) {
            		selectedMeal = ReadWriteController.MealType.DINNER;
            	}
            	else if (selectedMealString.equals("snack")) {
            		selectedMeal = ReadWriteController.MealType.SNACK;
            	}
            	String foodChosen = (String)tfood.getSelectedItem();
            	int foodID;
            	int amountEaten;
            	
            	foodID = dataStorage.IDOfAGivenFood(foodChosen);
            	amountEaten = Integer.valueOf(tamount.getText());
            	
            	int dayInt = Integer.valueOf(day.getText());
            	int monthInt = Integer.valueOf(month.getText()) - 1;
            	int YearInt = Integer.valueOf(year.getText()) - 1900;
            	
            	Date d1 = new Date(YearInt,monthInt,dayInt); 
            	
            	try {
					dataStorage.storeMeal(selectedMeal,foodID , amountEaten,d1);
					successOrFail.setText("Meal Log Successfully Entered");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					successOrFail.setText("ERROR: Meal Already Entered");
				}
            	String output = dataStorage.debugDumpDatabase();
            	tout.setText(output);
            	
               /* String sex;

                if (male.isSelected()) {
                    sex = "M";
                }else {
                    sex = "F";
                }
                String dob= ""+ (String)date.getSelectedItem()
                        + "/" + (String)month.getSelectedItem()
                        + "/" + (String)year.getSelectedItem();



            try {
                user = new User(tname.getText(),tage.getText(),dob,theight.getText(),tweight.getText(),sex);
                Datebase usertable = new Datebase();
                usertable.createNew(user);
                tout.setText(user.toString());
                tout.setEditable(false);
                res.setText("edit Successfully..");
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }*/



        }
    }
}

