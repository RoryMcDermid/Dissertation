package Roles.Townsfolk;

import Roles.SuperRole;

public class Investigator extends SuperRole {
    public Investigator(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Investigator";
    }
}
