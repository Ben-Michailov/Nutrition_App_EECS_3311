package nutrifit;

public class NutrientNameAndAmount {
	private String nutrientName;
	private double nutrientAmount;
	
	public NutrientNameAndAmount(String nutrientName,double nutrientAmount) {
		this.nutrientName=nutrientName;
		this.nutrientAmount = nutrientAmount;
	}
	public String getNutrientName() {
		return nutrientName;
	}
	public void setNutrientName(String nutrientName) {
		this.nutrientName = nutrientName;
	}
	public double getNutrientAmount() {
		return nutrientAmount;
	}
	public void setNutrientAmount(double nutrientAmount) {
		this.nutrientAmount = nutrientAmount;
	}
	
	@Override
	public String toString() {
		return nutrientName+", "+nutrientAmount;
	}
}
