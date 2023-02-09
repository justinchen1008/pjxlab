import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set everything but blue to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate everything */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  /** Method to turn everything into shades of gray */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int sum = 0; 
        sum += pixelObj.getRed();
        sum += pixelObj.getGreen();
        sum += pixelObj.getBlue();
        sum /= 3;
        pixelObj.setRed(sum);
        pixelObj.setGreen(sum);
        pixelObj.setBlue(sum);
      
      } 
    }
  }
  
   /** To pixelate by dividing area into size x size.
	* @param size Side length of square area to pixelate.
	*/
  public void pixelate(int size) {
	int bluesum = 0;
	int redsum = 0;
	int greensum = 0;
	int num = 0;
	Pixel[][] pixels = this.getPixels2D();  
	for(int row = 0; row < pixels.length; row += size){
		for(int col = 0; col < pixels[row].length; col += size){
			bluesum = 0;
			redsum = 0;
			greensum = 0;
			num = 0;
			for(int i = row; i < row + size; i++){
				for(int j = col; j < col + size; j++){
					if(i >= pixels.length || j >= pixels[i].length){
						continue;
					}
					num++;
					bluesum += pixels[i][j].getBlue();
					redsum += pixels[i][j].getRed();
					greensum += pixels[i][j].getGreen();
				}
			}
			bluesum /= num;
			greensum /= num;
			redsum /= num;
			for(int i = row; i < row + size; i++){
				for(int j = col; j < col + size; j++){
					if(i >= pixels.length || j >= pixels[i].length){
						continue;
					}
					pixels[i][j].setBlue(bluesum);
					pixels[i][j].setRed(redsum);
					pixels[i][j].setGreen(greensum);
				}
			}
		}
	}
  }	
  
  /** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
	*/
  public Picture blur(int size)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	int bluesum = 0;
	int redsum = 0;
	int greensum = 0;
	int num = 0;
	for(int row = 0; row < pixels.length; row++){
		for(int col = 0; col < pixels[row].length; col++){
			bluesum = 0;
			redsum = 0;
			greensum = 0;
			num = 0;
			for(int i = row - (size/2); i <= row + (size/2); i++){
				for(int j = col - (size / 2); j <= col + (size / 2); j++){
					if(i >= pixels.length || j >= pixels[0].length || i < 0 || j < 0){
						continue;
					}
					num++;
					bluesum += pixels[i][j].getBlue();
					redsum += pixels[i][j].getRed();
					greensum += pixels[i][j].getGreen();
				}
			}
			bluesum /= num;
			greensum /= num;
			redsum /= num;
			resultPixels[row][col].setBlue(bluesum);
			resultPixels[row][col].setRed(redsum);
			resultPixels[row][col].setGreen(greensum);
		}
	}
	return result;
  }	
  
  /** Method that enhances a picture by getting average Color around
   * a pixel then applies the following formula:
   *
   * pixelColor <- 2 * currentValue - averageValue
   *
   * size is the area to sample for blur.
   *
   * @param size Larger means more area to average around pixel
   * 		and longer compute time.
   * @return enhanced picture
   */
  public Picture enhance(int size)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	int bluesum = 0;
	int redsum = 0;
	int greensum = 0;
	int num = 0;
	for(int row = 0; row < pixels.length; row++){
		for(int col = 0; col < pixels[row].length; col++){
			bluesum = 0;
			redsum = 0;
			greensum = 0;
			num = 0;
			for(int i = row - (size/2); i <= row + (size/2); i++){
				for(int j = col - (size / 2); j <= col + (size / 2); j++){
					if(i >= pixels.length || j >= pixels[0].length || i < 0 || j < 0){
						continue;
					}
					num++;
					bluesum += pixels[i][j].getBlue();
					redsum += pixels[i][j].getRed();
					greensum += pixels[i][j].getGreen();
				}
			}
			bluesum /= num;
			greensum /= num;
			redsum /= num;
			resultPixels[row][col].setBlue((2*pixels[row][col].getBlue()) - bluesum);
			resultPixels[row][col].setRed((2*pixels[row][col].getRed()) - redsum);
			resultPixels[row][col].setGreen((2*pixels[row][col].getGreen()) - greensum);
		}
	}
	return result;
  }
  
  public Picture swapLeftRight(){
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		for(int i = 0; i < pixels.length; i++){
			for(int j = 0; j < pixels[i].length; j++){
				int newColumn = (j + pixels[i].length / 2) % pixels[i].length;
				resultPixels[i][newColumn].setBlue(pixels[i][j].getBlue());
				resultPixels[i][newColumn].setRed(pixels[i][j].getRed());
				resultPixels[i][newColumn].setGreen(pixels[i][j].getGreen());
			}
		}
		return result;	
  }
  
  /** Pixels are translated into a stair format from the original picture
	* @param shiftCount The number of pixels to shift to the right
	* @param steps The number of steps
	* @return The picture with pixels shifted in stair steps
	*/
 public Picture stairStep(int shiftCount, int steps){
	 Pixel[][] pixels = this.getPixels2D();
	 Picture result = new Picture(pixels.length, pixels[0].length);
	 Pixel[][] resultPixels = result.getPixels2D();
	 for(int i = 0; i < pixels.length; i++){
		 //the k only shifts the first ones
		 //for(int k = 0; k < shift; k++){
			 for(int j = 0; j < pixels[i].length; j++){
				int newColumn = (j + shiftCount * (i / (pixels.length / steps + 1))) % pixels[i].length;
				resultPixels[i][newColumn].setBlue(pixels[i][j].getBlue());
				resultPixels[i][newColumn].setRed(pixels[i][j].getRed());
				resultPixels[i][newColumn].setGreen(pixels[i][j].getGreen());
			}
		 //}
	 }
	 return result;
 }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
