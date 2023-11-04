package knowledge;

public class Connection {

    private Theory theory1;

    private Theory theory2;

    private int relation; //the relation is 0 = unrelated, 1 = theory1 implies theory2 is true, 2 = theory1 implies theory2 is false

    private boolean confirmed; //has this been confirmed by the person who knows it (starts as false)

    public Connection(Theory theory1in, Theory theory2in, int relationin ){

        theory1 = theory1in;

        theory2 = theory2in;

        relation = relationin;

        confirmed = false;

    }

    //does this connection imply any other connections
    public boolean[] checkImplies(Connection[] connections){

        boolean[] implies = new boolean[connections.length];

        for (int i = 0; i < connections.length; i++){
            implies[i] = connections[i].getTheory1() == theory2;
        }

        return implies;
    }

    //is this connection implied by any other connections
    public boolean[] checkImplied(Connection[] connections){

        boolean[] implied = new boolean[connections.length];

        for (int i = 0; i < connections.length; i++){
            implied[i] = connections[i].getTheory2() == theory1;
        }
        return implied;
    }

    public Theory getTheory1() {
        return theory1;
    }

    public Theory getTheory2() {
        return theory2;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public void setRelationAndConfirmed(int relation, boolean confirmed){
        this.relation = relation;
        this.confirmed = confirmed;
    }

    //returns this connection, but has the theories switched, so if this object says that a implies b, then the returned function says that b implies a
    public Connection equivalance(){
        return new Connection(theory2, theory1, relation);
    }
}
