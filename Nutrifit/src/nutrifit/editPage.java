package nutrifit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class editPage  extends JFrame implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel name;
    private JTextField tname;
    private JLabel age;
    private JTextField tage;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JLabel height;
    private JTextArea theight;
    private JLabel weight;
    private JTextArea tweight;
    private JButton sub;
    private JButton back;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019" };
    private JPanel panel1;

    public editPage(){
        setTitle("Edit Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Edit Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        age = new JLabel("Age");
        age.setFont(new Font("Arial", Font.PLAIN, 20));
        age.setSize(100, 20);
        age.setLocation(100, 150);
        c.add(age);

        tage = new JTextField();
        tage.setFont(new Font("Arial", Font.PLAIN, 15));
        tage.setSize(100, 20);
        tage.setLocation(200, 150);
        c.add(tage);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 200);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 200);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(200, 250);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(250, 250);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(320, 250);
        c.add(year);

        height = new JLabel("Height");
        height.setFont(new Font("Arial", Font.PLAIN, 20));
        height.setSize(100, 20);
        height.setLocation(100, 300);
        c.add(height);

        theight = new JTextArea();
        theight.setFont(new Font("Arial", Font.PLAIN, 15));
        theight.setSize(100, 20);
        theight.setLocation(200, 300);
        theight.setLineWrap(true);
        c.add(theight);

        weight = new JLabel("Weight");
        weight.setFont(new Font("Arial", Font.PLAIN, 20));
        weight.setSize(100, 20);
        weight.setLocation(100, 350);
        c.add(weight);

        tweight = new JTextArea();
        tweight.setFont(new Font("Arial", Font.PLAIN, 15));
        tweight.setSize(100, 20);
        tweight.setLocation(200, 350);
        tweight.setLineWrap(true);
        c.add(tweight);



        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(270, 450);
        back.addActionListener(this);
        c.add(back);

        tout = new JTextArea();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setLineWrap(true);
        tout.setEditable(false);
        c.add(tout);

        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
        User user;
        if (e.getSource() == sub) {


                String sex;

                if (male.isSelected()) {
                    sex = "M";
                }else {
                    sex = "F";
                }
                String dob= ""+ (String)date.getSelectedItem()
                        + "/" + (String)month.getSelectedItem()
                        + "/" + (String)year.getSelectedItem();


                
            try {
            	if(tname.getText().isEmpty() || tage.getText().isEmpty() ||dob.isEmpty() || theight.getText().isEmpty() ||tweight.getText().isEmpty() ||sex.isEmpty()) {
            		 res.setText("input can not be null");
                }else {
                	user = new User(tname.getText(),tage.getText(),dob,theight.getText(),tweight.getText(),sex);
                	Datebase usertable = new Datebase();
                	usertable.createNew(user);
                	tout.setText(user.toString());
                	tout.setEditable(false);
                	res.setText("edit Successfully..");
                }
            } catch (ParseException | IOException ex) {
                ex.printStackTrace();
            }



        }

        else if (e.getSource() == back) {
            setVisible(false);
            new welcomePage();


        }
    }

}
