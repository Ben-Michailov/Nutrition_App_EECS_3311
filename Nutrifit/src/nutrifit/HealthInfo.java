package nutrifit;

import java.sql.Date;

public class HealthInfo {
	private Date date = null;
	private char meal = 'n';
	private int foodID = 0;
	private double amountRatio = 0.0;
	private int caloriesConsumed =0;
	private double protein=0.0;
	private double fat=0.0;
	private double carbohydrates=0.0;
	private double sugar=0.0;
	private int caloriesBurned=0;
	
	public HealthInfo() {
		
	}
	public HealthInfo(Date date, char meal, int foodID, double amountRatio, int caloriesConsumed, double protein, double fat,
			double carbohydrates, double sugar, int caloriesBurned) {
		super();
		this.date = date;
		this.meal = meal;
		this.foodID = foodID;
		this.amountRatio = amountRatio;
		this.caloriesConsumed = caloriesConsumed;
		this.protein = protein;
		this.fat = fat;
		this.carbohydrates = carbohydrates;
		this.sugar = sugar;
		this.caloriesBurned = caloriesBurned;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public char getMeal() {
		return meal;
	}
	public void setMeal(char meal) {
		this.meal = meal;
	}
	public int getFoodID() {
		return foodID;
	}
	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}
	public double getAmountRatio() {
		return amountRatio;
	}
	public void setAmountRatio(double amountRatio) {
		this.amountRatio = amountRatio;
	}
	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}
	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public int getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	@Override
	public String toString() {
		return "HealthInfo [date=" + date + ", meal=" + meal + ", foodID=" + foodID + ", amountRatio=" + amountRatio
				+ ", caloriesConsumed=" + caloriesConsumed + ", protein=" + protein + ", fat=" + fat
				+ ", carbohydrates=" + carbohydrates + ", sugar=" + sugar + ", caloriesBurned=" + caloriesBurned + "]";
	}

	
	
	
}
