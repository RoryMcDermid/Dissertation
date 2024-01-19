package BOTC.Roles.Minion;

import BOTC.Roles.SuperRole;

public class Spy extends SuperRole {
    public Spy(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Spy";
    }
}
