package BOTC;

import BOTC.Roles.*;
import BOTC.knowledge.Connection;
import BOTC.knowledge.Theory;

public class CreateGame {
    GetInput input = new GetInput();

    public SuperRole[] CreateNewGame() {
        // args will be the number of intended players (currently 7)

        // SuperRole[] players = new SuperRole[Integer.parseInt(args[0])];
        // hardcoded for now



        int numPlayers = Integer.parseInt(input.readIn("int", "How many players are in your game"));
        SuperRole[] players = new SuperRole[numPlayers];

        String[] names = new String[numPlayers];

        names[0] = input.readIn("String", "Please enter your name");

        for (int i = 1; i < names.length; i++) {
            names[i] = input.readIn("String", "Please enter the name of player " + (i+1) + "");
        }

        assign(players, names, numPlayers);


        return players;

    }

    public SuperRole[] assign(SuperRole[] players, String[] player_names, int numPlayers) {

        ApplyRole applyRole = new ApplyRole();

        Theory[] names = new Theory[numPlayers];

        int self_alignment = Integer.parseInt(input.readIn("alignment", "What is your alignment"));

        ClassName classNameUser = ClassName.valueOf(input.readIn("classname", "What is your class"));

        players[0] = applyRole.giveRole(player_names[0], self_alignment, numPlayers, classNameUser);

        for (int i = 1; i < players.length; i++) {
            int player_alignment = Integer.parseInt(input.readIn("alignment", "What is player " + (i+1) + "'s alignment"));
            ClassName classNamePlayer = ClassName.valueOf(input.readIn("classname", "What is player " + (i+1) + "'s class, if not known, enter \"UNKNOWN\" "));
            players[i] = applyRole.giveRole(player_names[i], player_alignment, numPlayers, classNamePlayer);
        }

        // hardcoded for now


        for (int i = 0; i < numPlayers; i++) {
            names[i] = new Theory(players[i], Trait.NAME, players[i].getPlayerName());
        }

        for (int i = 0; i < numPlayers; i++) {
            names[i].confirm();
        }

        // hardcoded as 7 for now
        for (int i = 0; i < numPlayers; i++) {
            players[i].setBaseFacts(numPlayers, i, names);
        }

        return players;
    }

}
