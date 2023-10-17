package Roles.Townsfolk;

import Roles.SuperRole;

public class Ravenkeeper extends SuperRole {
    public Ravenkeeper(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Ravenkeeper";
    }
}
