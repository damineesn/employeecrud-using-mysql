package EmployeeEx;

public interface Interface{
	
	 public void statistics();
	 public void display();
	void importdetails(int id, String name, int age, String department, int salary);
	public void insert(String name, int age, String department, int salary);
	void displayemployee(int id);
	void updateX(int id, String name, int age, String department, int salary);
	void deleteX(int id);
}