package Roles;

//Roles can be changed, e.g. imp can kill themselves to switch demonhood, need some way to transfer statistics to other role
public abstract class SuperRole {

    private String playerName;

    //True if they are good (townsfolk or outsider), False if they are evil (minion or demon)
    private Boolean alignment;

    //True if alive, False if dead
    private Boolean alive;

    //knowledge, will want seperate storage? maybe multiple knowledge bases (things that are known to be true/false, known logic links, etc)



    public SuperRole(String playerNameIn, Boolean alignmentIn){

        playerName = playerNameIn;
        alignment = alignmentIn;
        alive = true;

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

    //here as a basecase
    public boolean checkFact(String Variable, Object Value) {
        switch (Variable){
            case "playerName":
                if(Value.equals(playerName)){
                    return true;
                }
                break;
            //getClassName should return the same as the name of the file/object of the given class
            case "class":
                if(Value.equals(this.getClassName())){
                    return true;
                }
                break;
            case "alignment":
                if(Value == alignment){
                    return true;
                }
                break;
            case "alive":
                if(Value == alive){
                    return true;
                }
                break;
        }
        return false;
    }


}
