package nutrifit.patterns;


/**
 * builder Class
 *
 */
public class UserBuilder implements Builder{
	 private User user;

	 public UserBuilder() {
		 this.user = new User();
	 }

	@Override
	public void setName(String name) {
		this.user.setUserName(name);
		
	}

	@Override
	public void setAge(int age) {
		this.user.setUserAge(age);
		
	}
	
	public void setAge(String age) {
		this.user.setUserAge(Integer.parseInt(age));
		
	}

	@Override
	public void setDOB(String dob) {
		this.user.setUserDOB(dob);
		
	}

	@Override
	public void setHeight(double height) {
		this.user.setUserHeight(height);
		
	}

	@Override
	public void setWeight(double weight) {
		this.user.setUserWeight(weight);
		
	}
	public void setHeight(String height) {
		this.user.setUserHeight(Double. parseDouble(height));
		
	}

	
	public void setWeight(String weight) {
		this.user.setUserWeight(Double. parseDouble(weight));
		
	}

	@Override
	public void setSex(String sex) {
		this.user.setUserSex(sex);
		
	}
	public User build() {
		
		return this.user;
		
	}
	
	
}
