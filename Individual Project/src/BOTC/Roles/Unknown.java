package BOTC.Roles;

import BOTC.Roles.SuperRole;

public class Unknown extends SuperRole {
    public Unknown(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Unknown";
    }
}
