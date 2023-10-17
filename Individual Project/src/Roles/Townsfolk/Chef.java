package Roles.Townsfolk;

import Roles.SuperRole;

public class Chef extends SuperRole {
    public Chef(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Chef";
    }
}
