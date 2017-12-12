import java.util.Scanner;
import javax.swing.JOptionPane;
public class Menu 
//Yusdel Lima
//10/19/2015
	{
		public static void main(String[]m)	
		{
		int option;
	    Scanner in = new Scanner(System.in);	 
	    JOptionPane.showMessageDialog(null,"\n 1. The area of a circle.\n 2. The circumference of a circle.\n 3. The volume of a cube.");
	    JOptionPane.showMessageDialog(null,"\n Enter option: ");
	    option = in.nextInt();
			 
			 if (option == 1)
			 {
				 double radius;
				 JOptionPane.showMessageDialog(null,"\n What is the radius? " );
				 radius = in.nextDouble();
				 JOptionPane.showMessageDialog(null, "\n The area is "+Math.PI*Math.pow(radius,2)+".");
			 }
			 else
			  
				if (option == 2)
				{
					double radius;
					JOptionPane.showMessageDialog(null,"What is the radius? ");
					radius = in.nextDouble();
					JOptionPane.showMessageDialog(null,"The circumference is "+2*Math.PI*radius);
				}
				else
				
					if (option == 3)
					{	
						double side;
						JOptionPane.showMessageDialog(null,"\n Enter a side: ");
						side = in.nextDouble();
						JOptionPane.showMessageDialog(null,"\n The volume is: "+Math.pow(side,3));
					}
					else
					
						JOptionPane.showMessageDialog(null,"\n invalid option.");
					
						JOptionPane.showMessageDialog(null,"\n Goodbye."); 
				}
		}