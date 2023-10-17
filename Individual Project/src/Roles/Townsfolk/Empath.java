package Roles.Townsfolk;

import Roles.SuperRole;

public class Empath extends SuperRole {
    public Empath(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Empath";
    }
}
