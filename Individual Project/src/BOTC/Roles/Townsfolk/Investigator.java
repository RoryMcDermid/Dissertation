package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Investigator extends SuperRole {
    public Investigator(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Investigator";
    }
}
