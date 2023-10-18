package nutrifit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class welcomePage extends JFrame implements ActionListener {
    private JButton createnew;
    private JButton login;
    public welcomePage() {

        setTitle("Welcome Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);


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
            setVisible(false);
            try {
                new HomePage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }




}