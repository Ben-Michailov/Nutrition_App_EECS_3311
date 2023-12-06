package nutrifit;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class generateFoodGroupsChart {
	
public JFreeChart generateBarChartFoodGroups(Date startDate, Date endDate) throws Exception {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ReadWriteController controller = ReadWriteController.getInstance();
		ArrayList<FoodAmountAndFoodGroup> info = controller.retrieveFoodGroupDataBetweenDates(startDate, endDate);
		
		TimeUnit timeUnit=TimeUnit.DAYS;
		long diffInMillies = endDate.getTime() - startDate.getTime();
	    long dayDiff = timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
		
	    dayDiff=dayDiff+1;
	    System.out.println("days between"+dayDiff);
	    //double dairyAmount;
	    double meatAmount =0;
	    double veggieAndFruitAmount=0;
	    double grainAmount=0;
	    
		for (int i =0; i< info.size(); i++) {
				
	
				if (info.get(i).getFoodGroup()==1) {
					// dairy serving size based on https://www.healthline.com/nutrition/how-much-fruit-per-day#TOC_TITLE_HDR_8
					dataset.addValue(info.get(i).getAmount()/250,"Your Intake","Dairy");
				}
			
				if (info.get(i).getFoodGroup()==5) {
					meatAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==7) {
					meatAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==12) {
					meatAmount=+info.get(i).getAmount();
				}
				if (info.get(i).getFoodGroup()==13) {
					meatAmount=+info.get(i).getAmount();
				}
				if (info.get(i).getFoodGroup()==15) {
					meatAmount=+info.get(i).getAmount();
				}
				if (info.get(i).getFoodGroup()==17) {
					meatAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==9) {
					veggieAndFruitAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==11) {
					veggieAndFruitAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==16) {
					veggieAndFruitAmount=+info.get(i).getAmount();
				}
				
				
				if (info.get(i).getFoodGroup()==8) {
					grainAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==18) {
					grainAmount=+info.get(i).getAmount();
				}
				
				if (info.get(i).getFoodGroup()==20) {
					grainAmount=+info.get(i).getAmount();
				}
				
				
	    	  //dataset.addValue(info.get(i).getNutrientName(),info.get(i).getNutrientAmount());
	      }
		
		// meat serving size based on https://www.cmc-cvc.com/sites/default/files/files/MeatIntake%20Fact%20Sheet%20ENG.pdf
	    
		dataset.addValue(meatAmount/65,"Your Intake","Meat");
		
		// Furit and Veggie servinf size source: https://www.healthline.com/nutrition/how-much-fruit-per-day#TOC_TITLE_HDR_8
		dataset.addValue(veggieAndFruitAmount/75,"Your Intake","Fruit Veggie");
		
		// Grain serving size source: https://wholegrainscouncil.org/whole-grains-101/how-much-enough/what-counts-serving
		dataset.addValue(grainAmount/16,"Your Intake","Grain");
	    
	    dataset.addValue(8* dayDiff,"Reccomended","Fruit Veggie");
	    
	    dataset.addValue(8* dayDiff,"Reccomended","Grain");
	    
	    dataset.addValue(2* dayDiff,"Reccomended","Dairy");
	    
	    dataset.addValue(3* dayDiff,"Reccomended","Meat");
	    
	  
		JFreeChart chart=ChartFactory.createBarChart(  
		        "Your Food Group Intake vs Reccomended Over Specified Time Interval", //Chart Title  
		        "Food Groups", // Category axis  
		        "Servings", // Value axis  
		        dataset,  
		        PlotOrientation.VERTICAL,  
		        true,true,false  
		       );  
		 
	    
	    return chart;
	}
}
