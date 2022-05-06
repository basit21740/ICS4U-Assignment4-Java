/*
* This is a class that does
* every calculations for triangles.
*
* @author  Abdul Basit
* @version 1.0
* @since   2022-02-02
*/

public final class CalculateTriangle {
    /** This is an array for sides. */
    private double[] side;
    /** This is an array for angles. */
    private double[] angle;
    /** This is the side that isn't 0. */
    private double ultimateSide;
    /** This is the angle that isn't 0. */
    private double ultimateAngle;
    /** This is the side's number that isn't 0. */
    private int ultimateSideNumber = 0;
    /** This is the angle's number that isn't 0. */
    private int ultimateAngleNumber = 0;
    /** This is the triangle's state. */
    private String triangleState;

    /** The sum of angles of a triangle. */
    private static final int FINALANGLE = 180;

    /**
    * This is the constructor for the triangle class.
    * @param inputSide
    * @param inputAngle
    */
    public CalculateTriangle(final double[] inputSide,
                             final double[] inputAngle) {
        this.side = inputSide;
        this.angle = inputAngle;
    }

    /**
    * This method figures out
    * what kind of triangle it is.
    * @param sideNumber
    */
    public void whatKind(final int sideNumber) {
        boolean isAAS = false;
        boolean isSSA = false;
        if (sideNumber == 1) {
            /* logic in this if statement only implies when one side and two
            angles are given.
            Possibilities: AAS, ASA */
            for (int counter = 0; counter < this.side.length; counter++) {
                if (this.side[counter] != 0) {
                    /* for every sides, find the side that isn't equal to 0,
                    and make that the ultimate Side. Also, figure out what
                    number the side is(if it is side 1, side 2, or side 3). */
                    ultimateSide = this.side[counter];
                    ultimateSideNumber = counter;
                }
                if (this.angle[counter] != 0) {
                    /* for every angle, if the angle is not equal to 0,
                    figure out what number the angle is(if it is angle 1,
                    angle 2, or angle 3). */
                    ultimateAngleNumber = counter;
                    if (ultimateSideNumber == ultimateAngleNumber) {
                        /* if the number of the side and the number of the
                        angle is equal, it is 100% an AAS triangle.
                        If they are equal, the angle would always be the
                        opposite to the side, therefore impossible to have a
                        side betwwen two given angles.*/
                        isAAS = true;
                    }
                }
            }
            if (isAAS) {
                triangleState = "AAS";
            } else {
                triangleState = "ASA";
            }
        } else if (sideNumber == 2) {
            /* logic in this if statement only implies when two sides and one
            angle is given.
            Possibilities: SSA, SAS */
            for (int counter = 0; counter < this.angle.length; counter++) {
                if (this.angle[counter] != 0) {
                    /* for every angle, find the angle that isn't equal to 0,
                    and make that the ultimate angle. Also, figure out what
                    number the angle is(if it's angle1, angle2, or angle3). */
                    ultimateAngle = this.angle[counter];
                    ultimateAngleNumber = counter;
                }
                if (this.side[counter] != 0) {
                    /* for every side, if the side is not equal to 0,
                    figure out what number the side is(if it is side 1,
                    side 2, or side 3).*/
                    ultimateSide = this.side[counter];
                    ultimateSideNumber = counter;
                    if (ultimateAngleNumber == ultimateSideNumber) {
                        /* if the number of the angle and the number of the
                        side is equal, it is 100% a SSA triangle.
                        If they are equal, the side would always be the
                        opposite to the angle, therefore impossible to have a
                        angle betwwen two given sides. */
                        isSSA = true;
                    }
                }
            }
            if (isSSA) {
                triangleState = "SSA";
            } else {
                triangleState = "SAS";
            }
        } else {
            // if it's not AAS, ASA, SSA, or SAS, it has to be SSS
            triangleState = "SSS";
        }
    }

    /**
    * This method figures out
    * the three sides of the triangles.
    */
    public void findSides() {
        double singleAngle = FINALANGLE;
        double emptySide;
        double emptyAngle;
        switch (triangleState) {
            case "AAS":
            case "ASA":
                for (int counter = 0; counter < this.angle.length; counter++) {
                    singleAngle -= this.angle[counter];
                }
                for (int counter = 0; counter < side.length; counter++) {
                    if (angle[counter] == 0) {
                        angle[counter] = singleAngle;
                    }
                }
                for (int counter = 0; counter < angle.length; counter++) {
                    if (side[counter] == 0) {
                        emptySide = (ultimateSide
                        * Math.sin(Math.toRadians(angle[counter])))
                        / Math.sin(Math.toRadians((angle[ultimateSideNumber])));
                        side[counter] = emptySide;
                    }
                }
                break;
            case "SSA":
                for (int counter = 0; counter < angle.length; counter++) {
                    if (angle[counter] == 0) {
                        // add angles
                        emptyAngle = Math.asin((side[counter]
                        * Math.sin(Math.toRadians(ultimateAngle)))
                        / side[ultimateAngleNumber]);
                        double degreeEmptyAngle = Math.toDegrees(emptyAngle);
                        angle[counter] = degreeEmptyAngle;
                    }
                }
                for (int counter = 0; counter < this.angle.length; counter++) {
                    singleAngle -= this.angle[counter];
                }
                for (int counter = 0; counter < side.length; counter++) {
                    if (angle[counter] == 0) {
                        angle[counter] = singleAngle;
                    }
                }
                for (int counter = 0; counter < angle.length; counter++) {
                    if (side[counter] == 0) {
                        emptySide = (ultimateSide * Math.sin(Math.toRadians(
                                     angle[counter])))
                                     / Math.sin(Math.toRadians(
                                     (angle[ultimateSideNumber])));
                        side[counter] = emptySide;
                    }
                }
                break;
            case "SAS":
                for (int counter = 0; counter < side.length; counter++) {
                    if (counter == 2) {
                        if (angle[0] == angle[2]) {
                            emptySide = Math.sqrt(Math.pow(side[0], 2)
                            + Math.pow(side[2], 2) - 2 * side[0] * side[2]
                            * Math.cos(Math.toRadians(ultimateAngle)));
                            side[ultimateAngleNumber] = emptySide;
                        }
                    } else if (angle[counter] == angle[counter + 1]) {
                        emptySide = Math.sqrt(Math.pow(side[counter], 2)
                                              + Math.pow(side[counter + 1 ], 2)
                                              - 2 * side[counter]
                                              * side[counter + 1]
                                              * Math.cos(Math.toRadians(
                                                         ultimateAngle)));
                        side[ultimateAngleNumber] = emptySide;
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
    * This method figures out
    * the perimeter using three sides.
    * @return perimeter
    */
    public double getPerimeter() {
        double perimeter = 0;
        for (int counter = 0; counter < side.length; counter++) {
            perimeter += side[counter];
        }
        return perimeter;
    }

    /**
    * This method figures out
    * the area using three sides.
    * @return area
    */
    public double getArea() {
        double area;
        double semiPerimeter = getPerimeter() / 2;
        area = Math.sqrt(semiPerimeter * (semiPerimeter - side[0])
                         * (semiPerimeter - side[1])
                         * (semiPerimeter - side[2]));
        return area;
    }

    /**
    * This method figures out the
    * type of triangle using three sides.
    * @return type
    */
    public String getName() {
        if (side[0] == side[1] && side[1] == side[2]) {
            return "Equilateral Triangle";
        } else if (side[0] != side[1] && side[1] != side[2]
                   && side[2] != side[0]) {
            return "Scalene Triangle";
        } else if ((side[0] == side[1] && side[1] != side[2])
                 || (side[0] != side[1]
                 && side[2] == side[0]) || (side[2] == side[1]
                 && side[2] != side[0])) {
            return "Isosceles Triangle";
        } else {
            return null;
        }
    }

    /**
    * This method prints
    * out the height.
    */
    public void printHeight() {
        double height;
        double base;
        double area = getArea();
        for (int counter = 0; counter < side.length; counter++) {
            base = side[counter];
            height = (2 * area) / base;
            System.out.print("When base is side ");
            System.out.println(counter + 1 + ", height: " + height);
        }
    }

    /**
    * This method figures
    * out the Inradius.
    * @return inRadius
    */
    public double getInradius() {
        double inRadius;
        double area = getArea();
        double semiPerimeter = getPerimeter() / 2;
        inRadius = area / semiPerimeter;
        return inRadius;
    }

    /**
    * This method figures out
    * the circumcircle's Area.
    * @return inRadius
    */
    public double getCircumcircleArea() {
        double circumRadius;
        double circumArea;
        circumRadius = (side[0] * side[1] * side[2])
                        / (Math.sqrt((side[0] + side[1] + side[2])
                        * (side[1] + side[2] - side[0])
                        * (side[2] + side[0] - side[1])
                        * (side[0] + side[1] - side[2])));

        circumArea = Math.PI * Math.pow(circumRadius, 2);
        return circumArea;
    }

    /**
    * This method checks if the
    * triangle is valid using three sides.
    * @return boolean
    */
    protected boolean isTriangleValid() {
        if (Double.isNaN(angle[0]) || Double.isNaN(angle[1])
            || Double.isNaN(angle[2])) {
                System.out.println("nan");
            return false;
        } else {
            return !(side[0] + side[1] <= side[2] || side[0] + side[2]
                     <= side[1] || side[1] + side[2] <= side[0]);
        }
    }
}
