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


 /**
     * Generates a pie chart representing the percentage of nutrients consumed between the specified dates.
     *
     * @param startDate     Start date of the interval
     * @param endDate       End date of the interval
     * @param amountListed  Number of nutrients to include in the chart
     * @return JFreeChart object representing the pie chart
     * @throws Exception if there is an issue retrieving data or generating the chart
     */
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


	    /**
     * Generates a bar chart comparing the user's nutrient intake to recommended values over a specified time interval.
     *
     * @param startDate Start date of the interval
     * @param endDate   End date of the interval
     * @return JFreeChart object representing the bar chart
     * @throws Exception if there is an issue retrieving data or generating the chart
     */
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
    /**
     * Generates a bar chart comparing the user's nutrient intake to recommended values (in milligrams) over a specified time interval.
     *
     * @param startDate Start date of the interval
     * @param endDate   End date of the interval
     * @return JFreeChart object representing the bar chart
     * @throws Exception if there is an issue retrieving data or generating the chart
     */
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

	
	 /**
	 * Generates a bar chart comparing the user's intake of micro-nutrients to recommended values (in micrograms) over a specified time interval.
	 *
	 * @param startDate Start date of the interval
	 * @param endDate   End date of the interval
	 * @return JFreeChart object representing the bar chart
	 * @throws Exception if there is an issue retrieving data or generating the chart
	 */

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


	 /**
	 * Generates a bar chart comparing the user's intake of various food groups to recommended servings over a specified time interval.
	 *
	 * @param startDate Start date of the interval
	 * @param endDate   End date of the interval
	 * @return JFreeChart object representing the bar chart
	 * @throws Exception if there is an issue retrieving data or generating the chart
	 */

	
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

	 /**
	 * Generates a line chart comparing calories burned and consumed over a specified time interval.
	 *
	 * @param startDate Start date of the interval
	 * @param endDate   End date of the interval
	 * @return JFreeChart object representing the line chart
	 * @throws Exception if there is an issue retrieving data or generating the chart
	 */
	
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
