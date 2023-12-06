package nutrifit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class generateBarChartReccomendedMacro {
public JFreeChart generateBarChartReccomendedMacro(Date startDate, Date endDate) throws Exception {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<NutrientNameAndAmount> info = controller.retrieveAdvancedDataBetweenDates(startDate, endDate,40);
		
		TimeUnit timeUnit=TimeUnit.DAYS;
		long diffInMillies = endDate.getTime() - startDate.getTime();
	    long dayDiff = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	    dayDiff=dayDiff+1;
	    System.out.println("days between"+dayDiff);
	    
		for (int i =0; i< info.size(); i++) {
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
				
				if (info.get(i).getNutrientName().equals("SUGARS, TOTAL")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Sugar");
				}
				
				
	    	  //dataset.addValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
		
		// Reccomended Values taken from: https://www.fda.gov/media/99059/download

	    dataset.addValue(50*dayDiff, "Reccomended", "Protein");   
 
	    dataset.addValue(78*dayDiff, "Reccomended", "Fats");   
 
	    dataset.addValue(275*dayDiff, "Reccomended", "Carbohydrates");  
	    
	    dataset.addValue(28*dayDiff, "Reccomended", "Fibre");
	    
	    dataset.addValue(50*dayDiff, "Reccomended", "Sugar");
	    
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
}
