package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Virgin extends SuperRole {
    public Virgin(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Virgin";
    }
}
