package travel;

import java.util.Random;

public class ComparableSample
{
	public static void main(String args[])
	{
		Table obj = new Table();// only one object
		MyThread1 t1 = new MyThread1(new Table());
		MyThread2 t2 = new MyThread2(new Table());
		t1.start();
		t2.start();

	}
	 void printTableR(int n)
		{// method not synchronized
			// synchronized (this.getClass())
			{
				for (int i = 1; i <= 5; i++)
				{
					System.out.println(n * i);
					try
					{
						Thread.sleep(400);
					} catch (Exception e)
					{
						System.out.println(e);
					}
				}
			}
		}
}

class Table
{

	synchronized static void printTable(int n)
	{// method not synchronized
		// synchronized (this.getClass())
		{
			for (int i = 1; i <= 5; i++)
			{
				System.out.println(n * i);
				try
				{
					Thread.sleep(400);
				} catch (Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}
}

class MyThread1 extends Thread
{
	Table t;

	MyThread1(Table t)
	{
		this.t = t;
	}

	public void run()
	{
		//t.printTable(5);
		 Table.printTable(5);
		
	}

}

class MyThread2 extends Thread
{
	Table t;

	MyThread2(Table t)
	{
		this.t = t;
	}

	public void run()
	{
		//t.printTable(100);
		 Table.printTable(100);
	}
}
