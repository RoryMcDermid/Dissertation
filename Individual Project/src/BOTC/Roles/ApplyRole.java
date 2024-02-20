package BOTC.Roles;

import BOTC.ClassName;

public class ApplyRole {

    public SuperRole giveRole(String playerNameIn, int alignmentIn, int playerCount, ClassName roleName){

        switch (roleName){
            case CHEF:
                return new BOTC.Roles.Townsfolk.Chef(playerNameIn, alignmentIn, playerCount);
            case EMPATH:
                return new BOTC.Roles.Townsfolk.Empath(playerNameIn, alignmentIn, playerCount);
            case FORTUNETELLER:
                return new BOTC.Roles.Townsfolk.FortuneTeller(playerNameIn, alignmentIn, playerCount);
            case INVESTIGATOR:
                return new BOTC.Roles.Townsfolk.Investigator(playerNameIn, alignmentIn, playerCount);
            case MAYOR:
                return new BOTC.Roles.Townsfolk.Mayor(playerNameIn, alignmentIn, playerCount);
            case MONK:
                return new BOTC.Roles.Townsfolk.Monk(playerNameIn, alignmentIn, playerCount);
            case RAVENKEEPER:
                return new BOTC.Roles.Townsfolk.Ravenkeeper(playerNameIn, alignmentIn, playerCount);
            case SLAYER:
                return new BOTC.Roles.Townsfolk.Slayer(playerNameIn, alignmentIn, playerCount);
            case SOLDIER:
                return new BOTC.Roles.Townsfolk.Soldier(playerNameIn, alignmentIn, playerCount);
            case UNDERTAKER:
                return new BOTC.Roles.Townsfolk.Undertaker(playerNameIn, alignmentIn, playerCount);
            case VIRGIN:
                return new BOTC.Roles.Townsfolk.Virgin(playerNameIn, alignmentIn, playerCount);
            case WASHERWOMAN:
                return new BOTC.Roles.Townsfolk.Washerwoman(playerNameIn, alignmentIn, playerCount);
            case SPY:
                return new BOTC.Roles.Minion.Spy(playerNameIn, alignmentIn, playerCount);
            case IMP:
                return new BOTC.Roles.Demon.Imp(playerNameIn, alignmentIn, playerCount);
            case UNKNOWN:
                return new BOTC.Roles.Unknown(playerNameIn, alignmentIn, playerCount);
        }

        return null;
    }


}
