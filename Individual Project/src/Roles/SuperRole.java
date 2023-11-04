package Roles;

import knowledge.Connection;
import knowledge.Theory;

import java.util.ArrayList;

//Roles can be changed, e.g. imp can kill themselves to switch demonhood, need some way to transfer statistics to other role
public abstract class SuperRole {

    private String playerName;

    //True if they are good (townsfolk or outsider), False if they are evil (minion or demon)
    private Boolean alignment;

    //True if alive, False if dead
    private Boolean alive;

    //knowledge, will want seperate storage? maybe multiple knowledge bases (things that are known to be true/false, known logic links, etc)
    //maybe have each knowledge list as the same size, with empty values for each player
    private ArrayList<Theory> knowledge = new ArrayList<Theory>();

    private ArrayList<Connection> connections = new ArrayList<Connection>();


    public SuperRole(String playerNameIn, Boolean alignmentIn, int playerCount){

        playerName = playerNameIn;
        alignment = alignmentIn;
        alive = true;

        for(int i = 0; i < (playerCount * 3); i++){
            knowledge.add(new Theory(null, "", ""));
        }

    }


    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String playerNameIn){
        playerName = playerNameIn;
    }

    public Boolean getAlignment(){
        return alignment;
    }

    public void setAlignment(Boolean alignmentIn){
        alignment = alignmentIn;
    }

    public Boolean getAlive(){
        return alive;
    }

    public void setAlive(Boolean aliveIn){
        alive = aliveIn;
    }


    public abstract String getClassName();

    //to set a class, will just need to use a (x.getPlayerName(), x.getAlignment()) in the constructors


    //no need for overload
//    public boolean checkFact(String Variable, String Value){
//
//        return false;
//    }
//
//    public boolean checkFact(String Variable, Boolean Value){
//
//        switch (Variable){
//
//    }

    public boolean checkFact(String Variable, Object Value) {
        switch (Variable){
            case "playerName":
                return Value.equals(playerName);
            //getClassName should return the same as the name of the file/object of the given class
            case "class":
                return Value.equals(this.getClassName());
            case "alignment":
                return Value == alignment;
            case "alive":
                return Value == alive;
        }
        return false;
    }


}
