package nutrifit;

public class YesDateNoFoodState  extends MealLoggingState{
	public YesDateNoFoodState(MealLoggingPage mealLoggingPage) {
		super(mealLoggingPage);
		// TODO Auto-generated constructor stub
	}

	public void handle() {
		
		mealLoggingPage.setDateAndMealButtonState(false);
		mealLoggingPage.enterDateState(false);
		mealLoggingPage.typeOfMealComboBoxState(false);
		mealLoggingPage.searchState(true);
		
		mealLoggingPage.amountTextFieldState(true);
		mealLoggingPage.foodComboBoxState(true);
		mealLoggingPage.changeDateState(true);
		mealLoggingPage.addIngredientState(true);
		
		mealLoggingPage.submitButtonState(false);
		
		
		
	}

	@Override
	public MealLoggingState switchState(MealLoggingEvent event) {
		
		if(event == MealLoggingEvent.CHANGEIT) {
			return new NoDateNoFoodState(mealLoggingPage);
		}
		else if (event == MealLoggingEvent.ADDINGREDIENT) {
			return new YesDateYesFoodState(mealLoggingPage);
		}
		
		return this;
	}
}
