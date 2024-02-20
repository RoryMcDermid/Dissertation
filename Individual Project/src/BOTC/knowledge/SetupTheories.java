package BOTC.knowledge;

import BOTC.GetInput;
import BOTC.Roles.SuperRole;
import BOTC.Trait;

import java.util.HashMap;
import java.util.Objects;

public class SetupTheories {

    //each possible fact (theory) can have multiple possible answers, but only one true answer.
    //array of length of users (always a set number of users)
    //each user can have only a given set of theories (have each class be able to return what type values they can have ?), maybe this be a dictionary?
    //each theory can either have multiple false or one true
    //the string is either "truth" denoting the theory as fact, or it is "x,y" denoting the value that it connects to by indexing in the array, or it is just a single number if it doesn't have a source link
    GetInput input = new GetInput();

    public HashMap<String, Theory>[][] activate(SuperRole[] players){



        HashMap<String, Theory>[][] theories = new HashMap[players.length][Trait.values().length];

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                theories[i][j] = new HashMap<>();
            }
        }

        //initiate known player data (data inputted in CreateGame)
        for (int i = 0; i < players.length; i++) {
            if(!Objects.equals(players[i].getPlayerName(), "UNKNOWN")){
                theories[i][0].put("truth", new Theory(players[i], Trait.NAME, players[i].getPlayerName()));
            }

            if(players[i].getAlignment() != -1){
                theories[i][1].put("truth", new Theory(players[i], Trait.ALIGNMENT, players[i].getAlignment()));
            }

            String alive = input.readIn("check", "Is player "+ (i + 1) +" still alive");
            if(Objects.equals(alive, "true")){
                theories[i][2].put("truth", new Theory(players[i], Trait.ALIVE, true));
            }
            else if (Objects.equals(alive, "false")){
                theories[i][2].put("truth", new Theory(players[i], Trait.ALIVE, false));
            }


            if(!Objects.equals(players[i].getClassName(), "Unknown")){
                theories[i][3].put("truth", new Theory(players[i], Trait.CLASS, players[i].getClassName()));
            }
        }
        //add possible theories in createConnections



        return theories;
    }




}
