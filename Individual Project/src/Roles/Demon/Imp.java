package Roles.Demon;

import Roles.SuperRole;

public class Imp extends SuperRole {
    public Imp(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Imp";
    }
}
