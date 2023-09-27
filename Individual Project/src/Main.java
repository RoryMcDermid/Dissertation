import Roles.SuperRole;
import Roles.Townsfolk.Chef;

public class CreateGame
{

    public static void main(String[] args) {
        //args will be the number of intended players (currently 7)

        SuperRole[] players = new SuperRole[Integer.parseInt(args[0])];

        players[0] = new Chef("John", true);

        for (SuperRole player:
             players) {
            System.out.println(player.getPlayerName());
        }

//        assign(players);

    }

    public SuperRole[] assign(SuperRole[] players){
        //assign
        return players;
    }
}
