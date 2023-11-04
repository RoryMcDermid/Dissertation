package knowledge;

import Roles.SuperRole;

public class Theory {

    private SuperRole player;

    private String variable;

    private Object value;

    //maybe have one theory with multiple values
    public Theory(SuperRole playerIn, String variableIn, Object valueIn){
        player = playerIn;
        variable = variableIn;


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

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
