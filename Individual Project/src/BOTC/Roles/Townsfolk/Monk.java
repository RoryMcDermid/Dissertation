package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Monk extends SuperRole {
    public Monk(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Monk";
    }
}
