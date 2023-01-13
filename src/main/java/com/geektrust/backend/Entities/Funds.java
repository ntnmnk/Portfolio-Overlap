package com.geektrust.backend.Entities;

import lombok.Getter;
import java.util.Set;


@Getter
public class Funds {
    private  String name;
    @Getter private Set<String> stocks;
    public Funds(){}
    public Funds(String name, Set<String> stocks) {
        this.name = name;
        this.stocks = stocks;
    }
    
    
    


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((stocks == null) ? 0 : stocks.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funds other = (Funds) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (stocks == null) {
            if (other.stocks != null)
                return false;
        } else if (!stocks.equals(other.stocks))
            return false;
        return true;
    }
    
    
    
}
