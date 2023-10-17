package Roles.Townsfolk;

import Roles.SuperRole;

public class Soldier extends SuperRole {
    public Soldier(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Soldier";
    }
}
