package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Undertaker extends SuperRole {
    public Undertaker(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Undertaker";
    }
}
