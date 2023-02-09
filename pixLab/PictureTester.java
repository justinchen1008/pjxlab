/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test zeroBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayscale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test pixelate 
   * @param size    the size of each grid
   */
  public static void testPixelate(int size)
  {
    Picture beach = new Picture("images/water.jpg");
    beach.explore();
    beach.pixelate(size);
    beach.explore();
  }
  
  /** Method to test blur 
   * @param size	the number of pixels surrounding it to blur it with 
   */
  public static void testBlur(int size)
  {
    Picture beach = new Picture("images/water.jpg");
    beach.explore();
    beach = beach.blur(size);
    beach.explore();
  }
  
  /** Method to test the left and right half swap */
  public static void testSwapLeftRight()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.swapLeftRight();
    beach.explore();
  }
  
  /** Method to test the the stair swap */
  public static void testStairStep(int shiftCount, int steps)
  {
    Picture beach = new Picture("images/gorge.jpg");
    beach.explore();
    beach = beach.stairStep(shiftCount, steps);
    beach.explore();
  }
  
  /** Method to test enhance 
   * @param size	the number of pixels surrounding it to blur it with 
   */
  public static void testEnhance(int size)
  {
    Picture beach = new Picture("images/gorge.jpg");
    beach.explore();
    beach = beach.enhance(size);
    beach.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
	//tested fully (changed size param greater and less,
	//				changed pic to different pics)
    //testPixelate(11);
    //tested fully (changed size param greater and less, 
    //				changed pic to different pics)
    //testBlur(11);
    //tested fully (changed size param greater and less, 
    //				changed pic to different pics)
    //tested fully (changed and tried with 3 different pictures)
    //testSwapLeftRight();
    testStairStep(11, 11);
    //testEnhance(11);
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
