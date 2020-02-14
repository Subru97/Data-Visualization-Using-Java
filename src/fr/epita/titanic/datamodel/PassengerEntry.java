package fr.epita.titanic.datamodel;

public class PassengerEntry {
	private Integer survived;
	private Integer pclass;
	private String name;
	private String sex;
	private Double age;
	private Integer siblingsOrSpouse;
	private Integer parentsOrChildrens;
	private Double fare;
	
	public Integer getSurvived() {
		return survived;
	}

	public void setSurvived(Integer survived) {
		this.survived = survived;
	}

	public Integer getPclass() {
		return pclass;
	}

	public void setPclass(Integer pclass) {
		this.pclass = pclass;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}
	
	public Integer getSiblingsOrSpouse() {
		return siblingsOrSpouse;
	}

	public void setSiblingsOrSpouse(Integer siblingsOrSpouse) {
		this.siblingsOrSpouse = siblingsOrSpouse;
	}

	public Integer getParentsOrChildrens() {
		return parentsOrChildrens;
	}

	public void setParentsOrChildrens(Integer parentsOrChildrens) {
		this.parentsOrChildrens = parentsOrChildrens;
	}
	
	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

}