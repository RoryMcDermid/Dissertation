package Roles.Townsfolk;

import Roles.SuperRole;

//going to have the red herring added here instead, reduce amount of code written

public class FortuneTeller extends SuperRole {

    public String redHerring;

    public FortuneTeller(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getRedHerring(){
        return redHerring;
    }

    public void setRedHerring(String redHerringIn){
        redHerring = redHerringIn;
    }

    @Override
    public boolean checkFact(String Variable, Object Value) {
        switch (Variable){
            case ("redHerring"):
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
