package Roles.Townsfolk;

import Roles.SuperRole;

public class Monk extends SuperRole {
    public Monk(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Monk";
    }
}
