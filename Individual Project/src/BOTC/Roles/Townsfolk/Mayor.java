package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Mayor extends SuperRole {
    public Mayor(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Mayor";
    }
}
