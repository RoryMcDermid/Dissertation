package Roles.Townsfolk;

import Roles.SuperRole;

public class Slayer extends SuperRole {
    public Slayer(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Slayer";
    }
}
