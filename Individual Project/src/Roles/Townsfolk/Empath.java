package Roles.Townsfolk;

import Roles.SuperRole;

public class Empath extends SuperRole {
    public Empath(String playerNameIn, Boolean alignmentIn, int playerCountIn) {
        super(playerNameIn, alignmentIn, playerCountIn);
    }

    public String getClassName(){
        return "Empath";
    }
}
