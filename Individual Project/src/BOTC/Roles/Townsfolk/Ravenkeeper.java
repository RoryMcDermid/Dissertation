package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Ravenkeeper extends SuperRole {
    public Ravenkeeper(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Ravenkeeper";
    }
}
