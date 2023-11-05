package nutrifit;
import java.sql.Date;



public class WeightLossCalculator {

    private User user;
    ReadWriteController controller;

    public WeightLossCalculator(User user) {
        ReadWriteController controller = ReadWriteController.getInstance();
        this.user= user;

    }

    public double calculateWeightLoss(Date targetDate) throws Exception {
        // Calculate the total calorie intake and expenditure until the target date

        Date currentDate = new Date(System.currentTimeMillis());
        HealthInfo info = controller.retrieveDataBetweenDates(currentDate, targetDate);
        double caloriesGained = info.getCaloriesConsumed();
        double caloriesBurned = info.getCaloriesBurned();



        double netCalories = caloriesGained - caloriesBurned;


        double potentialWeightChange = netCalories / 7700;


        double projectedWeight = user.getWeight() - potentialWeightChange;

        return projectedWeight;
    }

    /*
    public static void main(String[] args) {




        WeightLossCalculator calculator = new WeightLossCalculator();


        Date targetDate = new Date(); //


        double projectedWeight = calculator.calculateWeightLoss(targetDate);

        System.out.println("Projected weight on " + targetDate + ": " + projectedWeight + " kg");
    }

     */
}
