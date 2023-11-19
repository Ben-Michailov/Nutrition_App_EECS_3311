package nutrifit;

public class NoDateNoFoodState extends MealLoggingState {
	
	
	public NoDateNoFoodState(MealLoggingPage mealLoggingPage) {
		super(mealLoggingPage);
		// TODO Auto-generated constructor stub
	}

	public void handle() {
		mealLoggingPage.clearFood();
		
		mealLoggingPage.setDateAndMealButtonState(true);
		mealLoggingPage.enterDateState(true);
		mealLoggingPage.typeOfMealComboBoxState(true);
		
		mealLoggingPage.amountTextFieldState(false);
		mealLoggingPage.foodComboBoxState(false);
		mealLoggingPage.submitButtonState(false);
		mealLoggingPage.addIngredientState(false);
		mealLoggingPage.changeDateState(false);
		
		
	}
	
	public MealLoggingState switchState(MealLoggingEvent event) {
		if(event == MealLoggingEvent.SETDATEANDMEAL) {
			return new YesDateNoFoodState(mealLoggingPage);
		}
		
		return this;
	}
}
