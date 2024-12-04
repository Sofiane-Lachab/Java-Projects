/*
 * Written By: Sofiane Lachab
 */
import javax.swing.*;//GUI
import java.awt.Graphics;//draw shapes
import java.awt.Color;//set black and white
/**
 * Class that utilizes a recursive function to draw the shape pattern known
 * as "Sierpinski's Triangles." Java Swing is also used to display the
 * triangels once they are drawn. The class extends JPanel to make a JPanel
 * with the shapes, and then puts the panel onto a JFrame.
 */
public class Sierpinski extends JPanel
{
    private static final int FRAME_DIM = 500;//dimensions of the fram
    private static JFrame frame = new JFrame("Sierpinski's Triangles");
    public static void main(String[] args)
    {
        frame.setSize(FRAME_DIM, FRAME_DIM);//frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close on x
        frame.setBackground(Color.WHITE);//white background
        Sierpinski panel = new Sierpinski();//runs paintComponent for panel
        frame.add(panel);//puts panel on the frame
        frame.setVisible(true);//displays to user
    }
    /**
     * Resets the newly created frame and draws the initial black triangle
     * that acts as the background for the pattern. Then, after changing
     * the color to white, makes a call to the recursive helper function.
     */
    public void paintComponent(Graphics g)//graphics object to draw triangles
    {
        super.paintComponent(g);//reset the frame to be clean
        int[] xVertices = {0, FRAME_DIM/2, FRAME_DIM};//initial triangle
        int[] yVertices = {FRAME_DIM, 0, FRAME_DIM};//initial triangle
        int points = 3;//vertices
        g.setColor(Color.BLACK);//initial triangle is black
        g.fillPolygon(xVertices, yVertices, points);//draws the triangle
        
        g.setColor(Color.WHITE);//rest of the triangles are white
        drawTriangles(g, xVertices, yVertices, points);//calls recursive method
    }
    /**
     * Recursive method that draws the upside-down white triangles to fill out
     * the pattern. Calculates the vertices of the triangle to draw it, then is
     * recursively called being fed the remaining black triangles around the
     * newly drawn white triangle. Keeps going until a pixel limit of 4
     * is reached.
     */
    private void drawTriangles(Graphics g, int[] xPos, int[] yPos, int numPoints)
    {
        //pixel limit 4 means left and mid vertex should be 1 pixel apart
        if(xPos[1] - 1 <= xPos[0])//base condition
            return;

        //vertices for the upside-down white triangle
        int[] midX = {(xPos[0]+xPos[1]) / 2, xPos[1], (xPos[1]+xPos[2]) / 2};
        int[] midY = {(yPos[0] + yPos[1]) / 2, (yPos[0] + yPos[2]) / 2, (yPos[1] + yPos[2]) / 2};

        g.fillPolygon(midX, midY, numPoints);//new white triangle

        //remaining black triangle to the left of white triangle
        int[] leftX = {xPos[0], midX[0], midX[1]};
        int[] leftY = {yPos[0], midY[0], midY[1]};
        drawTriangles(g, leftX, leftY, numPoints);//recursive call

        //remaining black triangle above white triangle
        int[] topX = {midX[0], midX[1], midX[2]};
        int[] topY = {midY[0], yPos[1], midY[2]};
        drawTriangles(g, topX, topY, numPoints);//recursive call

        //remaining black triangle to the right of white triangle
        int[] rightX = {midX[1], midX[2], xPos[2]};
        int[] rightY = {midY[1], midY[2], yPos[2]};
        drawTriangles(g, rightX, rightY, numPoints);//recursive call
    }
}