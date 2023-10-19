package nutrifit;

public class HealthInfo {
	private int caloriesConsumed =0;
	private double protein=0.0;
	private double fat=0.0;
	private double carbohydrates=0.0;
	private double sugar=0.0;
	private int caloriesBurned=0;
	
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
		return "HealthInfo [caloriesConsumed=" + caloriesConsumed + ", protein=" + protein + ", fat=" + fat
				+ ", carbohydrates=" + carbohydrates + ", sugar=" + sugar + ", caloriesBurned=" + caloriesBurned + "]";
	}
	
}
