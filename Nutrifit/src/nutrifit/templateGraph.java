package nutrifit;

import java.io.*;
import java.sql.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
public class templateGraph {
	
	public JFreeChart generateBarChart(Date startDate, Date endDate) throws Exception{
		ReadWriteController controller = ReadWriteController.getInstance();
		HealthInfo info = controller.retrieveDataBetweenDates(startDate, endDate);
		System.out.println(info.getProtein());
		System.out.println(startDate +""+ endDate);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      //dataset.setValue("test",1.0);
	      dataset.addValue(info.getCaloriesConsumed(), "Calories", "Calories Consumed");
	      dataset.addValue(info.getCaloriesBurned(), "Calories burned", "Calories burned");

	      JFreeChart chart = ChartFactory.createBarChart(
	    		  "Nutrient Bar Chart",           
	    	         "Category",            
	    	         "Score",            
	    	         dataset,          
	    	         PlotOrientation.VERTICAL,           
	    	         true, true, false);
	      
	      return chart;
	}
	
	

}
