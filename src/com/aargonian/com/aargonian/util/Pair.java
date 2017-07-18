package com.aargonian.com.aargonian.util;

/**
 * Created by aargonian on 7/17/17.
 */
public class Pair<K, T>
{
    private K first;
    private T second;
    
    public Pair(K first, T second)
    {
        this.first = first;
        this.second = second;
    }
    
    public K getFirst()
    {
        return this.first;
    }
    
    public T getSecond()
    {
        return this.second;
    }
    
    public void setFirst(K first)
    {
        this.first = first;
    }
    
    public void setSecond(T second)
    {
        this.second = second;
    }
}
