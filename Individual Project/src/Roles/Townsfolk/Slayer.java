package Roles.Townsfolk;

import Roles.SuperRole;

public class Slayer extends SuperRole {
    public Slayer(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Slayer";
    }
}
