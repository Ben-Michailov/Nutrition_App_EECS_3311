package nutrifit;

import java.io.*;
import java.sql.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GenerateChart {
	
	public JFreeChart generateChart(Date startDate, Date endDate) throws Exception {
		ReadWriteController controller = ReadWriteController.getInstance();
		HealthInfo info = controller.retrieveDataBetweenDates(startDate, endDate);
		System.out.println(info.getProtein());
		System.out.println(startDate +""+ endDate);
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      //dataset.setValue("test",1.0);
	      dataset.setValue("Protein", info.getProtein() );
	      dataset.setValue("Fat", info.getFat() );
	      dataset.setValue("Carbohydrates", info.getCarbohydrates());
	      dataset.setValue("Sugar", info.getSugar() );

	      JFreeChart chart = ChartFactory.createPieChart(
	         "% of Nutrients Consumed",   // chart title
	         dataset,          // data
	         true,             // include legend
	         true,
	         false);
	      
	      return chart;
	}

   
   /*public static void main( String[ ] args ) throws Exception {
	   
	  
	  Date d1= new Date(0);
	  Date d2= new Date(300,0,0);
	  HealthInfo info = controller.retrieveDataBetweenDates(d1, d2);
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue("Protein", info.getProtein() );
      dataset.setValue("Fat", info.getFat() );
      dataset.setValue("Carbohydrates", info.getCarbohydrates());
      dataset.setValue("Sugar", info.getSugar() );

      JFreeChart chart = ChartFactory.createPieChart(
         "% of Nutrients Consumed",   // chart title
         dataset,          // data
         true,             // include legend
         true,
         false);
         
      int width = 640;   //Width of the image 
      int height = 480;  //Height of the image 
      JFrame frame = new JFrame("Distance/Energy Converter");
      frame.setSize(600, 600);
      ChartPanel chartPanel2 = new ChartPanel(chart);
      frame.setContentPane(chartPanel2);
      frame.setVisible(true);
      
      
      //File pieChart = new File( "PieChart2.jpeg" ); 
      //ChartUtils.saveChartAsJPEG( pieChart , chart , width , height );
      System.out.println("test");
   }*/
}