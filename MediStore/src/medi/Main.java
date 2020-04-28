package medi;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.Random;

public class Main extends customer{
	public static void write(File file) throws IOException
	{
		File medicineq=new File("D:\\PROJECT\\MediStore\\src\\medi\\medicinequantity.txt");
		File medicinec=new File("D:\\PROJECT\\MediStore\\src\\medi\\medicinecost.txt");
		try
		{
			FileReader fr1=new FileReader(file);
			LineNumberReader lr1=new LineNumberReader(fr1);
			try
			{
				String stme=lr1.readLine();
				FileOutputStream fos = new FileOutputStream(medicineq);
				BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos));
				FileOutputStream fos2 = new FileOutputStream(medicinec);
				BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(fos2));
				while(stme!=null)
				{
					Random rand1=new Random();
					stme=lr1.readLine();
					if(stme!=null)
					{
						bw1.write(Integer.toString(rand1.nextInt(100)));
						bw1.newLine();
						bw2.write(Integer.toString(rand1.nextInt(25)+5));
						bw2.newLine();
					}
					else
					{
						bw1.write(Integer.toString(rand1.nextInt(100)));
						bw2.write(Integer.toString(rand1.nextInt(25)+5));
					}
				}
				bw2.close();
				bw1.close();
				lr1.close();
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	@SuppressWarnings("resource")
	public void customers()
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter coustomer namme");
	    name=s.next();
		System.out.println("Enter coustomer phone number:");
	    r=s.nextLong();
	}
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		File f = new File("D:\\PROJECT\\MediStore\\src\\medi\\medicines.txt");
		int c=0;
		if(f.exists())
		{
			try
			{
				FileReader fr=new FileReader(f);
				LineNumberReader lr=new LineNumberReader(fr);
				try
				{
					String strm3=lr.readLine();
					while(strm3!=null)
					{
						c++;
						strm3=lr.readLine();
					}
					//System.out.println(c);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		System.out.println("            WELCOME TO MEDICAL STORE          ");
		customer cust=new Main();
		cust.customers();
		String cart[]=new String[c];
		int cartitemcosts[]=new int[100];
		int nooftablets[]=new int[100];
		int tabletcost[]=new int[100];
		File f1=new File("D:\\PROJECT\\MediStore\\src\\medi\\bills.txt");
		int c1=0;
		if(f1.exists())
		{
			try
			{
				FileReader fri=new FileReader(f1);
				LineNumberReader lr=new LineNumberReader(fri);
				try
				{
					String strm2=lr.readLine();
					while(strm2!=null)
					{
						c1++;
						strm2=lr.readLine();
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				if(c1==0)
				{
					try
					{
						write(f);
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		}
		System.out.println("Buy Medicine");
		int cost=0;
		int h=0;
		while(true)
		{
			System.out.println("Enter the medicine u need");
			String tablet=sc.next();
			String z = "no";
			int n=0;
			int count1=0;
			try
			{
				File filemedicine=new File("D:\\PROJECT\\MediStore\\src\\medi\\medicines.txt");
				FileReader fr=new FileReader(filemedicine);
				LineNumberReader lr=new LineNumberReader(fr);
				try
				{
					String strm=lr.readLine();
					while(strm!=null)
					{
						count1++;
						if(strm.contains(tablet))
						{
							n=count1;
							z="yes";
							break;
						}
						else
						{
							strm=lr.readLine();
						}
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			File medicineq=new File("D:\\PROJECT\\MediStore\\src\\medi\\medicinequantity.txt");
			File medicinec=new File("D:\\PROJECT\\MediStore\\src\\medi\\medicinecost.txt");
			System.out.println(z);
			if(z.equals("yes"))
			{
				System.out.println("How many Tablets do you want to add to cart");
				int count=sc.nextInt();
				int count2=0;
				int medicinequantity=0;
				try
				{
					FileReader fr3=new FileReader(medicineq);
					LineNumberReader lr=new LineNumberReader(fr3);
					try
					{
						String stm1=lr.readLine();
						while(stm1!=null)
						{
							count2++;
							if(count2==n)
							{
								medicinequantity=Integer.parseInt(stm1);
								break;
							}
							stm1=lr.readLine();
						}
						lr.close();
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				boolean status=checkAvailability(medicinequantity,count);
				if(status==true)
				{
					int lnc=0;
					try
					{
						FileReader fr4=new FileReader(medicinec);
						LineNumberReader lrc=new LineNumberReader(fr4);
						try
						{
							String stmk=lrc.readLine();
							while(stmk!=null)
							{
								lnc++;
								if(lnc==n)
								{
									cart[h]=tablet;
									cost=cost+(count*Integer.parseInt(stmk));
									nooftablets[h]=count;
									tabletcost[h]=Integer.parseInt(stmk);
									cartitemcosts[h]=(count*Integer.parseInt(stmk));
									h=h+1;
								}
								stmk=lrc.readLine();
							}
							lrc.close();
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("Medicine is out of stock.\nplease come back later");
				}
			}
			else
			{
				System.out.println("Medicine is out of stock.\nPlease come back later");
				Path p=Paths.get("D:\\PROJECT\\MediStore\\src\\medi\\medicinecost.txt");
				Path p1=Paths.get("D:\\PROJECT\\MediStore\\src\\medi\\medicinequantity.txt");
				UpdateStock(tablet,p,p1);
			}
			System.out.println("Do you want to add other medicine:(choose 1/0)");
			int choice=sc.nextInt();
			if(choice==0)
			{
				break;
			}
		}
		if(cost==0)
		{
			System.out.println("Thanks for visiting");
		}
		else
		{
			Path p=Paths.get("D:\\PROJECT\\MediStore\\src\\medi\\bills.txt");
			String main="BILLING FOR YOUR MEDICINE";
			String name1="Name :"+name;
			String phno="Phone number :"+r;
			String totbill="The Bill is:"+cost;
			String discount="Discount:"+cost/10;
			String finalbill="The Final Bill:"+(cost-(cost/10));
			System.out.println(main);
			System.out.println(name1);
			System.out.println(phno);
			String dupmain=System.lineSeparator()+main;
			String dupname1=System.lineSeparator()+name1;
			String dupphno=System.lineSeparator()+phno;
			String duptotbill=System.lineSeparator()+totbill;
			String linesep=System.lineSeparator()+" ";
			String dupdiscount=System.lineSeparator()+discount;
			String dupfinalbill=System.lineSeparator()+finalbill;
			try {
			    Files.write(p, dupmain.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, dupname1.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, dupphno.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
			    System.err.println(e);
			}
			for(int k=0;k<h;k++)
			{
				String str2=cart[k]+"  "+nooftablets[k]+"x"+tabletcost[k]+" : "+cartitemcosts[k];
				String dupstr2=System.lineSeparator()+str2;
				try
				{
					Files.write(p, dupstr2.getBytes(), StandardOpenOption.APPEND);
				}
				catch(IOException e)
				{
					System.err.println(e);
				}
				System.out.println(str2);
			}

			System.out.println(totbill);
			System.out.println(discount);
			System.out.println(finalbill);
			try {

			    Files.write(p, duptotbill.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, dupdiscount.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, dupfinalbill.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, linesep.getBytes(), StandardOpenOption.APPEND);
			    Files.write(p, linesep.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
			    System.err.println(e);
			}
		}
	}
}