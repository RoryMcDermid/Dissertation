package Roles.Townsfolk;

import Roles.SuperRole;

public class Mayor extends SuperRole {
    public Mayor(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Mayor";
    }
}
