package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Virgin extends SuperRole {
    public Virgin(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Virgin";
    }
}
