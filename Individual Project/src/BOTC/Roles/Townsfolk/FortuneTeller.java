package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;
import BOTC.Trait;

//going to have the red herring added here instead, reduce amount of code written, redherring will be the index of the targetted player

public class FortuneTeller extends SuperRole {

    public int redHerring;

    public FortuneTeller(String playerNameIn, int alignmentIn, int playerCount) {
        super(playerNameIn, alignmentIn, playerCount);
    }

    public int getRedHerring(){
        return redHerring;
    }

    public void setRedHerring(int redHerringIn){
        redHerring = redHerringIn;
    }

    @Override
    public boolean checkFact(Trait Variable, Object Value) {
        switch (Variable){
            case REDHERRING:
                if(Value.equals(redHerring)){
                    return true;
                }
                break;
        }
        return super.checkFact(Variable, Value);
    }

    public String getClassName(){
        return "FortuneTeller";
    }
}
