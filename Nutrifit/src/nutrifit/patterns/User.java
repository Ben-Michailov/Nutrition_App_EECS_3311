package nutrifit.patterns;
/**
         * User Class
         *
         */
public class User{
	

	private  String name;
	private  int age;
	private  String DOB;
	private  double height;
	private  double weight;
	private  String sex;
	
	
	@Override
    public String toString() {
        return name + ",\n" + age + ",\n" + DOB + ",\n" + height + ",\n" + weight + ",\n" + sex;
    }
	
    public double BMRcalculator() {
    	
    	if(this.sex.equals("M")) {
    		return 10*this.weight + 6.25*this.height - (double)5*this.age + 5;
    	}else {
    		return 10*this.weight + 6.25*this.height - (double)5*this.age -161;
    	}
    }
    
    
	public void setUserName(String name) {
		this.name = name;
		
	}
	public void setUserAge(int age) {
		this.age = age;
		
	}
	
	public void setUserDOB(String dob) {
		this.DOB = dob;
		
	}
	
	public void setUserHeight(double height) {
		this.height = height;
		
	}
	
	public void setUserWeight(double weight) {
		this.weight = weight;
		
	}
	
	public void setUserSex(String sex) {
		this.sex = sex;
		
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getDOB() {
		return DOB;
	}
	public double getHeight() {
		return height;
	}
	public double getWeight() {
		return weight;
	}
	public String getSex() {
		return sex;
	}

}
