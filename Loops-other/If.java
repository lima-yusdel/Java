import java.util.Scanner;
public class If
{//Yusdel Lima
 //10/12/2015

			public static void main(String []m)
		
		{
				int age;
			
				Scanner i = new Scanner(System.in);
		
				System.out.print("Enter your age: ");
						
				age = i.nextInt();
				
					if (age>18) 		
					
					System.out.print("You may vote!");
					
					else
					
						if (age<18)
						
							System.out.print("You may not vote.");
						
						else 
						
							System.out.print("Register yourself.");
						
					
						
							System.out.print(" Goodbye.");
					
		}
}	