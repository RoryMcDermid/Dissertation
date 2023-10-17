package Roles.Townsfolk;

import Roles.SuperRole;

public class Undertaker extends SuperRole {
    public Undertaker(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Undertaker";
    }
}
