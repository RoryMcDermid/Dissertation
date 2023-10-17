package Roles.Townsfolk;

import Roles.SuperRole;

public class Washerwoman extends SuperRole {
    public Washerwoman(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Washerwoman";
    }
}
