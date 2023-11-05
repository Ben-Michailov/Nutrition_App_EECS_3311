package nutrifit;

import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import nutrifit.ReadWriteController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DataVisualizationPage extends JFrame implements ActionListener {
	private JButton back;
    private JButton sub;
    private JLabel text1;
    private JTextField startDay;
    private JTextField startMonth;
    private JTextField startYear;
    private JLabel text2;
    private JLabel from;
    private JLabel to;
    private JTextField endDay;
    private JTextField endMonth;
    private JTextField endYear;
    
    private ChartPanel chartPanel;
   // private JLabel successOrFail;

    
    ReadWriteController dataStorage = ReadWriteController.getInstance();
	
    
    
    public DataVisualizationPage() throws IOException {

        setTitle("Exercise Logging Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);




        back = new JButton("back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(270, 450);
        back.addActionListener(this);
        c.add(back);


        setVisible(true);

        
        text1 = new JLabel("Visualize Your Data From a Given Date to a Given Date");
        text1.setFont(new Font("Arial", Font.PLAIN, 20));
        text1.setSize(1000, 20);
        text1.setLocation(100, 100);
        c.add(text1);
        
        text2 = new JLabel("Enter Start Date and Then End Date (DD-MM-YYYY)");
        text2.setFont(new Font("Arial", Font.PLAIN, 15));
        text2.setSize(1000, 20);
        text2.setLocation(100, 150);
        c.add(text2);
        
        from = new JLabel("from");
        from.setFont(new Font("Arial", Font.PLAIN, 12));
        from.setSize(1000, 20);
        from.setLocation(100, 175);
        c.add(from);
        
        
        
        startDay = new JTextField();
        startDay.setFont(new Font("Arial", Font.PLAIN, 15));
        startDay.setSize(25, 20);
        startDay.setLocation(100, 200);
        c.add(startDay);
        
        startMonth = new JTextField();
        startMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        startMonth.setSize(25, 20);
        startMonth.setLocation(130, 200);
        c.add(startMonth);
        
        startYear = new JTextField();
        startYear.setFont(new Font("Arial", Font.PLAIN, 15));
        startYear.setSize(50, 20);
        startYear.setLocation(160, 200);
        c.add(startYear);
        
        to = new JLabel("to");
        to.setFont(new Font("Arial", Font.PLAIN, 12));
        to.setSize(1000, 20);
        to.setLocation(100, 225);
        c.add(to);
        
        endDay = new JTextField();
        endDay.setFont(new Font("Arial", Font.PLAIN, 15));
        endDay.setSize(25, 20);
        endDay.setLocation(100, 250);
        c.add(endDay);
        
        endMonth = new JTextField();
        endMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        endMonth.setSize(25, 20);
        endMonth.setLocation(130, 250);
        c.add(endMonth);
        
        endYear = new JTextField();
        endYear.setFont(new Font("Arial", Font.PLAIN, 15));
        endYear.setSize(50, 20);
        endYear.setLocation(160, 250);
        c.add(endYear);
       
        

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 450);
        sub.addActionListener(this);
        c.add(sub);
      
           
          
           
           
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
            		
            	
            	int startDayInt = Integer.valueOf(startDay.getText());
            	int startMonthInt = Integer.valueOf(startMonth.getText()) - 1;
            	int startYearInt = Integer.valueOf(startYear.getText()) - 1900;
            	
            	int endDayInt = Integer.valueOf(endDay.getText());
            	int endMonthInt = Integer.valueOf(endMonth.getText()) - 1;
            	int endYearInt = Integer.valueOf(endYear.getText()) - 1900;
            	
            	Date startDate = new Date(startYearInt,startMonthInt,startDayInt); 
            	Date endDate = new Date(endYearInt,endMonthInt,endDayInt);
            	
            	GenerateChart generateChart = new GenerateChart();
            	JFreeChart chart;
            	
            	try {
            		chart = generateChart.generateChart(startDate,endDate);
            		chartPanel = new ChartPanel(chart);
            		chartPanel.setSize(300,300);
            		chartPanel.setLocation(450, 150);
            		add(chartPanel);
            		repaint();
            		revalidate();
            		setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            

        }
    }
  /* public static void main(String[] args) throws IOException, InterruptedException, Exception {  
		DataVisualizationPage test = new DataVisualizationPage();
   } */
}


