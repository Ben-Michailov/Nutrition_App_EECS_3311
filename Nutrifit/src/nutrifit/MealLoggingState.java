package nutrifit;

public abstract class MealLoggingState {
	MealLoggingPage mealLoggingPage;
	public MealLoggingState(MealLoggingPage mealLoggingPage) {
		this.mealLoggingPage = mealLoggingPage;
	}
	public abstract void handle();
	
	public abstract MealLoggingState switchState(MealLoggingEvent event);
}
