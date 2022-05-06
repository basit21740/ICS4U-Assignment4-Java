/*
* This is a program that has
* every information about triangles.
*
* @author  Abdul Basit
* @version 1.0
* @since   2022-02-22
*/

import java.util.Scanner;

public final class MainTriangle {
    private MainTriangle() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
        throw new IllegalStateException("Cannot be instantiated");
    }

    /** The length of array. */
    public static final int THREE = 3;
    /** The sum of angles of a triangle. */
    public static final int FINALANGLE = 180;

    /**
    * This function
    * is the main function.
    * @param args
    */
    public static void main(final String[] args) {
        double[] side;
        double[] angle;
        side = new double[THREE];
        angle = new double[THREE];
        int sideNumber = 0;
        int angleNumber = 0;
        int sumAngle = 0;

        System.out.println("Triangle Program\n");
        System.out.println("Please enter any combination of 3 pieces of"
                           + " information \nabout a triangle (side length or"
                           + " angle) but not 3 angles.");
        System.out.println("\nPlease enter 0 if you don't want to enter"
                           + " any value.\n");
        // input
        Scanner userInput = new Scanner(System.in);
        try {
            for (int counter = 0; counter < side.length; counter++) {
                // get sides
                System.out.print("Side " + (counter + 1) + ": ");
                side[counter] = userInput.nextDouble();
                if (side[counter] != 0) {
                    sideNumber++;
                }
            }
            System.out.println();

            for (int index = 0; index < angle.length; index++) {
                // get angles
                System.out.print("Angle " + (index + 1) + ": ");
                angle[index] = userInput.nextDouble();
                if (angle[index] != 0) {
                    angleNumber++;
                }
                sumAngle += angle[index];
            }
            // create object
            CalculateTriangle myTriangle = new CalculateTriangle(side, angle);

            if (angleNumber + sideNumber != THREE) {
                System.out.println("\nOnly a combination of 3 pieces of "
                                   + "information is allowed.");
            } else if (angleNumber == THREE) {
                System.out.println("\nYou can't enter 3 angles.");
            } else if (sumAngle >= FINALANGLE) {
                System.out.println("\nThe sum of angles is too big.");
            } else {
                System.out.println();
                myTriangle.whatKind(sideNumber);
                myTriangle.findSides();
                if (myTriangle.isTriangleValid()) {
                    System.out.println("Area: " + myTriangle.getArea());
                    System.out.println("\nPerimeter: "
                                       + myTriangle.getPerimeter());
                    System.out.println("\nType: " + myTriangle.getName());
                    System.out.println();
                    myTriangle.printHeight();
                    System.out.println("\nInradius: "
                                       + myTriangle.getInradius());
                    System.out.println("\nCircumcircle area: "
                                       + myTriangle.getCircumcircleArea());
                } else {
                    System.out.println("Triangle can't be created given"
                                       + " these sides/angles.");
                }
            }
        } catch (Exception e) {
            System.out.println("\nPlease enter a number!");
        }
    }
}
