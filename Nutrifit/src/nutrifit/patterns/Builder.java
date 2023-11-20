package nutrifit.patterns;


/**
 * Builder interface
 *
 */

public interface Builder {
	public void setName(String name);
	public void setAge(int age);
	public void setDOB(String dob);
	public void setHeight(double height);
	public void setWeight(double weight);
	public void setSex(String sex);
}
