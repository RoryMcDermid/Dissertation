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
import java.util.*;

public class Results {

    //test picking random pairs for demon possibilities for fortune tellers against going in sequence, both with and without red herrings

    //initialise (and save?) a setup of players
    //use that in a number of different layouts
    Random playerRand = new Random(1);

    //used to set where the demon and red herring are
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

//            returnValues[i] = singleStep(numPlayers);

            returnValues[i] = doubleStep(numPlayers);

        }

        return returnValues;
    }

    public String[] playGamesString(int numGames, int numPlayers){
        Random gameRand = new Random(10);


        String[] returnValues = new String[numGames];

        //play the games in here
        for (int i = 0; i < numGames; i++) {

            returnValues[i] = doubleStepHerring(numPlayers);

        }

        return returnValues;
    }

    //runs a "game", every "turn" it compares the next players in sequence against eachother, this is without red herrings

    //need to ask what happens if a demon and a red herring are pinged on the same night, and check that the minions don't ping

    //this code isn't functional
    public int simpleGame(int numPlayers){
        //in here run a single turn in one iteration
        int currentPlayer = 1;
        int nextPlayer = 2;
        int numTurns = 0;
        boolean demonPinged = false;

        SuperRole[] players = createPlayerList(numPlayers);
        HashMap<String, Theory>[][] theories = createTheories(players);
        HashMap<String, Connection>[][] connections = createConnections(players);
        System.out.println(players[0].getClassName());
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

    //code is working and functional, with no errors in 1000 games
    public int singleStep(int numPlayers){

        SuperRole[] players = createPlayerList(numPlayers);
        HashMap<String, Theory>[][] theories = createTheories(players);
        HashMap<String, Connection>[][] connections = createConnections(players);

        boolean demonFound = false;
        boolean firstStepDemon = false;
        int n = 1; //player 0 is the fortune teller
        int numberOfTurns = 0;

        while (!demonFound){
            numberOfTurns = numberOfTurns + 1;
            boolean currentLoopDemon = (players[n].getAlignment() == 1 || players[n + 1].getAlignment() == 1);

            if(!currentLoopDemon){//if there is no demon in the current pair
                if(firstStepDemon){
                    n = n - 1;
                    demonFound = true;
                    break;
                }
            }
            else {//there is a demon in the current pair
                if(firstStepDemon){
                    demonFound = true;
                    break;
                }
                if(n == 1){
                    firstStepDemon = true;
                }
                else {
                    n = n + 1;
                    demonFound = true;
                    break;
                }
            }
            n = n + 1;

        }

        if(Objects.equals(players[n].getClassName(), "Imp")){
            return numberOfTurns;
        }
        //returning -1 to show that the code got the wrong demon
        System.out.println(players[n].getClassName());
        return -1;
    }

    //code is working and functional, with no errors in 1000 games
    public int doubleStep(int numPlayers){

        SuperRole[] players = createPlayerList(numPlayers);
        HashMap<String, Theory>[][] theories = createTheories(players);
        HashMap<String, Connection>[][] connections = createConnections(players);

        int n = 1;
        int townsfolkNumber = -1;
        boolean firstStepDemon = false;
        boolean demonFound = false;
        int numberOfTurns = 0;

        while(!demonFound){
            numberOfTurns = numberOfTurns + 1;

            boolean currentLoopDemon = (players[n].getAlignment() == 1 || players[n + 1].getAlignment() == 1);
            if(!currentLoopDemon){//no demon this loop
                townsfolkNumber = n;
            }
            else{//demon this loop
                if(townsfolkNumber == -1){//this signifies that it is the first loop
                    if(players[n].getAlignment() == 1 || players[n + 2].getAlignment() == 1){//player n was the demon
                        numberOfTurns = numberOfTurns + 1;
                        demonFound = true;
                        break;
                    }
                    else{//player n wasn't the demon, so must be n + 1
                        numberOfTurns = numberOfTurns + 1;
                        n = n + 1;
                        demonFound = true;
                        break;
                    }
                }
                else{
                    if(players[n].getAlignment() == 1 || players[townsfolkNumber].getAlignment() == 1){//if player n was the demon
                        numberOfTurns = numberOfTurns + 1;
                        demonFound = true;
                        break;
                    }
                    else{//player n wasn't the demon, so must be n + 1
                        numberOfTurns = numberOfTurns + 1;
                        n = n + 1;
                        demonFound = true;
                        break;
                    }
                }
            }
            n = n + 2;
        }

        if(Objects.equals(players[n].getClassName(), "Imp")){
            return numberOfTurns;
        }
        //returning -1 to show that the code got the wrong demon
        System.out.println(players[n].getClassName());
        return -1;

    }

    //must have 4 players
    public String doubleStepHerring(int numPlayers){
        SuperRole[] players = createPlayerList(numPlayers);
        HashMap<String, Theory>[][] theories = createTheories(players);
        HashMap<String, Connection>[][] connections = createConnections(players);
        int redHerring = createRandomList(1, 0, numPlayers)[0]; //make someone the red herring
        int turns = 0;
        int[] foundIdAndLikelihood = new int[6];// goes [player ID, turn they were discovered, are they definately demon, player ID, turn they were discovered, are they definately demon]
        //the definately demon part is 0 if unsure, 1 if sure

        boolean pair = checkPair(0, players[0].getAlignment(), 1, players[1].getAlignment(), redHerring);
        turns++;

        if(pair){//demon possible
            pair = checkPair(2, players[2].getAlignment(), 3, players[3].getAlignment(), redHerring);
            turns++;

            if(pair){
                pair = checkPair(1, players[1].getAlignment(), 4, players[4].getAlignment(), redHerring);
                turns++;

                if(pair){//self not herring, player 1 possible demon
                    foundIdAndLikelihood[0] = 1;
                    foundIdAndLikelihood[1] = turns;
                    foundIdAndLikelihood[2] = 0;

                    pair = checkPair(0, players[0].getAlignment(), 2, players[2].getAlignment(), redHerring);
                    turns++;

                    if(pair){//player 2 is possible demon
                        foundIdAndLikelihood[3] = 2;
                        foundIdAndLikelihood[4] = turns;
                        foundIdAndLikelihood[5] = 0;
                        //loop should exit here
                    }
                    else{//player 3 is possible demon
                        foundIdAndLikelihood[3] = 3;
                        foundIdAndLikelihood[4] = turns;
                        foundIdAndLikelihood[5] = 0;
                        //loop should exit here

                    }
                }
                else{//self red herring
                    foundIdAndLikelihood[0] = 0;
                    foundIdAndLikelihood[1] = turns;
                    foundIdAndLikelihood[2] = 0;
                    pair = checkPair(1, players[1].getAlignment(), 2, players[2].getAlignment(), redHerring);
                    turns++;
                    if(pair){//player 2 is the demon
                        foundIdAndLikelihood[3] = 2;
                        foundIdAndLikelihood[4] = turns;
                        foundIdAndLikelihood[5] = 1;
                        //loop should exit here
                    }
                    else{//player 3 is the demon
                        foundIdAndLikelihood[3] = 3;
                        foundIdAndLikelihood[4] = turns;
                        foundIdAndLikelihood[5] = 1;
                        //loop should exit here
                    }

                }
            }
            else{//players 2 and 3 are not demons
                pair = checkPair(1, players[1].getAlignment(), 2, players[2].getAlignment(), redHerring);
                turns++;
                if(pair){//player 1 is demon candidate
                    foundIdAndLikelihood[0] = 1;
                    foundIdAndLikelihood[1] = turns;
                    foundIdAndLikelihood[2] = 0;

                    pair = checkPair(0, players[0].getAlignment(), 2, players[2].getAlignment(), redHerring);
                    turns++;
                    if(pair){//self red herring
                        foundIdAndLikelihood[3] = 0;
                        foundIdAndLikelihood[4] = turns;
                        foundIdAndLikelihood[5] = 0;
                        foundIdAndLikelihood[2] = 1;//we know player 1 is the demon
                        //loop should exit here
                    }
                    else{//self not red herring
                        int[] temp = checkRest(players,  redHerring, 0, turns, 4, 1, 0);
                        foundIdAndLikelihood[3] = temp[0];
                        foundIdAndLikelihood[4] = temp[1];
                        foundIdAndLikelihood[5] = temp[2];
                        //loop should exit here
                    }

                }
                else{//self red herring
                    int[] temp = checkRest(players,  redHerring, 1, turns, 4, 1, 1);
                    foundIdAndLikelihood[3] = temp[0];
                    foundIdAndLikelihood[4] = temp[1];
                    foundIdAndLikelihood[5] = temp[2];
                    //loop should exit here
                }

            }
        }
        else{//self and player 1 are not demons
            int[] temp = checkRest(players,  redHerring, 0, turns, 2, 2, 0);
            foundIdAndLikelihood = temp;
        }

        String returnValue = String.valueOf(foundIdAndLikelihood[0]);

        for (int i = 1; i < foundIdAndLikelihood.length; i++) {
            returnValue = returnValue + ":" + String.valueOf(foundIdAndLikelihood[i]);
        }

        return returnValue;

    }

    //selfGood is 0 if the player is good, to add to return values 2 or 5 for non confirmed demon
    //confirmed good is the index of a confirmed good player
    public int[] checkRest(SuperRole[] players, int redHerring, int confirmedGood, int turns, int nextPlayerToCheck, int numToCheck, int selfGood){

        int[] returnValues = new int[numToCheck * 3];

        int temp = 0;//used to write to returnValues

        while (true){
            if(nextPlayerToCheck + 1 == players.length){ //if there are not enough remaining players to check a full pair
                returnValues[(temp * 3)] = nextPlayerToCheck;
                returnValues[(temp * 3) + 1] = turns;
                returnValues[(temp * 3) + 2] = selfGood;
                return returnValues;
            }
            boolean pair = checkPair(nextPlayerToCheck, players[nextPlayerToCheck].getAlignment(), nextPlayerToCheck + 1, players[nextPlayerToCheck + 1].getAlignment(), redHerring);
            turns++;

            if(pair){//one is a demon
                pair = checkPair(nextPlayerToCheck, players[nextPlayerToCheck].getAlignment(), confirmedGood, players[confirmedGood].getAlignment(), redHerring);
                turns++;

                if(pair){//nextPlayerToCheck is demon candidate
                    returnValues[(temp * 3)] = nextPlayerToCheck;
                }
                else{//nextPlayerToCheck is demon candidate + 1
                    returnValues[(temp * 3)] = nextPlayerToCheck + 1;
                }
                returnValues[(temp * 3) + 1] = turns;
                returnValues[(temp * 3) + 2] = selfGood;
                if(numToCheck == temp + 1){
                    return returnValues;
                }
                else{
                    temp = temp + 1;
                }
            }
            nextPlayerToCheck = nextPlayerToCheck + 2;
        }


    }

    public boolean checkPair(int playerAId, int playerAAlignment, int playerBId, int playerBAlignment, int redHerring){
        if (playerAId == redHerring || playerBId == redHerring){
            return true;
        }
        if (playerAAlignment == 1 || playerBAlignment == 1){
            return true;
        }
        return false;
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
            myWriter.write(String.valueOf(games[0]));
            for (int i = 0; i < games.length; i++) {
                myWriter.write("," + String.valueOf(games[i]));
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean writeToFileString(String[] games, String fileName){
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
            myWriter.write(games[0]);
            for (int i = 0; i < games.length; i++) {
                myWriter.write("," + games[i]);
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //playGames is for the simple Single and Double step, playGamesString is for the complex Double step
    public void test(){
        int[] temp = playGames(10000, 9);
//        String[] temp = playGamesString(10000, 7);
        System.out.println(Arrays.toString(temp));
        writeToFile(temp, "output.txt");
//        writeToFileString(temp, "output.txt");


    }
}
