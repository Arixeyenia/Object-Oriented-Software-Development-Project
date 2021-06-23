import bagel.*;


public class ShadowBounce extends AbstractGame {

    //Fixed position where the ball appears
    final double BALL_STARTING_X = 512;
    final double BALL_STARTING_Y = 32;
    //Number of pegs in a game
    final int NUM_OF_PEGS = 50;
    //Create objects used in game: peg and ball
    Ball Ball;
    Peg[] Pegs;

    //ShadowBounce game constructor
    public ShadowBounce(){
        Ball = new Ball("res/ball.png");
        Pegs = new Peg[NUM_OF_PEGS];
        for (int i = 0; i < NUM_OF_PEGS; i++) {
            Pegs[i] = new Peg("res/peg.png");
        }
    }

    //Entry point
    public static void main(String[] args){
        ShadowBounce game = new ShadowBounce();
        game.run();
    }

    //Override update method from Abstract Game
    @Override
    protected void update(Input input) {
        /*
        * Initialise ball on screen if
        * left mouse button is clicked
        * and ball has disappeared off screen
        *
        * Set velocity of the ball from
        * the position of the initial mouse click
        */
        if(input.wasPressed(MouseButtons.LEFT) && Ball.getExists() == false){
            Ball.draw(BALL_STARTING_X, BALL_STARTING_Y);
            Ball.setVelocity(input.getMouseX(), input.getMouseY());
        }

        /*
        * If ball is on screen, render the ball
        * to drop with with updating x and y
        */
        if(Ball.getExists() == true) {
            Ball.draw(0, 0);
        }

        /*
        * For each peg in pegs array,
        * If the peg has not been hit by
        * the ball, render it
        * If it has been hit by the ball
        * Clear the peg so it won't be rendered
        * */
        for (int i = 0; i < Pegs.length; i++) {
            if(Ball.getBoundingBox().intersects(Pegs[i].getBoundingBox())){
                Pegs[i].setExists(false);
            }

            Pegs[i].draw();
        }
    }
}