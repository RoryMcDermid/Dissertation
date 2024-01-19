package BOTC.Roles.Townsfolk;

import BOTC.Roles.SuperRole;

public class Empath extends SuperRole {
    public Empath(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Empath";
    }
}
