import bagel.*;
import bagel.util.Rectangle;

public class Ball extends GameObject {

    //Fixed position where the ball appears at the start
    public static final double BALL_STARTING_X = 512;
    public static final double BALL_STARTING_Y = 32;
    //Magnitude of ball is 10p/s
    public static final double MAGNITUDE = 10;
    //Velocity increase at which the coordinate of y of ball increases
    public static final double Y_VELOCITY_INCREASE = 0.15;
    //Power for squaring is 2
    public static final double SQUARE = 2;
    //x and y velocity that Ball moves at
    private double xVelocity;
    private double yVelocity;

    //Constructor for Ball
    public Ball(String filename) {
        super(filename);
        this.setExists(false);
    }

    /*
     * Override draw method in superclass Image
     * Draws the ball
     * If there is no ball on screen, render the ball
     * to start at the starting x and y
     * The velocity is set in the ShadowBounce class
     *
     * If there is a ball on screen,
     * render the ball to be dropping
     * by increasing x and y
     * If the ball hits the left or right of the window,
     * Reverse the x velocity so it bounces
     * If it falls below the bottom or top of the window
     * Make it disappear
     */
    @Override
    public void draw(double x, double y) {
        if(this.getExists() == false) {
            super.draw(this.BALL_STARTING_X, this.BALL_STARTING_Y, new DrawOptions());
            this.setX(x);
            this.setY(y);
            this.setExists(true);
        }
        else {
            if(this.getX() > Window.getWidth() || this.getX() < 0) {
                this.setXVelocity(-this.xVelocity);
            }
            if(this.getY() > Window.getHeight() || this.getY() < 0){
                this.setExists(false);
            }
            super.draw(this.getX() + this.xVelocity, this.getY() + this.yVelocity, new DrawOptions());
            this.setX(this.getX() + this.xVelocity);
            this.setY(this.getY() + this.yVelocity);
            this.setYVelocity(this.yVelocity + Y_VELOCITY_INCREASE);
        }
    }

    /*
     * Set velocity of ball by distance to initial mouse position scaled to 10 pixels
     */
    public void setVelocity(double xMousePosition, double yMousePosition) {
        double xDistance = xMousePosition - this.getX();
        double yDistance = yMousePosition - this.getY();
        double distance = Math.sqrt(Math.pow(xDistance, SQUARE) + Math.pow(yDistance, SQUARE));
        this.xVelocity = xDistance / distance * MAGNITUDE;
        this.yVelocity = yDistance / distance * MAGNITUDE;
    }

    /*
     * Get velocity of y coordinate of ball
     */
    public double getYVelocity() { return this.yVelocity; }

    /*
     * Set velocity of y coordinate of ball
     */
    private void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /*
     * Get velocity of x coordinate of ball
     */
    public double getXVelocity() { return this.xVelocity; }

    /*
     * Set velocity of x coordinate of ball
     */
    private void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }
}
