import java.io.File; 
import java.io.FileWriter;
import java.util.Scanner;

public class Encrypt101 {

    static int fromCharToNumber(char letter) {

        int asciiForChar = (int) letter;
        return asciiForChar;
    }

    static char fromNumberToChar(int asc){

        char charForAscii = (char) asc;
        return charForAscii;
    }

    static String encryptLine(String line, int stepsToMove){

        int lineLength = -1;
        int currentLetterAscii = 0;
        int asciiAfterMoving = 0;
        lineLength = line.length();
        int charToAsciiArray[] = new int[lineLength];
        char asciiToCharArray[] = new char[lineLength];

        for (int i = 0; i < lineLength; i++) {
            currentLetterAscii = fromCharToNumber(line.charAt(i));
            System.out.println(currentLetterAscii);
            if (currentLetterAscii >= 65 && currentLetterAscii <= 90) {

                currentLetterAscii = currentLetterAscii - 64;
                asciiAfterMoving = (currentLetterAscii + stepsToMove) % 26;

                if (asciiAfterMoving == 0) {
                    asciiAfterMoving = 26;
                }

                asciiAfterMoving = asciiAfterMoving + 64;
                charToAsciiArray[i] = asciiAfterMoving;
                
                
            }

            else if (currentLetterAscii >= 97 && currentLetterAscii <= 122) {
                
                currentLetterAscii = currentLetterAscii - 96;
                asciiAfterMoving = (currentLetterAscii + stepsToMove) % 26;

                if (asciiAfterMoving == 0) {
                    asciiAfterMoving = 26;
                }

                asciiAfterMoving = asciiAfterMoving + 96;
                charToAsciiArray[i] = asciiAfterMoving;
                

            }

            else {
                charToAsciiArray[i] = currentLetterAscii;
                
            }

        }

        for(int i = 0; i < lineLength; i++) {
            asciiToCharArray[i] = fromNumberToChar(charToAsciiArray[i]);
            
        }

        String arrayOfCharsToStraing = new String(asciiToCharArray);
        return arrayOfCharsToStraing;

    }

    public static void main(String[] args) {

        //===========================================
        String readFrom = "plain.txt";
        String writeTo = "encrypted.txt";
        int stepsToShift = 1;
        //===========================================
        try {
            File myPlainObj = new File(readFrom);
            FileWriter myEncryptWriter = new FileWriter(writeTo);

            Scanner myReader = new Scanner(myPlainObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                data = encryptLine(data, stepsToShift);
                data = data + '\n';
                myEncryptWriter.write(data);
                System.out.println(data);
                
            }
            System.out.println((25 + 1) % 26);

            myReader.close();
            myEncryptWriter.close();
            
        } catch (Exception e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
    }
}
