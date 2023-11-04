package Roles.Townsfolk;

import Roles.SuperRole;

public class Soldier extends SuperRole {
    public Soldier(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Soldier";
    }
}
