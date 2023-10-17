package Roles.Townsfolk;

import Roles.SuperRole;

public class Virgin extends SuperRole {
    public Virgin(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Virgin";
    }
}
