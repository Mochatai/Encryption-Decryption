import java.io.File; 
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;

public class Crack101 {

    static void addToDic(Character charFromLine, HashMap<Character, Double> objHashMap){

        if(objHashMap.containsKey(charFromLine)){
            Double temp = objHashMap.get(charFromLine);
            temp = temp + 1;
            objHashMap.put(charFromLine, temp);
        }
        
        else{
            objHashMap.put(charFromLine, 1.0);
        }


    }

    static HashMap<Character, Double> readFrom(){

        File myOpFile = new File("Encrypted.txt");
        HashMap<Character,Double> dic = new HashMap<>();
        
        try {
            Scanner myReader = new Scanner(myOpFile);
            
            while (myReader.hasNext()) {

                String line = myReader.nextLine();

                for(int i = 0; i < line.length(); i++){

                    addToDic(line.charAt(i), dic);

                }

                
            }

            myReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dic;

    }






    public static void main(String[] args){
        HashMap<Character,Double> test = new HashMap<>();
        Double totalNumbers = 0.0;
        test = readFrom();
        String line= "%s| \t\t | \t\t%.2f \n";
        String result = "";
        
        
        for (Map.Entry<Character, Double> entery : test.entrySet()){
            totalNumbers = totalNumbers + entery.getValue();
        }

        System.out.println(totalNumbers);

        for (Map.Entry<Character, Double> entery : test.entrySet()){
            test.put(entery.getKey(), (entery.getValue()/totalNumbers) * 100.0);
        }

        try {
            FileOutputStream fos = new FileOutputStream("frequency.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos,StandardCharsets.UTF_8);
            BufferedWriter writer = new BufferedWriter(osw);
            for (Map.Entry<Character, Double> entery : test.entrySet()){
            result = String.format(line, entery.getKey(),entery.getValue());
            System.out.println(result);
            writer.write(result);
            result = "";
        
        }
        writer.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        

        




    }

    
}
