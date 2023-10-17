package knowledge;

import Roles.SuperRole;

public class Theory {

    private SuperRole player;

    private String variable;

    private Object value;

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
        System.out.println(value.getClass());
        return player.checkFact(variable, value);
    }

}
