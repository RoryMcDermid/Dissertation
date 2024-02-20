package BOTC.knowledge;

import BOTC.GetInput;
import BOTC.Roles.SuperRole;
import BOTC.Trait;
import BOTC.knowledge.Theory;

import java.util.HashMap;
import java.util.Objects;

public class SetupConnections {

    GetInput input = new GetInput();


    //connections will work as the values in [x][y] will be connections that if true make this value in the theory array true
    //a connection has it's related theory, and the string which dictates whether it is being proved by that theory being true or false (the string says true then true, else false then false)
    public HashMap<String, Connection>[][] createConnections(SuperRole[] players, HashMap<String, Theory>[][] theories){

        HashMap<String, Connection>[][] connections = new HashMap[players.length][Trait.values().length];

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                connections[i][j] = new HashMap<>();
            }
        }

        System.out.println("Input any connections you may have");

        //get player being connected
        //get trait for that player
        //check that the traits don't already have a truth
        //input the new theories and new connection


        //skip the user (player 1, index 0)
        for (int i = 1; i < players.length; i++) {
            String check = input.readIn("", "If you don't have any connections for player " + i + " type NEXT");
            if(Objects.equals(check, "NEXT")){
                continue;
            }

            //this while functions
            while (true){
                if(theories[i][1].containsKey("truth")){
                    break;
                }

                String checkAlign = input.readIn("", "If you don't have any potential alignments for player " + i + " type NEXT");



                if(Objects.equals(checkAlign, "NEXT")){
                    break;
                }

                int correlation = Integer.parseInt(input.readIn("correlation", "What is the relation these theories have? 0 (unrelated), 1 (a implies b is true) or 2 (a implies b is false)"));

                String tempAlign = input.readIn("alignment", "What is the potential alignment");

                String newTheory = input.readIn("check", "Is there a new theory?");


                int player = Integer.parseInt(input.readIn("int", "Which other player is being referenced (by index)"));
                Trait trait = Trait.valueOf(input.readIn("trait", "Which trait is being referenced"));

                Theory theory1 = null;

                if(Objects.equals(newTheory, "true")){

                    theory1 = makeTheory(players, player, trait);
                    if(theory1 == null){
                        continue;
                    }
                    //this is the one that implies (theory 1)
                    int index = hashCheck(theories[player][trait.ordinal()]);
                    theories[player][trait.ordinal()].put(String.valueOf(index), theory1);

                    System.out.println("This theory has the index: " + String.valueOf(index));
                }
                else {
                    String index = input.readIn("", "Which theory is being referenced");
                    while(!theories[player][trait.ordinal()].containsKey(index)){
                        index = input.readIn("", "Please enter a valid index");
                    }
                    theory1 = theories[player][trait.ordinal()].get(index);
                }




                //this is the one being implied (theory 2)
                int index2 = hashCheck(theories[i][1]);
                Theory theory2 = new Theory(players[i], Trait.ALIGNMENT, tempAlign);
                theories[i][1].put(String.valueOf(i), theory2);


                String key = player + "," + trait.ordinal();
                connections[i][1].put(key, new Connection(theory1, theory2, correlation));

            }



        }




        return null;
    }

    //going to trust I won't make a theory that is already true
    public Theory makeTheory(SuperRole[] players, int player, Trait trait){
        switch (trait){
            case NAME:
            case ALIVE:
                System.out.println("This is set, no theories work off of this");
                return null;
            case ALIGNMENT:
                int alignment = Integer.parseInt(input.readIn("alignment", "What is the expected value"));
                return new Theory(players[player], Trait.ALIGNMENT, alignment);
            case CLASS:
                String classname = input.readIn("classname", "What is the expected value");
                return new Theory(players[player], Trait.CLASS, classname);
            case REDHERRING:
                int playerId = Integer.parseInt(input.readIn("int", "What is the index of the redherring"));
                return new Theory(players[player], Trait.REDHERRING, playerId);
        }
        return null;
    }

    //inefficient, but functions
    public int hashCheck(HashMap<String, Theory> theories){
        int checkVal = 0;
        while (theories.containsKey(String.valueOf(checkVal))){
            checkVal++;
        }
        return checkVal;
    }

}
