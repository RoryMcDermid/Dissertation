package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Washerwoman extends SuperRole {
    public Washerwoman(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Washerwoman";
    }
}
