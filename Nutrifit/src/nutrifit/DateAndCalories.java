package nutrifit;

import java.sql.Date;

public class DateAndCalories {
	Date date;
	int caloriesConsumed;
	int caloriesBurned;
	
	public DateAndCalories(Date date, int caloriesConsumed, int caloriesBurned) {
		super();
		this.date = date;
		this.caloriesConsumed = caloriesConsumed;
		this.caloriesBurned = caloriesBurned;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}

	public void setCaloriesConsumed(int caloriesConsumed) {
		this.caloriesConsumed = caloriesConsumed;
	}

	public int getCaloriesBurned() {
		return caloriesBurned;
	}

	public void setCaloriesBurned(int caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}

	@Override
	public String toString() {
		return "DateAndCalories [date=" + date + ", caloriesConsumed=" + caloriesConsumed + ", caloriesBurned="
				+ caloriesBurned + "]";
	}
	
}
