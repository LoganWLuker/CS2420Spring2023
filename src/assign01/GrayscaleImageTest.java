package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;
    private GrayscaleImage bigWide;
    private GrayscaleImage tallSmall;

    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        smallWide = new GrayscaleImage(new double[][]{{1,2,3},{4,5,6}});
        bigWide = new GrayscaleImage(new double[][]{{1,2,3,4,5},{6,7,8,9,10}});
        tallSmall = new GrayscaleImage(new double[][]{{1,2},{3,4},{5,6}});
    }

    @Test
    void testGetPixel(){
        assertEquals(smallSquare.getPixel(0,0), 1);
        assertEquals(smallSquare.getPixel(1,0), 2);
        assertEquals(smallSquare.getPixel(0,1), 3);
        assertEquals(smallSquare.getPixel(1,1), 4);

    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5*.001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127*.001);
        var scale = 127/2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2*scale},{3*scale, 4*scale}});
        for(var row = 0; row < 2; row++){
            for(var col = 0; col < 2; col++){
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row)*.001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2,1},{4,3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1,1,1,1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }
    /**
     * Test that the error throws if It's out of bounds
     */
    @Test
    void cropThrow() {
    	assertThrows(IllegalArgumentException.class, () -> { smallSquare.cropped(0,0,3,3); });
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{4,5}});
        assertEquals(squared, expected);
    }
    /**
     * Test bigger array
     */
    @Test
    void bigSquarified() {
        var squared = bigWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{2,3},{7,8}});
        assertEquals(squared, expected);
    }
    /**
     * Test a tall array to see if it squares off the y axis too
     */
    @Test
    void tallSquarified() {
        var squared = tallSmall.squarified();
        var expected = new GrayscaleImage(new double[][]{{1,2},{3,4}});
        assertEquals(squared, expected);
    }
    /**
     * Test that if It's already a square, it stays a square
     */
    @Test
    void squareSquarified() {
    	var squared = smallSquare.squarified();
    	var expected = new GrayscaleImage(new double[][] {{1,2},{3,4}});
    	assertEquals(squared, expected);
    }
}