package nutrifit;

public class NoDateYesFoodState extends MealLoggingState {
	
	
	public NoDateYesFoodState(MealLoggingPage mealLoggingPage) {
		super(mealLoggingPage);
		// TODO Auto-generated constructor stub
	}

	public void handle() {
		
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
			return new YesDateYesFoodState(mealLoggingPage);
		}
		
		return this;
	}
}