package lab07treestesterf16;
public class Lab07RunTestTreeWithCar
{
    public static void main(String[] args)
    {
        runMe();
    }

    public static void runMe()
    {
        Lab07TestTreeGeneric<Car> testTreeCar = new Lab07TestTreeGeneric<Car>();

        Car[] lab08ArrayOfCarsToAdd = {
                    new Car(2015,"Honda Accord"),
                    new Car(1998,"Camry"),
                    new Car(1994,"Explorer"),
                    new Car(2015,"Zoom Zoom"),
                    new Car(2015,"Acura"),
                    new Car(2007,"Neon"),
                    new Car(1965,"Shelby Mustang GT 350"),
                    new Car(2015,"zOom zoOM")};

        // To Test remove() method - condition 1
        // - cursor is null - target not found
        // meaning:  attempt to remove value not in tree
        // Note: since the .equals() in Car ignores case for the
        //       make, then case doesn't matter so my wAckY cAr caSe
        Car testConditionOneSingleCarToDelete = new Car(1994,"Expedition");

        // array for Lab 9 Test Condition  2
        Car[] lab09ArrayOfCarsToAddForCondition2 = { new Car(2013,"Dodge"),
                    new Car(2013,"Explorer"), new Car(2013,"Malibu"),
                    new Car(2013,"Edsel") };

        // To Test remove() method - condition 2
        // node to be removed is at root and no left child
        Car testConditionTwoSingleCarToDelete = new Car(2013,"Dodge") ;

        // array for Lab 9 Test Condition 3
        Car[] lab09ArrayOfCarsToAddForCondition3 = { new Car(2013,"Explorer"),
                new Car(2013,"Dodge"), new Car(2013,"Malibu"),
                new Car(2013,"Aero"), new Car(2013,"Edsel"),
                new Car(2013,"Avalon") };

        // To Test remove() method - condition 3
        // node removed is not at root and has no left child
        Car testConditionThreeSingleCarToDelete = new Car(2013,"Aero");

        // array for Lab 9 Test Condition 4
        Car[] lab09ArrayOfCarsToAddForCondition4 = { new Car(2013,"Explorer"),
                new Car(2013,"Dodge"), new Car(2013,"Malibu"),
                new Car(2013,"Aero"), new Car(2013,"Edsel"),
                new Car(2013,"Avalon") };

        // To Test remove() method - condition 4
        // node to be removed has a left child
        // - will go left and get largest on left and bring up
        Car testConditionFourSingleCarToDelete = new Car(2013,"Explorer"); // coincidentally root


        testTreeCar.test ( lab08ArrayOfCarsToAdd,
                                   testConditionOneSingleCarToDelete,
                                   "Test Condition 1");

        testTreeCar.test ( lab09ArrayOfCarsToAddForCondition2,
                                   testConditionTwoSingleCarToDelete,
                                   "Test Condition 2");

        testTreeCar.test ( lab09ArrayOfCarsToAddForCondition3,
                                   testConditionThreeSingleCarToDelete,
                                   "Test Condition 3");

        testTreeCar.test ( lab09ArrayOfCarsToAddForCondition4,
                                   testConditionFourSingleCarToDelete,
                                   "Test Condition 4");
        System.out.println("\n*** Scroll up to see test conditions 1,2,3,4 ****");

    }
}

/*

*** LAB 07 Output is:

run:
===========================================================
				Test Condition 1

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: [Car 2015 Honda Accord] 	added...now size is 1
Adding: [Car 1998 Camry] 	added...now size is 2
Adding: [Car 1994 Explorer] 	added...now size is 3
Adding: [Car 2015 Zoom Zoom] 	added...now size is 4
Adding: [Car 2015 Acura] 	added...now size is 5
Adding: [Car 2007 Neon] 	added...now size is 6
Adding: [Car 1965 Shelby Mustang GT 350] 	added...now size is 7
Adding: [Car 2015 zOom zoOM] 	added...now size is 8
Stopped adding>>
===========


Display Tree/Size after adds
[Car 1965 Shelby Mustang GT 350]
[Car 1994 Explorer]
[Car 1998 Camry]
[Car 2007 Neon]
[Car 2015 Acura]
[Car 2015 Honda Accord]
[Car 2015 Zoom Zoom]
[Car 2015 zOom zoOM]
Size: 8

[Car 1994 Expedition] is stated as not in the Tree - remove failed...double check output

Display Tree after trying to remove: [Car 1994 Expedition]
[Car 1965 Shelby Mustang GT 350]
[Car 1994 Explorer]
[Car 1998 Camry]
[Car 2007 Neon]
[Car 2015 Acura]
[Car 2015 Honda Accord]
[Car 2015 Zoom Zoom]
[Car 2015 zOom zoOM]
Size: 8
===========================================================
				Test Condition 2

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: [Car 2013 Dodge] 	added...now size is 1
Adding: [Car 2013 Explorer] 	added...now size is 2
Adding: [Car 2013 Malibu] 	added...now size is 3
Adding: [Car 2013 Edsel] 	added...now size is 4
Stopped adding>>
===========


Display Tree/Size after adds
[Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Explorer]
[Car 2013 Malibu]
Size: 4

[Car 2013 Dodge] stated as removed from Tree...double check output

Display Tree after trying to remove: [Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Explorer]
[Car 2013 Malibu]
Size: 3
===========================================================
				Test Condition 3

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: [Car 2013 Explorer] 	added...now size is 1
Adding: [Car 2013 Dodge] 	added...now size is 2
Adding: [Car 2013 Malibu] 	added...now size is 3
Adding: [Car 2013 Aero] 	added...now size is 4
Adding: [Car 2013 Edsel] 	added...now size is 5
Adding: [Car 2013 Avalon] 	added...now size is 6
Stopped adding>>
===========


Display Tree/Size after adds
[Car 2013 Aero]
[Car 2013 Avalon]
[Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Explorer]
[Car 2013 Malibu]
Size: 6

[Car 2013 Aero] stated as removed from Tree...double check output

Display Tree after trying to remove: [Car 2013 Aero]
[Car 2013 Avalon]
[Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Explorer]
[Car 2013 Malibu]
Size: 5
===========================================================
				Test Condition 4

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: [Car 2013 Explorer] 	added...now size is 1
Adding: [Car 2013 Dodge] 	added...now size is 2
Adding: [Car 2013 Malibu] 	added...now size is 3
Adding: [Car 2013 Aero] 	added...now size is 4
Adding: [Car 2013 Edsel] 	added...now size is 5
Adding: [Car 2013 Avalon] 	added...now size is 6
Stopped adding>>
===========


Display Tree/Size after adds
[Car 2013 Aero]
[Car 2013 Avalon]
[Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Explorer]
[Car 2013 Malibu]
Size: 6

[Car 2013 Explorer] stated as removed from Tree...double check output

Display Tree after trying to remove: [Car 2013 Explorer]
[Car 2013 Aero]
[Car 2013 Avalon]
[Car 2013 Dodge]
[Car 2013 Edsel]
[Car 2013 Malibu]
Size: 5

*** Scroll up to see test conditions 1,2,3,4 ****
BUILD SUCCESSFUL (total time: 0 seconds)


*/