import java.util.HashMap;

/**
 * Created by Ayush Bandil on 6/11/2019.
 */
public class Main {

    public static void main(String[] args) {
        Double length = 100d;
        int noOfCards = 1000;
        Double centerOfMass = 0d; // initialize
        HashMap<Integer, Double> cardsLocation = new HashMap<>();

        for (int i = 1; i <= noOfCards; i++) {
            cardsLocation.put(i, -length / 2);
            centerOfMass = updateCMAfterAddingCardi(i, cardsLocation, centerOfMass, length);
            resetAxis(cardsLocation, centerOfMass);
        }
        drawLocations(cardsLocation, length);
        printLocations(cardsLocation, length);
    }

    private static void printLocations(HashMap<Integer, Double> cardsLocation, Double length) {

        cardsLocation.forEach((card, location) -> {
            Double shift = ((card > 1) ? (-cardsLocation.get(card - 1) + location) : (length / 2));

            System.out.println("Shift = " + shift + " "
                    + "Card 1: [" + (location - length / 2) + " " + (location + length / 2) + "]");
        });
    }

    private static void drawLocations(HashMap<Integer, Double> cardsLocation, Double length) {
        Double scaleDown = 2d;
        int tableLength = 100;
        int tableHeigth = 5;
        cardsLocation.forEach((card, location) -> {
            for (int i = 0; i < (location + tableLength - length / 2) / scaleDown; i++) {
                System.out.print(" ");
            }
            for (int i = 0; i < (length) / scaleDown; i++) {
                System.out.print("_");
            }
            System.out.println();
        });

        for (int i = 0; i < (tableLength) / scaleDown; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int j = 0; j < (tableHeigth) / scaleDown; j++) {
            for (int i = 0; i < (tableLength) / scaleDown; i++) {
                System.out.print("#");
            }
            System.out.print("|");
            System.out.println();
        }
    }

    private static void resetAxis(HashMap<Integer, Double> cardsLocation, Double centerOfMass) {
        cardsLocation.forEach((card, location) -> {
            location -= centerOfMass;
            cardsLocation.put(card, location);
        });

    }

    private static Double updateCMAfterAddingCardi(int cardIndex, HashMap<Integer, Double> cardsLocation, Double centerOfMass, Double length) {
        final int[] totalMass = {0};
        final Double[] addLocation = {0d};
        cardsLocation.forEach((card, location) -> {
            addLocation[0] += location;
            totalMass[0]++;
        });
        return addLocation[0] / totalMass[0];
    }
}


