package BOTC;

import BOTC.Roles.Demon.Imp;
import BOTC.Roles.SuperRole;
import BOTC.Roles.Townsfolk.Chef;
import BOTC.knowledge.Connection;
import BOTC.knowledge.ReturnObject;
import BOTC.knowledge.Theory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {


    public void opposeTest(){

        SuperRole[] testPlayers = new SuperRole[2];

        testPlayers[0] = new Chef("Bob", 0, 2);
        testPlayers[1] = new Imp("Jim", 1, 2);



        HashMap<String, Theory>[][] testTheories = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testTheories[i][j] = new HashMap<>();
            }
        }

        testTheories[0][0].put("truth", new Theory(testPlayers[0], Trait.NAME, testPlayers[0].getPlayerName()));
        testTheories[1][0].put("truth", new Theory(testPlayers[1], Trait.NAME, testPlayers[1].getPlayerName()));

        testTheories[0][1].put("0", new Theory(testPlayers[0], Trait.ALIGNMENT, 0));
        testTheories[0][1].put("1", new Theory(testPlayers[0], Trait.ALIGNMENT, 1));

        testTheories[0][3].put("0", new Theory(testPlayers[0], Trait.CLASS, "Chef"));
        testTheories[0][3].put("1", new Theory(testPlayers[0], Trait.CLASS, "Imp"));


        HashMap<String, Connection>[][] testConnections = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testConnections[i][j] = new HashMap<>();
            }
        }



        //if good then chef
        testConnections[0][3].put("0", new Connection(testTheories[0][1].get("0"), testTheories[0][3].get("0"), 1));

        //if chef then good
        testConnections[0][1].put("0", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("0"), 1));

        //if evil then imp
        testConnections[0][3].put("1", new Connection(testTheories[0][1].get("1"), testTheories[0][3].get("1"), 1));

        //if evil then not imp
        testConnections[0][3].put("2", new Connection(testTheories[0][1].get("1"), testTheories[0][3].get("1"), 2));





        //basics initialised

        System.out.println("Check 'if good then chef, if evil then imp' are incongruent");
        System.out.println(testConnections[0][3].get("0").areIncongruent(testConnections[0][3].get("1")));


        System.out.println("Check 'if good then chef, if good then chef' are not incongruent");
        System.out.println(testConnections[0][3].get("0").areIncongruent(testConnections[0][3].get("0")));

        System.out.println("Check 'if evil then imp, if evil then not imp' are incongruent");
        System.out.println(testConnections[0][3].get("0").areIncongruent(testConnections[0][3].get("2")));


        System.out.println("Check 'if good then chef, if chef then good' are not incongruent");
        System.out.println(testConnections[0][3].get("0").areIncongruent(testConnections[0][1].get("0")));

        //checks seem to function up to here

    }

    public void linkTest(){

        SuperRole[] testPlayers = new SuperRole[2];

        testPlayers[0] = new Chef("Bob", 0, 2);
        testPlayers[1] = new Imp("Jim", 1, 2);



        HashMap<String, Theory>[][] testTheories = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testTheories[i][j] = new HashMap<>();
            }
        }

        testTheories[0][0].put("truth", new Theory(testPlayers[0], Trait.NAME, testPlayers[0].getPlayerName()));
        testTheories[1][0].put("truth", new Theory(testPlayers[1], Trait.NAME, testPlayers[1].getPlayerName()));

        testTheories[0][1].put("0", new Theory(testPlayers[0], Trait.ALIGNMENT, 0));
        testTheories[1][1].put("0", new Theory(testPlayers[1], Trait.ALIGNMENT, 1));

        testTheories[0][3].put("0", new Theory(testPlayers[0], Trait.CLASS, "Chef"));
        testTheories[1][3].put("0", new Theory(testPlayers[1], Trait.CLASS, "Imp"));


        HashMap<String, Connection>[][] testConnections = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testConnections[i][j] = new HashMap<>();
            }
        }

        //if good then chef
        testConnections[0][3].put("0", new Connection(testTheories[0][1].get("0"), testTheories[0][3].get("0"), 1));

        //if chef then good
        testConnections[0][1].put("0", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("0"), 1));

        //if evil then imp
        testConnections[1][3].put("0", new Connection(testTheories[1][1].get("0"), testTheories[1][3].get("0"), 1));

        //if chef then imp
        testConnections[1][3].put("1", new Connection(testTheories[0][3].get("0"), testTheories[1][3].get("0"), 1));

        HashMap<String, Connection> returnValues = new HashMap<>();

        ReturnObject getSubs = testConnections[0][3].get("0").getSubsequent(testConnections, returnValues, 0, 3, "0");

        returnValues = getSubs.getReturnValues();

        System.out.println(returnValues.get("0").getTheory1().getPlayer().getPlayerName() + "," + returnValues.get("0").getTheory1().getVariable() + "," + returnValues.get("0").getTheory2().getPlayer().getPlayerName() + "," + returnValues.get("0").getTheory2().getVariable());
        System.out.println(returnValues.get("1").getTheory1().getPlayer().getPlayerName() + "," + returnValues.get("1").getTheory1().getVariable() + "," + returnValues.get("1").getTheory2().getPlayer().getPlayerName() + "," + returnValues.get("1").getTheory2().getVariable());
        System.out.println(returnValues.get("2").getTheory1().getPlayer().getPlayerName() + "," + returnValues.get("2").getTheory1().getVariable() + "," + returnValues.get("2").getTheory2().getPlayer().getPlayerName() + "," + returnValues.get("2").getTheory2().getVariable());

    }

    public void subsequentFixText(){

        SuperRole[] testPlayers = new SuperRole[2];

        testPlayers[0] = new Chef("Bob", 0, 2);
        testPlayers[1] = new Imp("Jim", 1, 2);



        HashMap<String, Theory>[][] testTheories = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testTheories[i][j] = new HashMap<>();
            }
        }

        testTheories[0][0].put("truth", new Theory(testPlayers[0], Trait.NAME, testPlayers[0].getPlayerName()));
        testTheories[1][0].put("truth", new Theory(testPlayers[1], Trait.NAME, testPlayers[1].getPlayerName()));

        testTheories[0][1].put("0", new Theory(testPlayers[0], Trait.ALIGNMENT, 0));
        testTheories[0][1].put("1", new Theory(testPlayers[0], Trait.ALIGNMENT, 1));
        testTheories[1][1].put("0", new Theory(testPlayers[1], Trait.ALIGNMENT, 1));

        testTheories[0][3].put("0", new Theory(testPlayers[0], Trait.CLASS, "Chef"));
        testTheories[0][3].put("1", new Theory(testPlayers[0], Trait.CLASS, "Imp"));
        testTheories[1][3].put("0", new Theory(testPlayers[1], Trait.CLASS, "Imp"));


        HashMap<String, Connection>[][] testConnections = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testConnections[i][j] = new HashMap<>();
            }
        }

        //if good then chef
        testConnections[0][3].put("0", new Connection(testTheories[0][1].get("0"), testTheories[0][3].get("0"), 1));

        //if chef then good
        testConnections[0][1].put("0", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("0"), 1));

        //if chef then evil
        testConnections[0][1].put("1", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("1"), 1));

//        testConnections[0][3].get("0").print();
//        testConnections[0][1].get("0").print();
//        testConnections[0][1].get("1").print();

        HashMap<String, Connection> returnValues = new HashMap<>();

        ReturnObject getSubs = testConnections[0][3].get("0").getSubsequent(testConnections, returnValues, 0, 3, "0");

        getSubs.getReturnValues().get("0").print();
        getSubs.getReturnValues().get("1").print();
        getSubs.getReturnValues().get("2").print();

    }

    public void areAllCongruent(){

        SuperRole[] testPlayers = new SuperRole[2];

        testPlayers[0] = new Chef("Bob", 0, 2);
        testPlayers[1] = new Imp("Jim", 1, 2);



        HashMap<String, Theory>[][] testTheories = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testTheories[i][j] = new HashMap<>();
            }
        }

        //player names
        testTheories[0][0].put("truth", new Theory(testPlayers[0], Trait.NAME, testPlayers[0].getPlayerName()));
        testTheories[1][0].put("truth", new Theory(testPlayers[1], Trait.NAME, testPlayers[1].getPlayerName()));

        //player 0 is good, and evil, player 2 is evil
        testTheories[0][1].put("0", new Theory(testPlayers[0], Trait.ALIGNMENT, 0));
        testTheories[0][1].put("1", new Theory(testPlayers[0], Trait.ALIGNMENT, 1));
        testTheories[1][1].put("0", new Theory(testPlayers[1], Trait.ALIGNMENT, 1));

        testTheories[0][3].put("0", new Theory(testPlayers[0], Trait.CLASS, "Chef"));
        testTheories[0][3].put("1", new Theory(testPlayers[0], Trait.CLASS, "Imp"));
        testTheories[1][3].put("0", new Theory(testPlayers[1], Trait.CLASS, "Imp"));


        HashMap<String, Connection>[][] testConnections = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testConnections[i][j] = new HashMap<>();
            }
        }

        //if good then chef
        testConnections[0][3].put("0", new Connection(testTheories[0][1].get("0"), testTheories[0][3].get("0"), 1));

        //if chef then good
        testConnections[0][1].put("0", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("0"), 1));

        //if evil then imp
        testConnections[0][3].put("1", new Connection(testTheories[0][1].get("1"), testTheories[0][3].get("1"), 1));

        HashMap<String, Connection> returnValues = new HashMap<>();

//        testConnections[0][3].get("0").print();
//        testConnections[0][3].get("1").print();
//        System.out.println(testConnections[0][3].get("0").areIncongruent(testConnections[0][3].get("1")));


        boolean test = testConnections[0][1].get("0").areSubsequentCongruent(testConnections[0][3].get("1"), testConnections, 0, 1, "0", 0, 3, "1");

        System.out.println(test);


    }

    public void shortTest(){
        SuperRole[] testPlayers = new SuperRole[2];

        testPlayers[0] = new Chef("Bob", 0, 2);
        testPlayers[1] = new Imp("Jim", 1, 2);



        HashMap<String, Theory>[][] testTheories = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testTheories[i][j] = new HashMap<>();
            }
        }

        testTheories[0][0].put("truth", new Theory(testPlayers[0], Trait.NAME, testPlayers[0].getPlayerName()));
        testTheories[1][0].put("truth", new Theory(testPlayers[1], Trait.NAME, testPlayers[1].getPlayerName()));

        testTheories[0][1].put("0", new Theory(testPlayers[0], Trait.ALIGNMENT, 0));
        testTheories[0][1].put("1", new Theory(testPlayers[0], Trait.ALIGNMENT, 1));
        testTheories[1][1].put("0", new Theory(testPlayers[1], Trait.ALIGNMENT, 1));

        testTheories[0][3].put("0", new Theory(testPlayers[0], Trait.CLASS, "Chef"));
        testTheories[0][3].put("1", new Theory(testPlayers[0], Trait.CLASS, "Imp"));
        testTheories[1][3].put("0", new Theory(testPlayers[1], Trait.CLASS, "Imp"));


        HashMap<String, Connection>[][] testConnections = new HashMap[testPlayers.length][Trait.values().length];

        for (int i = 0; i < testPlayers.length; i++) {
            for (int j = 0; j < Trait.values().length; j++) {
                testConnections[i][j] = new HashMap<>();
            }
        }

        //if good then chef
        testConnections[0][3].put("0", new Connection(testTheories[0][1].get("0"), testTheories[0][3].get("0"), 1));

        //if chef then good
        testConnections[0][1].put("0", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("0"), 1));

        //if chef then evil
        testConnections[0][1].put("1", new Connection(testTheories[0][3].get("0"), testTheories[0][1].get("1"), 1));


        System.out.println(testConnections[0][3].get("0").contains(testTheories[0][1].get("1")));
    }
}
