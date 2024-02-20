package BOTC;

import java.util.Objects;
import java.util.Scanner;

public class GetInput {


    public String readIn(String dataType, String requestSentence){

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(requestSentence);

        String temp = scanner.nextLine();

        if(dataType.equals("int")){
            while (!temp.chars().allMatch(Character::isDigit)){
                System.out.println("Enter a number");

                temp = scanner.nextLine();
            }
        }

        if(dataType.equals("classname")){
            boolean endLoop = false;
            while (true){
                try {
                    //if this check succeeds, then temp is the name of a class
                    ClassName myEnumValue = ClassName.valueOf(temp);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter a class name, must be all uppercase, no spaces");
                    temp = scanner.nextLine();
                }
            }
        }

        if(dataType.equals("alignment")){
            boolean endLoop = false;
            while (true){
                try {
                    //if this check succeeds, then temp is the name of a class
                    Alignment myEnumValue = Alignment.valueOf(temp);
                    switch (myEnumValue){
                        case UNKNOWN:
                            temp = "-1";
                            break;
                        case GOOD:
                            temp = "0";
                            break;
                        case EVIL:
                            temp = "1";
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter an alignment name, must be \"GOOD\", \"EVIL\" or \"UNKNOWN\"");
                    temp = scanner.nextLine();
                }
            }
        }

        if(dataType.equals("trait")){
            while (true) {
                try {
                    //if this check succeeds, then temp is the name of a trait
                    Trait myEnumValue = Trait.valueOf(temp);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Please enter an trait name (see the enum Trait)");
                    temp = scanner.nextLine();
                }
            }
        }

        if(dataType.equals("check")){
            while (true) {
                if(Objects.equals(temp, "true") || Objects.equals(temp, "false") ){
                    break;
                }
                else{
                    System.out.println("Please return \"true\" or \"false\"");
                    temp = scanner.nextLine();
                }
            }
        }

        if(dataType.equals("correlation")){
            boolean endloop = true;
            while (endloop){
                switch (temp){
                    default:
                        System.out.println("Please input 0 (unrelated), 1 (a implies b is true) or 2 (a implies b is false)");
                        temp = scanner.nextLine();
                    case "0":
                    case "1":
                    case "2":
                        endloop = false;
                }
            }

        }



        return temp;

    }

}
