import javax.swing.*;
import java.util.Scanner;
public class loop
//Yusdel Lima
//11/11/2015
	{
		public static void main(String[]m)	
		{
			int k = 1;
				Scanner i = new Scanner(System.in);
				System.out.print("Enter one of the sides: ");
				int a = i.nextInt();
			while(k < 11)
			{
				double result = PythagMethod.Pythag(7,k);
				System.out.println(a+"\t"+k+"\t"+result);
				k++;
			
			}
			
		}
	}