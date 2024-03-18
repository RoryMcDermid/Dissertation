package BOTC.knowledge;

import BOTC.Trait;

import java.util.*;

public class Connection {

    private Theory theory1;

    private Theory theory2;

    private int relation; //the relation is 0 = unrelated, 1 = theory1 implies theory2 is true, 2 = theory1 implies theory2 is false

    private boolean confirmed; //has this been confirmed by the person who knows it (starts as false)

    public Connection(Theory theory1in, Theory theory2in, int relationin ){

        theory1 = theory1in;

        theory2 = theory2in;

        relation = relationin;

        confirmed = false;

    }

    //does this connection imply any other connections
    public boolean[] checkImplies(Connection[] connections){

        boolean[] implies = new boolean[connections.length];

        for (int i = 0; i < connections.length; i++){
            implies[i] = connections[i].getTheory1() == theory2;
        }

        return implies;
    }

    //is this connection implied by any other connections
    public boolean[] checkImplied(Connection[] connections){

        boolean[] implied = new boolean[connections.length];

        for (int i = 0; i < connections.length; i++){
            implied[i] = connections[i].getTheory2() == theory1;
        }
        return implied;
    }

    public Theory getTheory1() {
        return theory1;
    }

    public Theory getTheory2() {
        return theory2;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public int getRelation(){
        return relation;
    }

    public void setRelationAndConfirmed(int relation, boolean confirmed) {
        this.relation = relation;
        this.confirmed = confirmed;
    }

    //returns this connection, but has the theories switched, so if this object says that a implies b, then the returned function says that b implies a
    public Connection equivalance(){
        return new Connection(theory2, theory1, relation);
    }

    //used to check if this connection is correct, for testing purposes
    public boolean isConnectionCorrect(){



        boolean firstTheory = theory1.checkTruth();
        boolean secondTheory = theory2.checkTruth();

        switch(relation){
            case(1):
                //if they are both true or both false then a implies b so it is true
                return firstTheory == secondTheory;
            case(2):
                //if a is true, then b must be false for the connection to be correct, and same with a being false, and b being true
                return firstTheory != secondTheory;
        }
        //if there is no connection (relation is 0) or relation is outwith the bounds set, then there is no connection
        return false;


    }

    //returns true if the secondConnection cannot exist along with this one
    //instances where this is the case:
    //both have the same theory 2, but different values (Bob is an imp, Bob is a chef)
    //both have the same theory 2 and values, but different relations (A implies Bob is an imp, B implies Bob isn't an imp)
    public boolean areIncongruent(Connection secondConnection){
        if((theory2.getPlayer() == secondConnection.getTheory2().getPlayer()) && (theory2.getVariable() == secondConnection.getTheory2().getVariable())){
            //different values, this works with basic testing
            if(theory2.getValue() != secondConnection.getTheory2().getValue()){
                return true;
            }

            //this one below may not be correct
            //same value on both theories, different relations
            if((theory1.getValue() == secondConnection.getTheory1().getValue()) && (theory2.getValue() == secondConnection.getTheory2().getValue()) && (relation != secondConnection.getRelation())){
                return true;
            }
        }
        return false;
    }

    //needs to get all connections linked to this one and return it
    //go through all connections, check their theory1 for this connection's theory2, then run this on all of those new connections
    //pop this connection/connections to be looked at from the connections passed to stop endless loops?
    public ReturnObject getSubsequent(HashMap<String, Connection>[][] connectionsIn, HashMap<String, Connection> returnValues, int selfX, int selfY, String selfKey){

        HashMap<String, Connection>[][] connections = deepCopy(connectionsIn);


        returnValues.put(String.valueOf(hashCheck(returnValues)), connections[selfX][selfY].get(selfKey));
        connections[selfX][selfY].remove(selfKey);


        for (int i = 0; i < connections.length; i++) {
            for (int j = 0; j < connections[i].length; j++) {
                Set<Map.Entry<String, Connection>> entrySet = new HashSet<>(connections[i][j].entrySet());

                for (Map.Entry<String, Connection> entry : entrySet) {
                    if (entry.getValue().getTheory1() == theory2) {

//                        returnValues.put(String.valueOf(hashCheck(returnValues)), entry.getValue());
//                        entry.getValue().print();
                        // Remove the entry from the original map

//                        connections[i][j].remove(entry.getKey());

                        ReturnObject getSubs = entry.getValue().getSubsequent(connections, returnValues, i, j, entry.getKey());

                        returnValues = getSubs.getReturnValues();
                        connections = getSubs.getConnections();
                    }
                }

            }
        }

        return new ReturnObject(returnValues, connections);
    }


    //what about when a connection implies that the next is false? Figure out example
    //returns true if all are congruent
    public boolean areSubsequentCongruent(Connection secondConnection, HashMap<String, Connection>[][] connections, int x1, int y1, String key1, int x2, int y2, String key2){
        HashMap<String, Connection> returnValues = new HashMap<>();
        HashMap<String, Connection> selfSubs = getSubsequent(connections, returnValues, x1, y1, key1).getReturnValues();
        HashMap<String, Connection> secondSubs = secondConnection.getSubsequent(connections, returnValues, x2, y2, key2).getReturnValues();


        for (Map.Entry<String, Connection> selfEntry : selfSubs.entrySet()){
//            selfEntry.getValue().print();
            for (Map.Entry<String, Connection> secondEntry : secondSubs.entrySet()){
//                secondEntry.getValue().print();
                if(selfEntry.getValue().areIncongruent(secondEntry.getValue())){
                    System.out.println(selfEntry.getValue().getTheory1().getVariable() + ":" + selfEntry.getValue().getTheory1().getValue() + "," + selfEntry.getValue().getTheory2().getVariable() + ":" + selfEntry.getValue().getTheory2().getValue());
                    System.out.println(secondEntry.getValue().getTheory1().getVariable() + ":" + secondEntry.getValue().getTheory1().getValue() + "," + secondEntry.getValue().getTheory2().getVariable() + ":" + secondEntry.getValue().getTheory2().getValue());
                    return false;
                }
            }
        }
        return true;
    }

    private int hashCheck(HashMap<String, Connection> connections){
        int checkVal = 0;
        while (connections.containsKey(String.valueOf(checkVal))){
            checkVal++;
        }
        return checkVal;
    }

    private HashMap<String, Connection>[][] deepCopy(HashMap<String, Connection>[][] original) {
        HashMap<String, Connection>[][] copy = new HashMap[original.length][];

        for (int i = 0; i < original.length; i++) {
            copy[i] = new HashMap[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = new HashMap<>(original[i][j]);
            }
        }

        return copy;
    }

    public void print(){
        theory1.print();
        theory2.print();
    }

    public boolean contains(Theory input){
        if((theory1 == input) || (theory2 == input)){
            return true;
        }
        return false;
    }

}
