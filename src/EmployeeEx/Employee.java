package EmployeeEx;


public class Employee {
	private int id;
	 private String name;
	 private int salary;
	 private int age;
	 private  String department;

		public Employee( int id,String name,int age,  String department,int salary) {
			this.id=id;
			this.name=name;
			this.salary=salary;
			this.age=age;
			this.department=department;
			
		}
		public int getId()
		{
			return id;
		}
		public void setEmpId(int id) {
			this.id = id;
		}
		public String getName()
		{
			return name;
		}
		public void setEmpName(String name) {
			this.name = name;
		}
		public int getAge()
		{
			return age;
		}
		public void setAge(int age)
		{
			this.age=age;
		}
		public int getSalary()
		{
			return salary;
		}
		public void setSalary(int salary)
		{
			this.salary=salary;
		}
		public String getDept()
		{
			return department;
		}
		public void setDept(String department)
		{
			this.department=department;
		}
		public void add(int id, String name, int age, String department, int salary) {
			this.id=id;
			this.name=name;
			this.salary=salary;
			this.age=age;
			this.department=department;
			Employee qw=new Employee(this.id,this.name,this.age,this.department,this.salary);
		}
	
	
}
