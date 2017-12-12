import java.util.Scanner; import java.text.DecimalFormat;
public class Mixing
{//Yusdel Lima
 //9/30/2015
		public static void main(String []m)
		{
		String name;
		float salary, result;
		double percentage;
		int age;
		int temp;
		double temp2;
			
			Scanner in = new Scanner(System.in);
			
				System.out.print("Enter name: ");
				name = in.nextLine();
				
				System.out.print("Enter Salary: ");
				salary = in.nextFloat();
				
				System.out.print("Enter Percentage: ");
				percentage = in.nextDouble();
				
				System.out.print("Enter age: ");
				age = in.nextInt();
				
					//result=(60-age)(salary*percentage);
					temp=(65-age);
					temp2=(salary*percentage);
					result=(float)(temp*temp2);
					
					DecimalFormat x = new DecimalFormat("#,###.00");
					
				System.out.print(name+" you will have $"+x.format(result)+" when you retire.");
		}
}