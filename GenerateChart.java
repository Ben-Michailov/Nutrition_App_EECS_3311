package nutrifit;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import java.util.concurrent.TimeUnit;
//OLD Modded
public class GenerateChart {
	
	public JFreeChart generateChart(Date startDate, Date endDate, int amountListed) throws Exception {
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<NutrientNameAndAmount> info = controller.retrieveAdvancedDataBetweenDates(startDate, endDate,amountListed);
		//System.out.println(info.getProtein());
		System.out.println(startDate +""+ endDate);
	      DefaultPieDataset dataset = new DefaultPieDataset( );
	      //dataset.setValue("test",1.0);
	      for (int i =0; i< amountListed; i++) {
	    	  dataset.setValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
	      /*dataset.setValue("Protein", info.getProtein() );
	      dataset.setValue("Fat", info.getFat() );
	      dataset.setValue("Carbohydrates", info.getCarbohydrates());
	      dataset.setValue("Sugar", info.getSugar() );*/

	      JFreeChart chart = ChartFactory.createPieChart(
	         "% of Nutrients Consumed",   // chart title
	         dataset,          // data
	         true,             // include legend
	         true,
	         false);
	      
	      return chart;
	}
	
public JFreeChart generateBarChartReccomendedMacro(Date startDate, Date endDate) throws Exception {
		generateBarChartReccomendedMacro Macro = new generateBarChartReccomendedMacro();
		JFreeChart chart= Macro.generateBarChartReccomendedMacro(startDate, endDate); 
	    return chart;
	}

public JFreeChart generateBarChartReccomendedMilli(Date startDate, Date endDate) throws Exception {
	
	generateBarChartReccomendedMilli Milli = new generateBarChartReccomendedMilli();
	JFreeChart chart = Milli.generateBarChartReccomendedMilli(startDate, endDate);  
    return chart;
}

	public JFreeChart generateBarChartReccomendedMicro(Date startDate, Date endDate) throws Exception {
		
		generateBarChartReccomendedMicro Micro = new generateBarChartReccomendedMicro();
		JFreeChart chart= Micro.generateBarChartReccomendedMicro(startDate, endDate);  
		 
	    
	    return chart;
	}
	
	public JFreeChart generateBarChartFoodGroups(Date startDate, Date endDate) throws Exception {
		
		generateFoodGroupsChart food = new generateFoodGroupsChart();
	    
	  
		JFreeChart chart=food.generateBarChartFoodGroups(startDate, endDate);  
		 
	    
	    return chart;
	}
	
	public JFreeChart generateLineChartCalories(Date startDate, Date endDate) throws Exception{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<DateAndCalories> info = controller.retrieveCalorieDataBetweenDates(startDate, endDate);
		
		for (int i =0; i< info.size(); i++) {
			dataset.addValue(info.get(i).getCaloriesBurned(), "Calories Burned",info.get(i).getDate() );
			dataset.addValue(info.get(i).getCaloriesConsumed(), "Calories Consumed",info.get(i).getDate() );
		}
		
		JFreeChart chart = ChartFactory.createLineChart(  
		        "Calories Burned vs Calories Consumed", // Chart title  
		        "Date", // X-Axis Label  
		        "Calories (kCal)", // Y-Axis Label  
		        dataset  
		        );  
		
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