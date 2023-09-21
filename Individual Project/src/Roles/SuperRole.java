package Roles;

//Roles can be changed, e.g. imp can kill themselves to switch demonhood, need some way to transfer statistics to other role
public abstract class SuperRole {

    private String roleName;

    //True if they are good, False if they are evil
    private Boolean alignment;

    //True if alive, False if dead
    private Boolean alive;

    //knowledge, will want seperate storage? maybe multiple knowledge bases (things that are known to be true/false, known logic links

    private SuperRole leftNeighbour;

    private SuperRole rightNeighbour;

    //Red Herring is something all roles need due to Fortune Teller
    private Boolean redHerring;

    public SuperRole(String roleNameIn, Boolean alignmentIn){

        roleName = roleNameIn;
        alignment = alignmentIn;
        alive = true;
        redHerring = false;

    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleNameIn){
        roleName = roleNameIn;
    }

    public Boolean getAlignment(){
        return alignment;
    }

    public void setAlignment(Boolean alignmentIn){
        alignment = alignmentIn;
    }

    public Boolean getAlive(){
        return alive;
    }

    public void setAlive(Boolean aliveIn){
        alive = aliveIn;
    }

    public void setLeftNeighbour(SuperRole leftNeighbourIn){
        leftNeighbour = leftNeighbourIn;
    }

    public void setRightNeighbour(SuperRole rightNeighbourIn){
        rightNeighbour = rightNeighbourIn;
    }

    public Boolean getRedHerring(){
        return redHerring;
    }

    public void setRedHerring(Boolean redHerringIn){
        redHerring = redHerringIn;
    }
}
