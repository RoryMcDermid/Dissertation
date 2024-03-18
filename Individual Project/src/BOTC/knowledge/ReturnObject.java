package BOTC.knowledge;

import java.util.HashMap;

public class ReturnObject {


    HashMap<String, Connection> returnValues;
    HashMap<String, Connection>[][] connections;

    public ReturnObject(HashMap<String, Connection> returnValuesIn, HashMap<String, Connection>[][] connectionsIn){
        returnValues = returnValuesIn;
        connections = connectionsIn;
    }

    public HashMap<String, Connection> getReturnValues() {
        return returnValues;
    }

    public HashMap<String, Connection>[][] getConnections() {
        return connections;
    }
}

