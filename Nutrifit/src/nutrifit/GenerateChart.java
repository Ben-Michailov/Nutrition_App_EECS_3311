package nutrifit;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

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
	
public JFreeChart generateBarChartReccomended(Date startDate, Date endDate) throws Exception {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<NutrientNameAndAmount> info = controller.retrieveAdvancedDataBetweenDates(startDate, endDate,40);
		
		TimeUnit timeUnit=TimeUnit.DAYS;
		long diffInMillies = endDate.getTime() - startDate.getTime();
	    long dayDiff = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		
	    System.out.println("days between"+dayDiff);
	    
		for (int i =0; i< 15; i++) {
				if (info.get(i).getNutrientName().equals("PROTEIN")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Protein");
				}
				if (info.get(i).getNutrientName().equals("CARBOHYDRATE, TOTAL (BY DIFFERENCE)")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Carbohydrates");
				}
				if (info.get(i).getNutrientName().equals("FAT (TOTAL LIPIDS)")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Fats");
				}
				
				if (info.get(i).getNutrientName().equals("FIBRE, TOTAL DIETARY")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Fibre");
				}
				
				if (info.get(i).getNutrientName().equals("SODIUM")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Sodium");
				}
				
				if (info.get(i).getNutrientName().equals("CHOLESTEROL")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Cholesterol");
				}
				
				
	    	  //dataset.addValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
		
		// Reccomended Values taken from: https://www.fda.gov/media/99059/download

	    dataset.addValue(50*dayDiff, "Reccomended", "Protein");   
 
	    dataset.addValue(78*dayDiff, "Reccomended", "Fats");   
 
	    dataset.addValue(275*dayDiff, "Reccomended", "Carbohydrates");  
	    
	    dataset.addValue(28*dayDiff, "Reccomended", "Fibre");
	    
	    dataset.addValue(2.3*dayDiff, "Reccomended", "Sodium");
	    
	    dataset.addValue(3*dayDiff, "Reccomended", "Cholesterol");
	    
		JFreeChart chart=ChartFactory.createBarChart(  
		        "Your Nutrient Intake vs Reccomended Over Specified Time Interval", //Chart Title  
		        "Nutrient", // Category axis  
		        "Amount (in grams)", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,false  
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