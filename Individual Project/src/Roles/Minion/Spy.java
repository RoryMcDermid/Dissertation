package Roles.Minion;

import Roles.SuperRole;

public class Spy extends SuperRole {
    public Spy(String playerNameIn, Boolean alignmentIn) {
        super(playerNameIn, alignmentIn);
    }

    public String getClassName(){
        return "Spy";
    }
}
