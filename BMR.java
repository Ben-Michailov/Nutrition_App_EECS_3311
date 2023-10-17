
public class BMR {
	// Initialize Fields
	private double weight, height;
	

	private int age;
	
	private char gender;
	
	public BMR (double w, double h, int a, char g) {
		weight = w;
		height = h;
		age = a;
		gender = g;
	}
	
	public double calculateBMR()

	{
		double rv = -1;
		
		
		if (gender == 'b' || gender == 'B' ) {
			rv = 66 + (6.3 * weight) + (12.9 * height) - (6.8 * age);
		}
		if (gender == 'f' || gender == 'F' ) {
			rv = 655 + 4.3 * weight + 4.7 * height - 6.8 * age;
		}
		
		
		
		return rv;
	}
	
	// getters and setters
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "BMR [weight=" + weight + ", height=" + height + ", age=" + age + ", gender=" + gender + "]";
	}


	
	
}
