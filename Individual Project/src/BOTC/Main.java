package BOTC;

import BOTC.Roles.SuperRole;
import BOTC.knowledge.Connection;
import BOTC.knowledge.SetupConnections;
import BOTC.knowledge.SetupTheories;
import BOTC.knowledge.Theory;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        CreateGame play = new CreateGame();

        SuperRole[] players = play.CreateNewGame();


        SetupTheories theorySetup = new SetupTheories();

        HashMap<String, Theory>[][] theories = theorySetup.activate(players);


        SetupConnections connectionSetup = new SetupConnections();

        HashMap<String, Connection>[][] connections =  connectionSetup.createConnections(players, theories);


    }
}
