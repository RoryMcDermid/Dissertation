package BOTC.knowledge;

import BOTC.Roles.SuperRole;
import BOTC.Trait;

public class Theory {

    private SuperRole player;

    private Trait variable;

    private Object value;

    private boolean confirmed;

    //maybe have one theory with multiple values
    public Theory(SuperRole playerIn, Trait variableIn, Object valueIn){
        player = playerIn;
        variable = variableIn;
        confirmed = false;
        //need to add more to this as and when more variable classes are added

        if(valueIn instanceof String){
            value = valueIn.toString();
        }
        if(valueIn instanceof Boolean){
            value = (boolean) valueIn;
        }
        if(valueIn instanceof Integer){
            String temp = valueIn.toString();
            value = Integer.parseInt(temp);
        }
    }

    public boolean checkTruth(){
        if (player == null){
            return false;
        }
        return player.checkFact(variable, value);
    }

    public void setPlayer(SuperRole player) {
        this.player = player;
    }

    public void setVariable(Trait variable) {
        this.variable = variable;
    }

    public Trait getVariable() {
        return variable;
    }

    public Object getValue() {
        return value;
    }

    public boolean isEqual(Theory secondTheory){
        if(secondTheory.getPlayer() == this.getPlayer()){
            if(secondTheory.getVariable() == this.getVariable()){
                if(secondTheory.getValue() == this.getValue()){
                    return true;
                }
            }
        }
        return false;
    }

    public SuperRole getPlayer() {
        return player;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    //this is for when a theory is 100% correct
    public void confirm(){
        confirmed = true;
    }

    public void print(){
        System.out.println(player.getPlayerName() + ":" + variable.toString() + ":" + value);
    }

}
