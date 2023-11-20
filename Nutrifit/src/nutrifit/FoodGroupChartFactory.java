package nutrifit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Date;
import java.time.LocalDate;

public class FoodGroupChartFactory {

    public JFreeChart generateChart(String chartType) {
        if ("average meal".equalsIgnoreCase(chartType)) {
            return generateAverageMealChart();
        } else if ("CFG".equalsIgnoreCase(chartType)) {
            return generateCFGChart();
        } else {
            throw new IllegalArgumentException("Invalid chart type: " + chartType);
        }
    }

    private JFreeChart generateAverageMealChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Date currentDate = Date.valueOf(LocalDate.now());


        HealthInfo healthInfo;
        try {
            healthInfo = ReadWriteController.getInstance().retrieveDataBetweenDates(currentDate, currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        double totalAmount = healthInfo.getCarbohydrates() + healthInfo.getProtein() +
                healthInfo.getFat() + healthInfo.getSugar();

        double carbohydratesPercentage = (healthInfo.getCarbohydrates() / totalAmount) * 100;
        double proteinPercentage = (healthInfo.getProtein() / totalAmount) * 100;
        double fatPercentage = (healthInfo.getFat() / totalAmount) * 100;
        double sugarPercentage = (healthInfo.getSugar() / totalAmount) * 100;


        dataset.setValue("Carbohydrates", carbohydratesPercentage);
        dataset.setValue("Protein", proteinPercentage);
        dataset.setValue("Fat", fatPercentage);
        dataset.setValue("Sugar", sugarPercentage);


        JFreeChart chart = ChartFactory.createPieChart(
                "Average Meal Composition",  
                dataset,              
                true,                  
                true,
                false);

        return chart;
    }

    private JFreeChart generateCFGChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();


        double proteinPercentage = 25.0;
        double carbohydratesPercentage = 25.0;
        double fruitsAndVegetablesPercentage = 50.0;


        dataset.setValue("Protein", proteinPercentage);
        dataset.setValue("Carbohydrates", carbohydratesPercentage);
        dataset.setValue("Fruits and Vegetables", fruitsAndVegetablesPercentage);


        JFreeChart chart = ChartFactory.createPieChart(
                "CFG Composition",  
                dataset,            
                true,               
                true,
                false);


        return chart;
    }


}

