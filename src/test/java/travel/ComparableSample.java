package travel;

import java.util.Random;

public class ComparableSample
{
	public static void main(String args[])
	{
		String str = "h.ello.one.mp4";
		Random r = new Random(System.currentTimeMillis());
		int tempRandomVal = 1000000 + r.nextInt(2000000);
		String.valueOf(tempRandomVal);
		System.out.println(String.valueOf(tempRandomVal));
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