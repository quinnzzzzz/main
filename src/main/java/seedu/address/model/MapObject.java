package seedu.address.model;

import javafx.util.Pair;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class MapObject {
    //pair field for points ranking and criteria
    private Pair<Integer, Integer> agePair = new Pair<>(0,0);
    private Pair<Integer, String> racePair = new Pair<>(0,"");
    private Pair<Integer, String> medicalPair = new Pair<>(0,"");
    private String comparator;

    /**
     * at least one field must be present and not null.
     */
    public MapObject(Pair<Integer, Integer> agePair,String comparator, Pair<Integer, String> racePair,Pair<Integer, String> medicalPair){
        this.agePair = (agePair.getKey() != 0) ? agePair : null;
        this.comparator = (agePair.getKey() != 0) ? comparator : null;
        this.racePair = (racePair.getKey() != 0) ? racePair : null;
        this.medicalPair = (medicalPair.getKey() != 0) ? medicalPair : null;
    }

    public String getComparator(){
        return comparator;
    }

    public Pair<Integer, Integer> getAgePair(){
        return agePair;
    }

    public Pair<Integer, String> getRacePair(){
        return racePair;
    }

    public Pair<Integer, String> getMedicalPair(){
        return medicalPair;
    }

    }
