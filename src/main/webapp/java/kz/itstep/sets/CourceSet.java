package kz.itstep.sets;

import kz.itstep.entity.Cource;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class CourceSet implements Iterable<Cource> {
    private List<Cource> cources;
    public CourceSet() {
        this.cources = new ArrayList<Cource>();
    }
    public CourceSet(List<Cource> cources){
        this.cources = cources;
    }
    @Override
    public Iterator<Cource> iterator() {
        return cources.iterator();
    }
}
