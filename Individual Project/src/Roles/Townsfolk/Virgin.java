package Roles.Townsfolk;

import Roles.SuperRole;

public class Virgin extends SuperRole {
    public Virgin(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Virgin";
    }
}
