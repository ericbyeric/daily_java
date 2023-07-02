package day001;

import java.security.SecureRandom;

public final class Day001 {

    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static void main(String[] args) {
        System.out.println("Generating a number between 50 and 100...");
        System.out.println(randomNumberBetween(50, 100));
    }

    private static int randomNumberBetween(int min, int max){
        return SECURE_RANDOM.nextInt(max - min) + min;
    }
}
