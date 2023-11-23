package nutrifit;

public class YesDateYesFoodState extends MealLoggingState{
	public YesDateYesFoodState(MealLoggingPage mealLoggingPage) {
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
		mealLoggingPage.submitButtonState(true);
		
		
		
	}

	@Override
	public MealLoggingState switchState(MealLoggingEvent event) {
		
		if(event == MealLoggingEvent.CHANGEIT) {
			return new NoDateYesFoodState(mealLoggingPage);
		}
		else if (event == MealLoggingEvent.SUBMIT) {
			return new NoDateNoFoodState(mealLoggingPage);
		}
		
		return this;
	}
}
