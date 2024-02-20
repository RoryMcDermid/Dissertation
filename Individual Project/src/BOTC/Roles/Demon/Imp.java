package BOTC.Roles.Demon;

import BOTC.Roles.SuperRole;

public class Imp extends SuperRole {
    public Imp(String playerNameIn, int alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Imp";
    }
}
