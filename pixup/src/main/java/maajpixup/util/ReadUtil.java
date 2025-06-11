package maajpixup.util;

import java.util.Scanner;

public class ReadUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt() {

        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida, por favor ingrese un número entero.");
            scanner.next();
            System.out.print("Ingrese un número entero: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static double readDouble() {

        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida, por favor ingrese un número decimal.");
            scanner.next();
            System.out.print("Ingrese un número decimal: ");
        }
        double value = scanner.nextDouble();
        scanner.nextLine();
        return value;
    }

    public static String read() {
        return readString();
    }
}
