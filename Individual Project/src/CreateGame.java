import Roles.*;
import knowledge.Connection;
import knowledge.Theory;

public class CreateGame {

    public void CreateGame(String[] args){
        //args will be the number of intended players (currently 7)

        //SuperRole[] players = new SuperRole[Integer.parseInt(args[0])];
        //hardcoded for now
        SuperRole[] players = new SuperRole[7];

        assign(players);


        System.out.println(players[0].getPlayerName());


        Theory tTest = new Theory(players[0], "playerName", players[0].getPlayerName());

        System.out.println(tTest.checkTruth());

        Theory tTest2 = new Theory(players[0], "class", players[0].getClassName());

        //need some way to easily setup all of the basic truths (facts about self), as well as having some way to compare them

        Connection cTest = new Connection(tTest, tTest2, 2);

        System.out.println(cTest.isConnectionCorrect());


    }

    public SuperRole[] assign(SuperRole[] players){

        //hardcoded for now
        players[0] = new Roles.Townsfolk.Chef("John", true, 7);
        players[1] = new Roles.Minion.Spy("Alan", false, 7);
        players[2] = new Roles.Demon.Imp("Horatio", false, 7);
        players[3] = new Roles.Townsfolk.Soldier("Leah", true, 7);
        players[4] = new Roles.Townsfolk.Virgin("Jim", true, 7);
        players[5] = new Roles.Townsfolk.Empath("Bob", true, 7);
        players[6] = new Roles.Townsfolk.Washerwoman("Tim", true, 7);

        return players;
    }
}
