package Roles.Townsfolk;

import Roles.SuperRole;

public class Investigator extends SuperRole {
    public Investigator(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Investigator";
    }
}
