package Roles.Demon;

import Roles.SuperRole;

public class Imp extends SuperRole {
    public Imp(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Imp";
    }
}
