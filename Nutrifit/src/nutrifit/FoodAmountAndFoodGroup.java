package nutrifit;

public class FoodAmountAndFoodGroup {
	double amount;
	int foodGroup;
	
	public FoodAmountAndFoodGroup(double amount, int foodGroup) {
		this.amount = amount;
		this.foodGroup = foodGroup;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getFoodGroup() {
		return foodGroup;
	}
	public void setFoodGroup(int foodGroup) {
		this.foodGroup = foodGroup;
	}
	
	@Override
	public String toString() {
		return amount+", "+foodGroup;
	}
}
