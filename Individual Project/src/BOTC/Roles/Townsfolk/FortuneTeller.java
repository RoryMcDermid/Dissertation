package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;
import BOTC.Trait;

//going to have the red herring added here instead, reduce amount of code written

public class FortuneTeller extends SuperRole {

    public String redHerring;

    public FortuneTeller(String playerNameIn, Boolean alignmentIn, int playerCount) {
        super(playerNameIn, alignmentIn, playerCount);
    }

    public String getRedHerring(){
        return redHerring;
    }

    public void setRedHerring(String redHerringIn){
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
