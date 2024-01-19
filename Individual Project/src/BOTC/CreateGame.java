package BOTC;

import BOTC.Roles.*;
import BOTC.knowledge.Connection;
import BOTC.knowledge.Theory;

public class CreateGame {

    public void CreateGame(String[] args) {
        // args will be the number of intended players (currently 7)

        // SuperRole[] players = new SuperRole[Integer.parseInt(args[0])];
        // hardcoded for now
        SuperRole[] players = new SuperRole[7];

        String names[] = new String[7];

        names[0] = "John";
        names[1] = "Alan";
        names[2] = "Horatio";
        names[3] = "Leah";
        names[4] = "Jim";
        names[5] = "Bob";
        names[6] = "Tim";

        assign(players, names);

        System.out.println(players[0].getPlayerName());

        Theory tTest = new Theory(players[0], Trait.NAME, players[0].getPlayerName());

        System.out.println(tTest.checkTruth());

        Theory tTest2 = new Theory(players[0], Trait.Class, players[0].getClassName());

        // need some way to easily setup all of the basic truths (facts about self), as
        // well as having some way to compare them

        Connection cTest = new Connection(tTest, tTest2, 2);

        System.out.println(cTest.isConnectionCorrect());

        System.out.println(players[0].getFact(players[2].getPlayerName(), Trait.NAME).getPlayer().getPlayerName());

    }

    public SuperRole[] assign(SuperRole[] players, String[] player_names) {

        Theory[] names = new Theory[7];

        // hardcoded for now
        players[0] = new BOTC.Roles.Townsfolk.Chef(player_names[0], true, 7);
        players[1] = new BOTC.Roles.Minion.Spy(player_names[1], false, 7);
        players[2] = new BOTC.Roles.Demon.Imp(player_names[2], false, 7);
        players[3] = new BOTC.Roles.Townsfolk.Soldier(player_names[3], true, 7);
        players[4] = new BOTC.Roles.Townsfolk.Virgin(player_names[4], true, 7);
        players[5] = new BOTC.Roles.Townsfolk.Empath(player_names[5], true, 7);
        players[6] = new BOTC.Roles.Townsfolk.Washerwoman(player_names[6], true, 7);

        for (int i = 0; i < names.length; i++) {
            names[i] = new Theory(players[i], Trait.NAME, players[i].getPlayerName());
        }

        for (int i = 0; i < 7; i++) {
            names[i].confirm();
        }

        // hardcoded as 7 for now
        for (int i = 0; i < 7; i++) {
            players[i].setBaseFacts(7, i, names);
        }

        return players;
    }

}
