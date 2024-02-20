package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Chef extends SuperRole {
    public Chef(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Chef";
    }
}
