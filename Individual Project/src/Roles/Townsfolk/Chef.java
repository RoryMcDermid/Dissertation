package Roles.Townsfolk;

import Roles.SuperRole;

public class Chef extends SuperRole {
    public Chef(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Chef";
    }
}
