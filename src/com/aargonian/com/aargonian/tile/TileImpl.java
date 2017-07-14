package com.aargonian.com.aargonian.tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.pcollections.HashPMap;
import org.pcollections.HashTreePMap;

/**
 * Created by aargonian on 7/4/17.
 */
public class TileImpl
{
    /**
     * Very important magic string values used by the tile class for common properties/values
     */
    public static final String PROPERTY_TILETYPE = "TileType";
    public static final String PROPERTY_IMG = "ImageRef";
    public static final String VALUE_TRUE = "True";
    public static final String VALUE_FALSE = "False";

    private final HashPMap<String, String> properties;
    private final HashPMap<String, Object> resources;
    private int hash = 0;

    public TileImpl(Map<String, String> startingProperties, Map<String, Object> startingResources)
    {
        if(startingProperties != null && startingProperties.size() > 0)
            this.properties = HashTreePMap.from(startingProperties);
        else
            this.properties = null;

        if(startingResources != null && startingResources.size() > 0)
            this.resources = HashTreePMap.from(startingResources);
        else
            this.resources = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileImpl tile = (TileImpl) o;

        if (properties != null ? !properties.equals(tile.properties) : tile.properties != null) return false;
        return resources != null ? resources.equals(tile.resources) : tile.resources == null;
    }

    @Override
    public int hashCode() {
        int result = hash;
        if(result == 0) {
            result = properties != null ? properties.hashCode() : 0;
            result = 31 * result + (resources != null ? resources.hashCode() : 0);
            hash = result;
        }
        return result;
    }

    private TileImpl(HashPMap<String, String> implProperties, HashPMap<String, Object> implResources)
    {
        this.properties = implProperties;
        this.resources = implResources;
    }

    public TileImpl removeProperty(String property)
    {
        if(properties == null)
            return this;
        return new TileImpl(properties.minus(property), resources);
    }

    public String getProperty(String property)
    {
        if(properties == null)
            return null;
        return properties.get(property);
    }

    public TileImpl setProperty(String property, String value)
    {
        if(properties == null)
            return new TileImpl(HashTreePMap.singleton(property, value), resources);
        return new TileImpl(properties.plus(property, value), resources);
    }

    public boolean hasProperty(String property)
    {
        if(properties == null)
            return false;
        return properties.containsKey(property) && properties.get(property) != null;
    }

    public TileImpl removeResource(String resource)
    {
        if(resources == null)
            return this;
        return new TileImpl(properties, resources.minus(resource));
    }

    public Object getResource(String resource)
    {
        if(resources == null)
            return null;
        return resources.get(resource);
    }

    public TileImpl setResource(String resourceKey, Object resource)
    {
        if(resources == null)
            return new TileImpl(properties, HashTreePMap.singleton(resourceKey, resource));
        return new TileImpl(properties, resources.plus(resourceKey, resource));
    }

    public boolean hasResource(String resourceKey)
    {
        if(resources == null)
            return false;
        return resources.containsKey(resourceKey) && resources.get(resourceKey) != null;
    }

}
