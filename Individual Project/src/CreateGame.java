import Roles.*;
import knowledge.Theory;

public class CreateGame {

    public void CreateGame(String[] args){
        //args will be the number of intended players (currently 7)

        //SuperRole[] players = new SuperRole[Integer.parseInt(args[0])];
        //hardcoded for now
        SuperRole[] players = new SuperRole[7];

        assign(players);


        System.out.println(players[0].getPlayerName());


        Theory test = new Theory(players[0], "playerName", players[0].getPlayerName());

        System.out.println(test.checkTruth());

        //need some way to easily setup all of the basic truths (facts about self), as well as having some way to compare them


    }

    public SuperRole[] assign(SuperRole[] players){

        //hardcoded for now
        players[0] = new Roles.Townsfolk.Chef("John", true);
        players[1] = new Roles.Minion.Spy("Alan", false);
        players[2] = new Roles.Demon.Imp("Horatio", false);
        players[3] = new Roles.Townsfolk.Soldier("Leah", true);
        players[4] = new Roles.Townsfolk.Virgin("Jim", true);
        players[5] = new Roles.Townsfolk.Empath("Bob", true);
        players[6] = new Roles.Townsfolk.Washerwoman("Tim", true);

        return players;
    }
}
