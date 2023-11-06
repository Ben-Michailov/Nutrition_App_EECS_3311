package nutrifit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private int age;
    private String DOB;
    
    private double height;
    private double weight;
    private String sex;

    public User() {
        this.name = null;
        this.age = 0;
        this.DOB = null;
        this.height = 0.0;
        this.weight = 0.0;
        this.sex = "";
    }
    public User(String name, String age, String DOB, String height, String weight, String sex) throws ParseException {
        this.name = name;
        this.age = Integer.parseInt(age);
        this.DOB = DOB;
        this.height = Double. parseDouble(height);
        this.weight = Double. parseDouble(weight);
        this.sex = sex;
    }
    public User(String name, int age, String DOB, double height, double weight, String sex) {
        this.name = name;
        this.age = age;
        this.DOB = DOB;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
    }

    public User(User user) {
        this.name = user.getName();
        this.age = user.getAge();
        this.DOB = user.getDOB();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.sex = user.getSex();
    }
   /* public User(String[] sl) throws ParseException {
        this.name = sl[0];
        this.age = Integer.parseInt(sl[1]);
        this.DOB = sl[2];
        this.height = Double.parseDouble(sl[3]);
        this.weight = Double.parseDouble(sl[4]);
        this.sex = sl[5];
    }
    */
    // Mifflin-St Jeor Equation: 
    public double BMRcalculator() {
    	
    	if(this.sex.equals("M")) {
    		return 10*this.weight + 6.25*this.height - (double)5*this.age + 5;
    	}else {
    		return 10*this.weight + 6.25*this.height - (double)5*this.age -161;
    	}
    }

    @Override
    public String toString() {
        return name + ",\n" + age + ",\n" + DOB + ",\n" + height + ",\n" + weight + ",\n" + sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String date) {
        this.DOB = date;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
