package travel;

public class ComparableSample
{
	private static void main(String args[])
	{

	}
}

class Smaple implements Comparable
{
	int rollno;
	String name;
	int age;

	Smaple(int rollno, String name, int age)
	{
		this.rollno = rollno;
		this.name = name;
		this.age = age;
	}

	public int compareTo(Object obj)
	{
		return 1;
	}
}