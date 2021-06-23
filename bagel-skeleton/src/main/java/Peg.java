import bagel.*;
import bagel.util.Rectangle;
import java.util.concurrent.ThreadLocalRandom;

public class Peg extends GameObject {

    //Min and Max x and y for each peg
    public static final double MIN_X = 0;
    public static final double MAX_X = 1024;
    public static final double MIN_Y = 100;
    public static final double MAX_Y = 768;
    //Number of pegs
    public static final int NUM_OF_PEGS = 50;
    //Store all bounding boxes of each peg
    public static Rectangle[] BoundingBoxes = new Rectangle[NUM_OF_PEGS];

    /*
    * Constructor for each peg.
    * Sets random point such that pegs do not overlap
    */
    public Peg(String filename) {
        super(filename);
        this.setExists(true);
        do {
            this.setNewPoint();
        } while (Peg.isOverlap(this.getBoundingBox()) == true);
        Peg.addBoundingBox(this.getBoundingBox());
    }

    /*
    * Checks if the bounding box of the peg being created
    * overlaps with the bounding boxes of any existing
    * pegs.
    * If it overlaps, return true, if no overlaps exist, return false
    */
    public static boolean isOverlap(Rectangle currentRectangle){
        for (int i = 0; i < BoundingBoxes.length; i++) {
            if (BoundingBoxes[i] != null){
                if (currentRectangle.intersects(BoundingBoxes[i])){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    * Add a bounding box to the array of Rectangles
    * when a peg is created to store all bounding boxes
    * to check if any of them overlap
    */
    public static void addBoundingBox(Rectangle boundingBox){
        for (int i = 0; i < BoundingBoxes.length; i++) {
            if(BoundingBoxes[i] == null) {
                BoundingBoxes[i] = boundingBox;
                return;
            }
        }
    }

    /*
    * Render the peg if it exists
    */
    public void draw() {
        if (this.getExists() == true){
            super.draw(this.getX(), this.getY(), new DrawOptions());
        }
    }

    /*
    * Generate a new random x and y for a point
    */
    private void setNewPoint(){
        this.setX();
        this.setY();
    }

    /*
    * Generate new random x coordinate with minimum and maximum x values
    */
    private void setX(){
        this.setX(ThreadLocalRandom.current().nextDouble(MIN_X, MAX_X + 1));
    }

    /*
    * Generate new random y coordinate with minimum and maximum y values
    */
    private void setY(){
        this.setY(ThreadLocalRandom.current().nextDouble(MIN_Y, MAX_Y + 1));
    }

}
