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
    private JButton top5;
    private JButton top10;
    private JButton barGraphReccomendedNutrients;
    private JButton barGraphMilli;
    private JButton barGraphMicro;
    
    private ChartPanel chartPanel;
   // private JLabel successOrFail;

    
    ReadWriteController dataStorage = ReadWriteController.getInstance();
	
    
    
    public DataVisualizationPage() throws IOException {

        setTitle("Data Visualization Page");
        setBounds(300, 90, 925, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container c = getContentPane();
        c.setLayout(null);




        back = new JButton("back");
        back.setFont(new Font("Arial", Font.PLAIN, 15));
        back.setSize(100, 20);
        back.setLocation(80, 450);
        back.addActionListener(this);
        c.add(back);


        setVisible(true);

        
        text1 = new JLabel("Visualize Your Data From a Given Date to a Given Date");
        text1.setFont(new Font("Arial", Font.PLAIN, 20));
        text1.setSize(1000, 20);
        text1.setLocation(10, 100);
        c.add(text1);
        
        text2 = new JLabel("Enter Start Date and Then End Date (DD-MM-YYYY)");
        text2.setFont(new Font("Arial", Font.PLAIN, 15));
        text2.setSize(1000, 20);
        text2.setLocation(10, 150);
        c.add(text2);
        
        from = new JLabel("from");
        from.setFont(new Font("Arial", Font.PLAIN, 12));
        from.setSize(1000, 20);
        from.setLocation(10, 175);
        c.add(from);
        
        
        
        startDay = new JTextField();
        startDay.setFont(new Font("Arial", Font.PLAIN, 15));
        startDay.setSize(25, 20);
        startDay.setLocation(10, 200);
        c.add(startDay);
        
        startMonth = new JTextField();
        startMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        startMonth.setSize(25, 20);
        startMonth.setLocation(40, 200);
        c.add(startMonth);
        
        startYear = new JTextField();
        startYear.setFont(new Font("Arial", Font.PLAIN, 15));
        startYear.setSize(50, 20);
        startYear.setLocation(70, 200);
        c.add(startYear);
        
        to = new JLabel("to");
        to.setFont(new Font("Arial", Font.PLAIN, 12));
        to.setSize(1000, 20);
        to.setLocation(10, 225);
        c.add(to);
        
        endDay = new JTextField();
        endDay.setFont(new Font("Arial", Font.PLAIN, 15));
        endDay.setSize(25, 20);
        endDay.setLocation(10, 250);
        c.add(endDay);
        
        endMonth = new JTextField();
        endMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        endMonth.setSize(25, 20);
        endMonth.setLocation(40, 250);
        c.add(endMonth);
        
        endYear = new JTextField();
        endYear.setFont(new Font("Arial", Font.PLAIN, 15));
        endYear.setSize(50, 20);
        endYear.setLocation(70, 250);
        c.add(endYear);
       
        

        /*sub = new JButton("Basic Nutrients");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(80, 450);
        sub.addActionListener(this);
        c.add(sub);*/
        
        top5 = new JButton("Top 5");
        top5.setFont(new Font("Arial", Font.PLAIN, 15));
        top5.setSize(100, 20);
        top5.setLocation(80, 500);
        top5.addActionListener(this);
        c.add(top5);
        
        top10 = new JButton("Top 10");
        top10.setFont(new Font("Arial", Font.PLAIN, 15));
        top10.setSize(100, 20);
        top10.setLocation(190, 500);
        top10.addActionListener(this);
        c.add(top10);
        
        barGraphReccomendedNutrients = new JButton("Show Reccomended Nutrients (g)");
        barGraphReccomendedNutrients.setFont(new Font("Arial", Font.PLAIN, 15));
        barGraphReccomendedNutrients.setSize(300, 20);
        barGraphReccomendedNutrients.setLocation(80, 540);
        barGraphReccomendedNutrients.addActionListener(this);
        c.add(barGraphReccomendedNutrients);
        
        barGraphMilli = new JButton("Show Reccomended Nutrients (mg)");
        barGraphMilli.setFont(new Font("Arial", Font.PLAIN, 15));
        barGraphMilli.setSize(300, 20);
        barGraphMilli.setLocation(80, 580);
        barGraphMilli.addActionListener(this);
        c.add(barGraphMilli);
        
        barGraphMicro = new JButton("Show Reccomended Nutrients (mcg)");
        barGraphMicro.setFont(new Font("Arial", Font.PLAIN, 15));
        barGraphMicro.setSize(300, 20);
        barGraphMicro.setLocation(80, 620);
        barGraphMicro.addActionListener(this);
        c.add(barGraphMicro);

        chartPanel.setEnabled(false);
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
            }else if (e.getSource() == top5) {
            		
            	
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
            		chart = generateChart.generateChart(startDate,endDate,5);
            		if (chartPanel != null) {
            			chartPanel.setChart(chart);
            		}
            		else {
	        			chartPanel = new ChartPanel(chart);
	            		chartPanel.setSize(450,500);
	            		chartPanel.setLocation(450, 150);
	            		add(chartPanel);
            		}

            		repaint();
            		revalidate();
            		setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            

        } else if (e.getSource() == top10) {
    		
        	
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
    		chart = generateChart.generateChart(startDate,endDate,10);
    		if (chartPanel != null) {
    			chartPanel.setChart(chart);
    		}
    		else {
    			chartPanel = new ChartPanel(chart);
        		chartPanel.setSize(450,500);
        		chartPanel.setLocation(450, 150);
        		add(chartPanel);
    		}
    		repaint();
    		revalidate();
    		setVisible(true);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
        }
            
        else if (e.getSource() == barGraphReccomendedNutrients) {
    		
        	
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
    			chart = generateChart.generateBarChartReccomendedMacro(startDate,endDate);
    			if (chartPanel != null) {
    				chartPanel.setChart(chart);
    			}
    			else {
    				chartPanel = new ChartPanel(chart);
    	    		chartPanel.setSize(450,500);
    	    		chartPanel.setLocation(450, 150);
    	    		add(chartPanel);
    			}
    			repaint();
    			revalidate();
    			setVisible(true);
    			
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	
    	    }
   
   else if (e.getSource() == barGraphMilli) {
		
   	
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
			chart = generateChart.generateBarChartReccomendedMilli(startDate,endDate);
			if (chartPanel != null) {
				chartPanel.setChart(chart);
			}
			else {
				chartPanel = new ChartPanel(chart);
	    		chartPanel.setSize(450,500);
	    		chartPanel.setLocation(450, 150);
	    		add(chartPanel);
			}
			repaint();
			revalidate();
			setVisible(true);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	    }
            
   else if (e.getSource() == barGraphMicro) {
		
	   	
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
			chart = generateChart.generateBarChartReccomendedMicro(startDate,endDate);
			if (chartPanel != null) {
				chartPanel.setChart(chart);
			}
			else {
				chartPanel = new ChartPanel(chart);
	    		chartPanel.setSize(450,500);
	    		chartPanel.setLocation(450, 150);
	    		add(chartPanel);
			}
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


