package nutrifit;

import javax.swing.*;
import java.awt.*;

public class editPageGUI extends JFrame{
    private JLabel res;
    private JTextField tname;
    private JTextField tage;
    private JRadioButton male;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JTextArea theight;
    private JTextArea tweight;
    private JButton sub;
    private JButton back;
    JTextArea tout, resadd;

    protected String[] dates = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    protected String[] months = {"01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12"};
    protected String[] years = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019"};

    /**
     * edit Page frame
     *
     */

    public editPageGUI(){
        setTitle("Edit Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Container c = getContentPane();
        c.setLayout(null);

        defaultLabel(c);
        defaultText(c);
        defaultBox(c);
        defaultButton(c);

        setVisible(true);
    }
    /**
     * set default labels
     *
     */
    private void defaultLabel(Container c){
        JLabel title = createJLabel("Edit Form",30,300,30,300,30);
        c.add(title);
        JLabel name = createJLabel("Name",20,100,20,100,100);
        c.add(name);
        JLabel age = createJLabel("Age",20,100,20,100,150);
        c.add(age);
        JLabel gender = createJLabel("Gender",20,100,20,100,200);
        c.add(gender);
        JLabel dob = createJLabel("DOB",20,100,20,100,250);
        c.add(dob);
        JLabel height = createJLabel("Height",20,100,20,100,300);
        c.add(height);
        JLabel weight = createJLabel("Weight",20,100,20,100,350);
        c.add(weight);
        res = createJLabel("",20,500,25,100,500);
        c.add(res);
    }
    /**
     * set default text frame
     *
     */
    private void defaultText(Container c){
        tname = createJTextField(190, 100);
        c.add(tname);
        tage = createJTextField(100, 150);
        c.add(tage);
        theight = createJTextArea(15,100,20,200,300);
        c.add(theight);
        tweight = createJTextArea(15,100,20,200,350);
        c.add(tweight);
        tout = createJTextArea(15,300,400,500,100);
        tout.setEditable(false);
        c.add(tout);
        resadd = createJTextArea(15,200,75,580,175);
        c.add(resadd);
    }
    /**
     * set default box
     *
     */
    private void defaultBox(Container c){
        date = createJComboBox(dates,15,50,20,200,250);
        c.add(date);
        month = createJComboBox(months,15,60,20,250,250);
        c.add(month);
        year = createJComboBox(years,15,60,20,320,250);
        c.add(year);
    }
    /**
     * set default button
     *
     */
    private void defaultButton(Container c){

        male = createJRadioButton("Male",true, 200);
        c.add(male);
        JRadioButton female = createJRadioButton("Female", false,280);
        c.add(female);
        ButtonGroup gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        c.add(sub);

        back = new JButton("Back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(270, 450);
        c.add(back);
    }
    /**
     * function for label
     *
     */
    private JLabel createJLabel(String text,int size,int width,int height,int x,int y){
        JLabel result = new JLabel(text);
        result.setFont(new Font("Arial", Font.PLAIN, size));
        result.setSize(width, height);
        result.setLocation(x, y);
        return result;
    }
    /**
     * function for text
     *
     */
    private JTextField createJTextField(int width, int y){
        JTextField result = new JTextField();
        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setSize(width, 20);
        result.setLocation(200, y);
        return result;
    }
    /**
     * function for button
     *
     */
    private JRadioButton createJRadioButton(String text, boolean b, int x){
        JRadioButton result = new JRadioButton(text);
        result.setFont(new Font("Arial", Font.PLAIN, 15));
        result.setSelected(b);
        result.setSize(80, 20);
        result.setLocation(x, 200);
        return result;
    }
    /**
     * function for box
     *
     */
    public JComboBox createJComboBox(String[] item, int size,int width,int height,int x,int y){
        JComboBox result = new JComboBox(item);
        result.setFont(new Font("Arial", Font.PLAIN, size));
        result.setSize(width, height);
        result.setLocation(x, y);
        return result;
    }
    /**
     * function for text view
     *
     */
    public JTextArea createJTextArea(int size,int width,int height,int x,int y){
        JTextArea result = new JTextArea();
        result.setFont(new Font("Arial", Font.PLAIN, size));
        result.setSize(width, height);
        result.setLocation(x, y);
        result.setLineWrap(true);
        return result;
    }
    /**
     * get methods
     *
     */
    public JLabel getRes() {
        return res;
    }

    public JTextField getTname() {
        return tname;
    }

    public JTextField getTage() {
        return tage;
    }

    public JRadioButton getMale() {
        return male;
    }

    public JComboBox getDate() {
        return date;
    }

    public JComboBox getMonth() {
        return month;
    }
    public JComboBox getYear() {
        return year;
    }
    public JTextArea getTheight() {
        return theight;
    }

    public JTextArea getTweight() {
        return tweight;
    }

    public JButton getSub() {
        return sub;
    }

    public JButton getBack() {
        return back;
    }
    public JTextArea getTout() {
        return tout;
    }

}
