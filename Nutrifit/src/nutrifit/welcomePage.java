package nutrifit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class welcomePage extends JFrame implements ActionListener {
	private JLabel nutrifit;
	private JLabel teamKilo;
	private JButton createnew;
    	private JButton login;
   	public welcomePage() {

        setTitle("Welcome Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);
        
        nutrifit = new JLabel("Nutrifit");
        nutrifit.setFont(new Font("Arial", Font.BOLD, 50));
        nutrifit.setSize(1000, 50);
        nutrifit.setLocation(150, 120);
        c.add(nutrifit);
        
        teamKilo = new JLabel("by Team Kilo");
        teamKilo.setFont(new Font("Arial", Font.PLAIN, 20));
        teamKilo.setSize(1000, 40);
        teamKilo.setLocation(150, 160);
        c.add(teamKilo);

        createnew = new JButton("new user");
        createnew.setFont(new Font("Arial", Font.PLAIN, 15));
        createnew.setSize(100, 20);
        createnew.setLocation(150, 450);
        createnew.addActionListener(this);
        c.add(createnew);

        login = new JButton("log in");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(270, 450);
        login.addActionListener(this);
        c.add(login);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createnew){
            setVisible(false);
            new editPage();
        }else{
            
           try {
            	Database user = new UserProfile();
            	if(user.tableExists("user_profile")) {
            		setVisible(false);
            		new HomePage();
            	}else {
            		JOptionPane.showMessageDialog(this, "there is no existed user-profile, please make a new one!");
            	}
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }


    }




}
