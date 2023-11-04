package Roles.Townsfolk;

import Roles.SuperRole;

public class Washerwoman extends SuperRole {
    public Washerwoman(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Washerwoman";
    }
}
