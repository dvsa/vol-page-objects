package utils;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static int generateRandomNumber(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String generateRandomWordOfLength(int numberOfCharacters){
        StringBuilder word = new StringBuilder(numberOfCharacters);

        for(int i = 0; i < numberOfCharacters; i++){
            word.append(generateRandomLetter());
        }

        return word.toString();
    }

    public static char generateRandomLetter(){
        int lowercaseASCIICode = generateRandomNumber(97, 122);
        int uppercaseASCIICode = generateRandomNumber(65, 90);

        return generateRandomBoolean() ? (char) uppercaseASCIICode : (char) lowercaseASCIICode;
    }

    public static boolean generateRandomBoolean(){
        return (generateRandomNumber(0, 1) == 1) ? true : false;
    }

}
