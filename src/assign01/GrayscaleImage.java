package assign01;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;


/**
 * Represents a grayscale (black and white) image as a 2D array of "pixel" brightnesses
 * 255 is "white" 127 is "gray" 0 is "black" with intermediate values in between
 * Author: Ben Jones and Logan Luker
 */
public class GrayscaleImage {
    private double[][] imageData; // the actual image data [height][width]


    /**
     * Initialize an image from a 2D array of doubles
     * This constructor creates a copy of the input array
     * @param data initial pixel values
     * @throws IllegalArgumentException if the input array is empty or "jagged" meaning not all rows are the same length
     */
    public GrayscaleImage(double[][] data){
        if(data.length == 0 || data[0].length == 0){
            throw new IllegalArgumentException("Image is empty");
        }

        imageData = new double[data.length][data[0].length];
        for(var row = 0; row < imageData.length; row++){
            if(data[row].length != imageData[row].length){
                throw new IllegalArgumentException("All rows must have the same length");
            }
            for(var col = 0; col < imageData[row].length; col++){
                imageData[row][col] = data[row][col];
            }
        }
    }

    /**
     * Fetches an image from the specified URL and converts it to grayscale
     * Uses the AWT Graphics2D class to do the conversion, so it may add
     * an item to your dock/menu bar as if you're loading a GUI program
     * @param url where to download the image
     * @throws IOException if the image can't be downloaded for some reason
     */
    public GrayscaleImage(URL url) throws IOException {
        var inputImage = ImageIO.read(url);
        //convert input image to grayscale
        //based on (https://stackoverflow.com/questions/6881578/how-to-convert-between-color-models)
        var grayImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d= grayImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, null);
        g2d.dispose();
        imageData = new double[grayImage.getHeight()][grayImage.getWidth()];

        //raster is basically a width x height x 1 3-dimensional array
        var grayRaster = grayImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                //getSample parameters are x (our column) and y (our row), so they're "backwards"
                imageData[row][col] = grayRaster.getSampleDouble(col, row, 0);
            }
        }
    }

    public void savePNG(File filename) throws IOException {
        var outputImage = new BufferedImage(imageData[0].length, imageData.length, BufferedImage.TYPE_BYTE_GRAY);
        var raster = outputImage.getRaster();
        for(var row = 0; row < imageData.length; row++){
            for(var col = 0; col < imageData[0].length; col++){
                raster.setSample(col, row, 0, imageData[row][col]);
            }
        }
        ImageIO.write(outputImage, "png", filename);
    }

    ///Methods to be filled in by students below

    /**
     * Get the pixel brightness value at the specified coordinates
     * (0,0) is the top left corner of the image, (width -1, height -1) is the bottom right corner
     * @param x horizontal position, increases left to right
     * @param y vertical position, **increases top to bottom**
     * @return the brightness value at the specified coordinates
     * @throws IllegalArgumentException if x, y are not within the image width/height
     */
    public double getPixel(int x, int y)
    {
    	return imageData[y][x];
    }

    /**
     * Two images are equal if they have the same size and each corresponding pixel
     * in the two images is exactly equal
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other)
    {
        if(!(other instanceof GrayscaleImage))
        {
            return false;
        }

        GrayscaleImage otherImage = (GrayscaleImage)other;
        return Arrays.deepEquals(this.imageData, otherImage.imageData);
    }


    /**
     * Computes the average of all values in image data
     * @return the average of the imageData array
     */
    public double averageBrightness()
    {
    	double sumOfBrightness = 0;
    	double pixelCount = 0;
    	for(double[] y : imageData) 
    	{
    		for(double x : y)
    		{
    			sumOfBrightness += x;
    			pixelCount ++;
    		}
    	}
        return sumOfBrightness/pixelCount;
    }

    /**
     * Return a new GrayScale image where the average new average brightness is 127
     * To do this, uniformly scale each pixel (ie, multiply each imageData entry by the same value)
     * Due to rounding, the new average brightness will not be 127 exactly, but should be very close
     * The original image should not be modified
     * @return a GrayScale image with pixel data uniformly rescaled so that its averageBrightness() is 127
     */
    public GrayscaleImage normalized()
    {
    	//sumOfBrightness/pixelCount = 127
    	//sumOfBrightness*scale = 127*pixelCount
    	//scale = (127*pixelCount)/sumOfBrightness
    	// = 127*(averageBrightness^(-1))
    	double scale = 127.0*Math.pow(this.averageBrightness(), -1);
    	double[][] normalData = new double[imageData.length][imageData[0].length];
    	for(int y = 0; y < normalData.length; y++)
    	{
    		for(int x = 0; x < normalData[0].length; x++)
    		{
    			normalData[y][x] = imageData[y][x]*scale;
    		}
    	}
        return new GrayscaleImage(normalData);
    }


    /**
     * Returns a new grayscale image that has been "mirrored" across the y-axis
     * In other words, each row of the image should be reversed
     * The original image should be unchanged
     * @return a new GrayscaleImage that is a mirrored version of the this
     */
    public GrayscaleImage mirrored()
    {
    	double[][] mirrorData = new double[imageData.length][imageData[0].length];
    	for(int y = 0; y < mirrorData.length; y++)
    	{
    		for(int x = 0; x < mirrorData[0].length; x++)
    		{
    			//put the last element in the first element slot and so on
    			mirrorData[y][x] = imageData[y][imageData[0].length-(1+x)];
    		}
    	}
        return new GrayscaleImage(mirrorData);
    }

    /**
     * Returns a new GrayscaleImage of size width x height, containing the part of `this`
     * from startRow -> startRow + height, startCol -> startCol + width
     * The original image should be unmodified
     * @param startRow
     * @param startCol
     * @param width
     * @param height
     * @return A new GrayscaleImage containing the sub-image in the specified rectangle
     * @throws IllegalArgumentException if the specified rectangle goes outside the bounds of the original image
     */
    public GrayscaleImage cropped(int startRow, int startCol, int width, int height)
    {
    	//throw an error if we're out of bounds
    	if(width > imageData.length || height > imageData[0].length)
    	{
            throw new IllegalArgumentException("Specified rectangle goes outside the bounds of the original image");
        }
    	double[][] cropData = new double[height][width];
    	//crop where they tell you to
    	for(int y = startRow; y <= height; y++)
    	{
    		for(int x = startCol; x <= width; x++)
    		{
    			cropData[y-startRow][x-startCol] = imageData[y][x];
    		}
    	}
        return new GrayscaleImage(cropData);
    }

    /**
     * Returns a new "centered" square image (new width == new height)
     * For example, if the width is 20 pixels greater than the height,
     * this should return a height x height image, with 10 pixels removed from the left and right
     * edges of the image
     * If the number of pixels to be removed is odd, remove 1 fewer pixel from the left or top part
     * (note this convention should be SIMPLER/EASIER to implement than the alternative)
     * The original image should not be changed
     * @return a new, square, GrayscaleImage
     */
    public GrayscaleImage squarified()
    {
    	//if height is greater, set equal to width
    	int newHeight = 
    			imageData.length > imageData[0].length 
    				? imageData[0].length 
    				: imageData.length;	
    	//if width is greater, set equal to height
    	int newWidth = 
    			imageData[0].length > imageData.length 
    				? imageData.length 
    				: imageData[0].length;
    	//calculate where to crop the edges
    	int modY = (imageData.length - newHeight)/2;
    	int modX = (imageData[0].length - newWidth)/2;
    	double[][] squareData = new double[newHeight][newWidth];
    	for(int y = 0; y < newHeight; y++)
    	{
    		for(int x = 0; x < newWidth; x++)
    		{
    			squareData[y][x] = imageData[y+modY][x+modX];
    		}
    	}
        return new GrayscaleImage(squareData);
    }
}