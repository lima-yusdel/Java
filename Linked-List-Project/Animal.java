package p2;
/**
 *
 * @author Yusdel Lima Lorenzo
 */
public class Animal 
{
    private String animalType;
    private double numLegs;
    private String animalColor;
    private String animalRegion;
   
    /**
     *Constructor when you issue a new animal this is called
     * @param newAnimalType the type of animal such as bird, fish
     * @param newNumLegs number of legs of the animal
     * @param newAnimalColor the color of the animal
     * @param newAnimalRegion where the animal is from
     */
    public Animal(String newAnimalType, double newNumLegs, String newAnimalColor, String newAnimalRegion)
    {
      setAnimalType(newAnimalType);
      setNumLegs(newNumLegs);
      setAnimalColor(newAnimalColor);
      setAnimalRegion(newAnimalRegion);
    }
    
    /**
     *
     * @param type
     */
    public void setAnimalType(String type)
    {
        animalType= type;
    }
    
    /**
     *
     * @param legs
     */
    public void setNumLegs(double legs)
    {
        numLegs = legs;
    }
    
    /**
     *
     * @param color
     */
    public void setAnimalColor(String color)
    {
        animalColor = color;
    }
    
    /**
     *
     * @param region
     */
    public void setAnimalRegion(String region)
    {
        animalRegion = region;
    }
    
    /**
     *
     * the animal type
     * @return 
     */
    public String getAnimalType()            
    {
        return animalType;
    }
   
    /**
     *
     * number of legs
     * @return 
     */
    public double getNumLegs()
    {
        return numLegs;
    }
    
    /**
     *
     * the color of the animal
     * @return 
     */
    public String getAnimalColor()
    {
        return animalColor;
    }
    
    /**
     *
     *the region
     * @return 
     */
    public String getAnimalRegion()
    {
        return animalRegion;
    }
    
       public boolean equals(Object otherObject) throws ClassCastException
    {
        boolean isSame;
           if ( !(otherObject instanceof Animal))
            throw new ClassCastException("Error invalid animal");     
        Animal otherAnimal = (Animal) otherObject;

        if (animalType.equalsIgnoreCase(otherAnimal.getAnimalType())&&
            numLegs == otherAnimal.getNumLegs() &&
            animalColor.equalsIgnoreCase(otherAnimal.getAnimalColor()) &&
            animalRegion.equalsIgnoreCase(otherAnimal.getAnimalRegion()))
            isSame = true;
             else
            isSame = false;
        return isSame;
    }
    public String toString()
    {
        return "\n\nUnique Animal\nAnimal Type: "+animalType+"\nAnimal Color: "+animalColor+"\nNumber of Legs: "+numLegs+"\nAnimal Region: "+animalRegion;
    }
}
    
    
