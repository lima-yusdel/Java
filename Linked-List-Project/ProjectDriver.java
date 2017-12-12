package p2;

import java.util.Scanner;

/**
 *
 * @author Yusdel Lima Lorenzo
 */
public class ProjectDriver {

    public static void main(String[] args) {
        String animalType = null;
        double numLegs = 0;
        String animalColor = null;
        String animalRegion = null;
        String command;
        int numElements =0;
        Scanner f = new Scanner(System.in);
        LinkedBag<Animal> myAnimalBag = new LinkedBag<>();
        {
            do {
                System.out.println("\n\nMenu\n"+"A - Add 'Animal' to bag"+"\n"+"R - Remove 'Animal' from bag"+"\n"+"F - Finds 'Animal' in bag"+"\n"+"C - Count 'Animal' in bag"+"\n"+"D - Display Contents of Bag"+"\n"+"S - Displays size of bag"+"\n"+"G - Grab"+"\n"+"X - Exit");
                System.out.print("Enter Selection: ");
                command = f.nextLine();

                if (command.equalsIgnoreCase("a")) {
                    System.out.print("\nEnter type of animal: ");
                    animalType = f.nextLine();

                    System.out.print("Enter number of legs: ");
                    numLegs = f.nextDouble();
                    f.nextLine();
                    //eats up the buffer

                    System.out.print("Enter color of the Animal: ");
                    animalColor = f.nextLine();

                    System.out.print("Enter where the animal is found: ");
                    animalRegion = f.nextLine();

                    myAnimalBag.add(new Animal(animalType, numLegs, animalColor, animalRegion));

                } else if (command.equalsIgnoreCase("r"))
                {
                    System.out.print("\nEnter type of animal to remove: ");
                    animalType = f.nextLine();

                    System.out.print("Enter number of legs: ");
                    numLegs = f.nextDouble();
                    f.nextLine();
                    //eats up the buffer

                    System.out.print("Enter color of the Animal to remove: ");
                    animalColor = f.nextLine();

                    System.out.print("Enter where the animal is found: ");
                    animalRegion = f.nextLine();
                    
                  boolean result = myAnimalBag.remove(new Animal(animalType, numLegs, animalColor, animalRegion));
                    if (result == false)
                        System.out.print("\nOops animal not removed.\n");
                    else
                        System.out.println("\nAnimal removed.\n");
                }
                 else if (command.equalsIgnoreCase("f")) {
                    System.out.print("\nEnter type of animal: ");
                    animalType = f.nextLine();
                    System.out.print("Enter number of legs: ");
                    numLegs = f.nextDouble();
                    f.nextLine();
                    //eats up the buffer
                    System.out.print("Enter color of the Animal: ");
                    animalColor = f.nextLine();

                    System.out.print("Enter where the animal is found: ");
                    animalRegion = f.nextLine();
                    
                   
                    boolean result = myAnimalBag.exists(new Animal(animalType, numLegs, animalColor, animalRegion));
                    if (result == false)
                        System.out.print("\nOops animal not found.\n");
                    else
                        System.out.println("\nAnimal found.\n");
                    
                } else if (command.equalsIgnoreCase("C")) {
                    System.out.print("\nEnter type of animal: ");
                    animalType = f.nextLine();

                    System.out.print("Enter number of legs: ");
                    numLegs = f.nextDouble();
                    f.nextLine();
                    //eats up the buffer

                    System.out.print("Enter color of the Animal: ");
                    animalColor = f.nextLine();

                    System.out.print("Enter where the animal is found: ");
                    animalRegion = f.nextLine();

                    System.out.print("\n\nThere are "+myAnimalBag.countOccurrences(new Animal(animalType, numLegs, animalColor, animalRegion)));
                    
                } else if (command.equalsIgnoreCase("D")) {                
                    System.out.print(myAnimalBag.toString());
                } else if (command.equalsIgnoreCase("S")) {
                    System.out.println("\nSize = "+myAnimalBag.getSize());
                } else if (command.equalsIgnoreCase("G")) {
                    System.out.print("Randomly removed: "+myAnimalBag.grab());
                } else if (command.equalsIgnoreCase("X")) {
                    System.out.println("Exiting");
                } else {
                    System.out.println("Invalid command, please enter either A, R, F, C, D, S,  or X");
                }
            } while (!(command.equalsIgnoreCase("X")));

        }
    }
}
