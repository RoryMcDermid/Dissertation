package Roles.Townsfolk;

import Roles.SuperRole;

public class Monk extends SuperRole {
    public Monk(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Monk";
    }
}
