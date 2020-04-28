package medi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Availability {
		public static boolean checkAvailability(int medicinequantity,int count)
	{
		if(count<=medicinequantity)
		{
			return true;
		}
		return false;
	}
	public static void write(String tablet,Path p3) throws IOException
	{
		String s=System.lineSeparator()+tablet;
		try {
		    Files.write(p3, s.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
		    System.err.println(e);
		}
	}
	public static void UpdateStock(String tablet,Path p,Path p1)
	{
		Random rand=new Random();
		Path p2=Paths.get("D:\\PROJECT\\MediStore\\src\\medi\\medicines.txt");
		try
		{
			write(tablet,p2);
			write(Integer.toString(rand.nextInt(25)+5),p);
			write(Integer.toString(rand.nextInt(100)),p1);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
