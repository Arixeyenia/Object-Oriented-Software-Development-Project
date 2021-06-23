import bagel.*;
import bagel.util.Rectangle;

public abstract class GameObject extends Image {

    private double x;
    private double y;
    private boolean exists;

    public GameObject(String filename) {
        super(filename);
    }

    /*
     * Get bounding box of object
     */
    @Override
    public Rectangle getBoundingBox(){
        return new Rectangle(this.x, this.y, this.getWidth(), this.getHeight());
    }

    /*
     * Get if the object exists
     */
    public boolean getExists(){
        return this.exists;
    }

    /*
     * Set if the object exists
     */
    public void setExists(boolean exists) { this.exists = exists; }

    /*
     * Get x coordinate of object
     */
    public double getX() { return this.x; }

    /*
     * Set x coordinate of object
     */
    public void setX(double x) {
        this.x = x;
    }

    /*
     * Get y coordinate of object
     */
    public double getY() { return this.y; }

    /*
     * Set y coordinate of object
     */
    public void setY(double y) {
        this.y = y;
    }
}
