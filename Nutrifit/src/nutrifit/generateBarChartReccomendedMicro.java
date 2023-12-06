package nutrifit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class generateBarChartReccomendedMicro {
	
public JFreeChart generateBarChartReccomendedMicro(Date startDate, Date endDate) throws Exception {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<NutrientNameAndAmount> info = controller.retrieveAdvancedDataBetweenDates(startDate, endDate,80);
		
		TimeUnit timeUnit=TimeUnit.DAYS;
		long diffInMillies = endDate.getTime() - startDate.getTime();
	    long dayDiff = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		
	    dayDiff=dayDiff+1;
	    System.out.println("days between"+dayDiff);
	    
		for (int i =0; i< info.size(); i++) {
				
	
				if (info.get(i).getNutrientName().equals("ZINC")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Zinc");
				}
			
				if (info.get(i).getNutrientName().equals("IRON")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Iron");
				}
				if (info.get(i).getNutrientName().equals("VITAMIN C")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Vit C");
				}
				if (info.get(i).getNutrientName().equals("VITAMIN B-6")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Vit B-6");
				}
				if (info.get(i).getNutrientName().equals("VITAMIN K")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Vit K");
				}
				
				if (info.get(i).getNutrientName().equals("VITAMIN D (D2 + D3)")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Vit D");
				}
				
				if (info.get(i).getNutrientName().equals("VITAMIN B-12")) {
					dataset.addValue(info.get(i).getNutrientAmount(),"Your Intake","Vit B-12");
				}
				
				
	    	  //dataset.addValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
		
		// Reccomended Values taken from: https://www.fda.gov/media/99059/download
		// Reccomended Values taken from: https://www.fda.gov/media/99069/download also
	    
	
	    
	    dataset.addValue(0.0011*dayDiff,"Reccomended","Zinc");
	    
	    dataset.addValue(0.018*dayDiff,"Reccomended","Iron");
	    
	    dataset.addValue(0.09*dayDiff,"Reccomended","Vit C");
	    
	    dataset.addValue(0.0017*dayDiff,"Reccomended","Vit B-6");
	    
	    dataset.addValue(0.00012*dayDiff,"Reccomended","Vit K");
	    
	    dataset.addValue(0.00002*dayDiff,"Reccomended","Vit D");
	    
	    dataset.addValue(0.0000024*dayDiff,"Reccomended","Vit B-12");
	    
		JFreeChart chart=ChartFactory.createBarChart(  
		        "Your Nutrient Intake vs Reccomended Over Specified Time Interval", //Chart Title  
		        "Nutrient", // Category axis  
		        "Amount (in mcg)", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,false  
		       );  
		 
	    
	    return chart;
	}

}
