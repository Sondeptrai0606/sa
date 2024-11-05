package org.example.validate;

import java.util.Scanner;

public class Validated {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputString(String message, String error, int minLength, int maxLength) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            // Kiểm tra chuỗi không được để trống và độ dài nằm trong khoảng cho phép
            if (!input.isEmpty() && input.length() >= minLength && input.length() <= maxLength) {
                return input;
            }
            System.out.println(error + " (Length must be between " + minLength + " and " + maxLength + " characters)");
        }
    }

    public static int inputInteger(String message, String error, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Out of range");
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }

    public static double inputDouble(String message, String error, double min, double max) {
        while (true) {
            try {
                System.out.print(message);
                double number = Double.parseDouble(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Out of range");
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }

    public static float inputFloat(String message, String error, float min, float max) {
        while (true) {
            try {
                System.out.print(message);
                float number = Float.parseFloat(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Out of range");
            } catch (Exception e) {
                System.out.println(error);
            }
        }
    }
}
