package BOTC.Roles;

import BOTC.Trait;
import BOTC.knowledge.Connection;
import BOTC.knowledge.Theory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//BOTC.Roles can be changed, e.g. imp can kill themselves to switch demonhood, need some way to transfer statistics to other role
public abstract class SuperRole {

    private String playerName;

    //True if they are good (townsfolk or outsider), False if they are evil (minion or demon)
    private Boolean alignment;

    //True if alive, False if dead
    private Boolean alive;

    //BOTC.knowledge, will want seperate storage? maybe multiple BOTC.knowledge bases (things that are known to be true/false, known logic links, etc)
    //maybe have each BOTC.knowledge list as the same size, with empty values for each player
    private HashMap<String, HashMap<Trait, Theory>> knowledge;

    //sort connections like BOTC.knowledge, with the sorted by value being the second theory (the value that would be set if the connection proved correct)
    private HashMap<String, HashMap<Trait, Connection>> connections;


    public SuperRole(String playerNameIn, Boolean alignmentIn, int playerCount){

        playerName = playerNameIn;
        alignment = alignmentIn;
        alive = true;

        //each player has an individually stored arraylist for each player, far easier to search for specific info
        knowledge = new HashMap<String, HashMap<Trait, Theory>>(playerCount);
        //need to find some better way of setting the size
        connections = new HashMap<String, HashMap<Trait, Connection>>(playerCount);

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

    //to set class, will just need to use a (x.getPlayerName(), x.getAlignment()) in the constructors (for changing class)


    public boolean checkFact(Trait Variable, Object Value) {
        switch (Variable){
            case NAME:
                return Value.equals(playerName);
            //getClassName should return the same as the name of the file/object of the given class
            case Class:
                return Value.equals(this.getClassName());
            case ALIGNMENT:
                return Value == alignment;
            case ALIVE:
                return Value == alive;
        }
        return false;
    }

    public Theory getFact(String PlayerNameIn, Trait Variable){

//      Is there any way to do this with switch cases that would be more efficient
        Theory temp = knowledge.get(PlayerNameIn).get(Variable);
        return temp;
    }

    public void setBaseFacts(int playerCount, int selfIndex, Theory[] names){

        for (int i = 0; i < playerCount; i++) {
            //Initialise the knowledge list with every player's names
            HashMap<Trait, Theory> info = new HashMap<Trait, Theory>();
            info.put(Trait.NAME, names[i]);
            knowledge.put(names[i].getValue() + "", info);

            //Initialise the connections list as empty with a spot for each player
            HashMap<Trait, Connection> empty = new HashMap<Trait, Connection>();
            connections.put(names[i].getValue() + "", empty);
        }

        Theory selfAlignment = new Theory(this, Trait.ALIGNMENT, alignment);
        selfAlignment.confirm();

        Theory selfAlive = new Theory(this, Trait.ALIVE, alive);
        selfAlive.confirm();

        Theory selfClass = new Theory(this, Trait.Class, this.getClassName());
        selfClass.confirm();

        //this will be for adding the values of self
        knowledge.get(playerName).put(Trait.ALIGNMENT, selfAlignment);
        knowledge.get(playerName).put(Trait.ALIVE, selfAlive);
        knowledge.get(playerName).put(Trait.Class, selfClass);


    }

    public void makeConnection(Theory theory1, Theory theory2, int relation){
        //Should it sort by theory 2 as the value that is set when it is confirmed, or by theory1 as the value that sets it?
        //Might have to have connections stored in a list as there could be multiple sources 
        connections.get(theory2.getPlayer().getPlayerName()).put(theory2.getVariable(), new Connection(theory1, theory2, relation));
    }

    public void confirmConnection(Theory theoryIn){
//        connections.get(theoryIn.getPlayer().getPlayerName()).get(theoryIn.getVariable());
    }

    //hashIn is for other classes overloading, it will normally be empty. It will be made using <Trait, Theory>
//    public HashMap makeGrimoire(HashMap hashIn){
//        hashIn.put(Trait.NAME, new Theory(this, Trait.NAME, playerName));
//        hashIn.put(Trait.ALIGNMENT, new Theory(this, Trait.ALIGNMENT, alignment));
////        hashIn.put(Trait.ALIVE)
//    }

}
