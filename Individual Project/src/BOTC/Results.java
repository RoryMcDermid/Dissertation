package BOTC;

import BOTC.Roles.Demon.Imp;
import BOTC.Roles.SuperRole;
import BOTC.Roles.Townsfolk.Chef;
import BOTC.Roles.Townsfolk.FortuneTeller;
import BOTC.Roles.Unknown;
import BOTC.knowledge.Connection;
import BOTC.knowledge.Theory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Results {

    //test picking random pairs for demon possibilities for fortune tellers against going in sequence, both with and without red herrings

    //initialise (and save?) a setup of players
    //use that in a number of different layouts
    Random playerRand = new Random(1);

    public int[] createRandomList(int count, int minNum, int maxNum){
        int[] returnList = new int[count];
        for (int i = 0; i < count; i++) {
            int temp = playerRand.nextInt(maxNum - minNum) + minNum;
            returnList[i] = temp;
        }
        return returnList;
    }

    //class not a factor, only checking good/evil
    public SuperRole[] createPlayerList(int numPlayers){
        int demonPos = createRandomList(1, 1, numPlayers)[0];

        SuperRole[] players = new SuperRole[numPlayers];

        players[0] = new FortuneTeller("Player", 0, numPlayers);
        for (int i = 1; i < numPlayers; i++) {
            if(i == demonPos){
                players[i] = new Imp("Imp", 1, numPlayers);
            }
            else {
                String playerName = "Player: " + i;
                players[i] = new Unknown(playerName, 0, numPlayers);
            }
        }
        return players;
    }

    public HashMap<String, Theory>[][] createTheories(SuperRole[] players){
        HashMap<String, Theory>[][] theories = new HashMap[players.length][Trait.values().length];
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                theories[i][j] = new HashMap<>();
            }
        }

        //create theories when they are found out
        for (int i = 0; i < players.length; i++) {
            theories[i][0].put("truth", new Theory(players[i], Trait.NAME, players[i].getPlayerName()));
//            theories[i][1].put("0", new Theory(players[i], Trait.ALIGNMENT, 0));
//            theories[i][1].put("1", new Theory(players[i], Trait.ALIGNMENT, 1));
//            theories[i][3].put("0", new Theory(players[i], Trait.CLASS, "Unknown"));
//            theories[i][3].put("1", new Theory(players[i], Trait.CLASS, "FortuneTeller"));
//            theories[i][3].put("2", new Theory(players[i], Trait.CLASS, "Imp"));
        }


        return theories;

    }

    public HashMap<String, Connection>[][] createConnections(SuperRole[] players){
        HashMap<String, Connection>[][] connections = new HashMap[players.length][Trait.values().length];

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                connections[i][j] = new HashMap<>();
            }
        }
        return connections;
    }

    public int hashCheckTheory(HashMap<String, Theory> theoryHashMap){
        int checkVal = 0;
        while (theoryHashMap.containsKey(String.valueOf(checkVal))){
            checkVal++;
        }
        return checkVal;
    }

    public int hashCheck(HashMap<String, Connection> connections){
        int checkVal = 0;
        while (connections.containsKey(String.valueOf(checkVal))){
            checkVal++;
        }
        return checkVal;
    }


    public int[] playGames(int numGames, int numPlayers){
        Random gameRand = new Random(10);


        int[] returnValues = new int[numGames];

        //play the games in here
        for (int i = 0; i < numGames; i++) {

            returnValues[i] = simpleGame(numPlayers);

        }

        return returnValues;
    }

    //runs a "game", every "turn" it compares the next players in sequence against eachother, this is without red herrings

    //need to ask what happens if a demon and a red herring are pinged on the same night, and check that the minions don't ping

    //need to double check that this code is functional
    public int simpleGame(int numPlayers){
        //in here run a single turn in one iteration
        int currentPlayer = 1;
        int nextPlayer = 2;
        int numTurns = 0;
        boolean demonPinged = false;

        SuperRole[] players = createPlayerList(numPlayers);
        HashMap<String, Theory>[][] theories = createTheories(players);
        HashMap<String, Connection>[][] connections = createConnections(players);
        while (true){
            numTurns++;

            if ((players[currentPlayer].getAlignment() == 0) && (players[nextPlayer].getAlignment() == 0)){
                theories[currentPlayer][1].put("truth", new Theory(players[currentPlayer], Trait.ALIGNMENT, 0));
                theories[nextPlayer][1].put("truth", new Theory(players[nextPlayer], Trait.ALIGNMENT, 0));
            }
            else if((players[currentPlayer].getAlignment() == 1) || (players[nextPlayer].getAlignment() == 1)){


                theories[currentPlayer][1].put("0", new Theory(players[currentPlayer], Trait.ALIGNMENT, 0));
                theories[currentPlayer][1].put("1", new Theory(players[currentPlayer], Trait.ALIGNMENT, 1));
                theories[nextPlayer][1].put("0", new Theory(players[nextPlayer], Trait.ALIGNMENT, 0));
                theories[nextPlayer][1].put("1", new Theory(players[nextPlayer], Trait.ALIGNMENT, 1));

                connections[currentPlayer][1].put("1", new Connection(theories[nextPlayer][1].get("0"), theories[currentPlayer][1].get("1"), 1));
                connections[nextPlayer][1].put("1", new Connection(theories[currentPlayer][1].get("0"), theories[nextPlayer][1].get("1"), 1));

            }
            for (int i = 0; i < players.length; i++) {
                for (Map.Entry<String, Connection> playerEntry : connections[i][1].entrySet()) {
                    for (int j = 0; j < players.length; j++) {
                        if(theories[j][1].containsKey("truth")){

                            if (theories[j][1].get("truth").isEqual(playerEntry.getValue().getTheory1())){
                                //demon found
                                return numTurns;
                            }
                        }
                    }

                }
            }

            if(currentPlayer == players.length - 1){
                currentPlayer = 1;
            }
            else {
                currentPlayer++;
            }
            if(nextPlayer == players.length - 1){
                nextPlayer = 1;
            }
            else {
                nextPlayer++;
            }

        }
    }

    public boolean writeToFile(int[] games, String fileName){
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            return false;
        }
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (int i = 0; i < games.length; i++) {
                myWriter.write(String.valueOf(games[i]) + ",");
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public void test(){
        int[] temp = playGames(10000, 7);
//        System.out.println(Arrays.toString(temp));
        writeToFile(temp, "output.txt");

    }
}
