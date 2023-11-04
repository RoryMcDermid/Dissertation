package Roles.Townsfolk;

import Roles.SuperRole;

public class Mayor extends SuperRole {
    public Mayor(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Mayor";
    }
}
