package nutrifit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class generateBarChartReccomendedMilli {
	
	public JFreeChart generateBarChartReccomendedMilli(Date startDate, Date endDate) throws Exception {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<NutrientNameAndAmount> info = controller.retrieveAdvancedDataBetweenDates(startDate, endDate,80);
		
		TimeUnit timeUnit=TimeUnit.DAYS;
		long diffInMillies = endDate.getTime() - startDate.getTime();
	    long dayDiff = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		
	    dayDiff=dayDiff+1;
	    System.out.println("days between"+dayDiff);
	    
		for (int i =0; i< info.size(); i++) {
				
				if (info.get(i).getNutrientName().equals("SODIUM")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Sodium");
				}
				
				if (info.get(i).getNutrientName().equals("CHOLESTEROL")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Cholesterol");
				}
				if (info.get(i).getNutrientName().equals("PHOSPHORUS")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Phosphorus");
				}
				if (info.get(i).getNutrientName().equals("POTASSIUM")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Potassium");
				}
				if (info.get(i).getNutrientName().equals("CALCIUM")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Calcium");
				}
				if (info.get(i).getNutrientName().equals("MAGNESIUM")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Magnesium");
				}		
				
	    	  //dataset.addValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
		
		// Reccomended Values taken from: https://www.fda.gov/media/99059/download
		// Reccomended Values taken from: https://www.fda.gov/media/99069/download also
	    
	    dataset.addValue(2.3*dayDiff, "Reccomended", "Sodium");
	    
	    dataset.addValue(3*dayDiff, "Reccomended", "Cholesterol");
	    
	    dataset.addValue(1.25*dayDiff,"Reccomended","Phosphorus");
	    
	    dataset.addValue(4.7*dayDiff,"Reccomended","Potassium");
	    
	    dataset.addValue(1.3*dayDiff,"Reccomended","Calcium");
	    
	    dataset.addValue(0.42*dayDiff,"Reccomended","Magnesium");
	    
	    
		JFreeChart chart=ChartFactory.createBarChart(  
		        "Your Nutrient Intake vs Reccomended Over Specified Time Interval", //Chart Title  
		        "Nutrient", // Category axis  
		        "Amount (in mg)", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,false  
		       );  
	    return chart;
	}
	
}
