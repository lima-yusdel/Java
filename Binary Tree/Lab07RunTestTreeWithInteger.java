package lab07treestesterf16;
public class Lab07RunTestTreeWithInteger
{
    public static void main(String[] args)
    {
        runMe();
    }

    public static void runMe()
    {
        Lab07TestTreeGeneric<Integer> testTreeInteger = new Lab07TestTreeGeneric<Integer>();

        // array for Lab 8 and Test Condition 1 for Lab 9
        Integer[] lab08ArrayOfIntsToAdd = {81, 605, 71, 57, 302, 605, 201, 923};

        // To Test remove() method - condition 1
        // - cursor is null - target not found
        // meaning:  attempt to remove value not in tree
        Integer testConditionOneSingleIntToDelete = 66 ;

        // array for Lab 9 Test Condition  2
        Integer[] lab09ArrayOfIntsToAddForCondition2 = { 15, 37, 23, 78 };

        // To Test remove() method - condition 2
        // node to be removed is at root and no left child
        Integer testConditionTwoSingleIntToDelete = 15 ;

        // array for Lab 9 Test Condition 3
        Integer[] lab09ArrayOfIntsToAddForCondition3 = { 38, 11, 62, 5, 21, 7 };

        // To Test remove() method - condition 3
        // node removed is not at root and has no left child
        Integer testConditionThreeSingleIntToDelete = 5 ;

        // array for Lab 9 Test Condition 4
        Integer[] lab09ArrayOfIntsToAddForCondition4 = { 38, 11, 62, 5, 21, 7 };

        // To Test remove() method - condition 4
        // node to be removed has a left child
        // - will go left and get largest on left and bring up
        Integer testConditionFourSingleIntToDelete = 38 ; // coincidentally root

        testTreeInteger.test ( lab08ArrayOfIntsToAdd,
                                   testConditionOneSingleIntToDelete,
                                   "Test Condition 1");

        testTreeInteger.test ( lab09ArrayOfIntsToAddForCondition2,
                                   testConditionTwoSingleIntToDelete,
                                   "Test Condition 2");

        testTreeInteger.test ( lab09ArrayOfIntsToAddForCondition3,
                                   testConditionThreeSingleIntToDelete,
                                   "Test Condition 3");

        testTreeInteger.test ( lab09ArrayOfIntsToAddForCondition4,
                                   testConditionFourSingleIntToDelete,
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
Adding: 81 	added...now size is 1
Adding: 605 	added...now size is 2
Adding: 71 	added...now size is 3
Adding: 57 	added...now size is 4
Adding: 302 	added...now size is 5
Adding: 605 	added...now size is 6
Adding: 201 	added...now size is 7
Adding: 923 	added...now size is 8
Stopped adding>>
===========


Display Tree/Size after adds
57
71
81
201
302
605
605
923
Size: 8

66 is stated as not in the Tree - remove failed...double check output

Display Tree after trying to remove: 66
57
71
81
201
302
605
605
923
Size: 8
===========================================================
				Test Condition 2

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: 15 	added...now size is 1
Adding: 37 	added...now size is 2
Adding: 23 	added...now size is 3
Adding: 78 	added...now size is 4
Stopped adding>>
===========


Display Tree/Size after adds
15
23
37
78
Size: 4

15 stated as removed from Tree...double check output

Display Tree after trying to remove: 15
23
37
78
Size: 3
===========================================================
				Test Condition 3

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: 38 	added...now size is 1
Adding: 11 	added...now size is 2
Adding: 62 	added...now size is 3
Adding: 5 	added...now size is 4
Adding: 21 	added...now size is 5
Adding: 7 	added...now size is 6
Stopped adding>>
===========


Display Tree/Size after adds
5
7
11
21
38
62
Size: 6

5 stated as removed from Tree...double check output

Display Tree after trying to remove: 5
7
11
21
38
62
Size: 5
===========================================================
				Test Condition 4

Display Tree/Size on startup

Tree is empty...Tree says: 
empty

===========
<<Start adds:
Adding: 38 	added...now size is 1
Adding: 11 	added...now size is 2
Adding: 62 	added...now size is 3
Adding: 5 	added...now size is 4
Adding: 21 	added...now size is 5
Adding: 7 	added...now size is 6
Stopped adding>>
===========


Display Tree/Size after adds
5
7
11
21
38
62
Size: 6

38 stated as removed from Tree...double check output

Display Tree after trying to remove: 38
5
7
11
21
62
Size: 5

*** Scroll up to see test conditions 1,2,3,4 ****
BUILD SUCCESSFUL (total time: 0 seconds)

*/