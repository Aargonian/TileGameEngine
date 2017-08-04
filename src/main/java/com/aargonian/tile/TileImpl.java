package com.aargonian.tile;

import com.aargonian.resource.Resource;
import org.pcollections.HashPMap;
import org.pcollections.HashTreePMap;

import java.util.Map;

/**
 * Created by aargonian on 7/4/17.
 */
public class TileImpl {
    /**
     * Very important magic string values used by the tile class for common properties/values
     */
    public static final String PROPERTY_TILETYPE = "TileType";
    public static final String PROPERTY_IMG = "ImageRef";
    public static final String VALUE_TRUE = "True";
    public static final String VALUE_FALSE = "False";

    private final HashPMap<String, String> properties;
    private final HashPMap<String, Resource> resources;
    private int hash = 0;

    public TileImpl(Map<String, String> startingProperties, Map<String, Resource> startingResources) {
        if (startingProperties != null && startingProperties.size() > 0) {
            this.properties = HashTreePMap.from(startingProperties);
        } else {
            this.properties = null;
        }

        if (startingResources != null && startingResources.size() > 0) {
            this.resources = HashTreePMap.from(startingResources);
        } else {
            this.resources = null;
        }
    }

    private TileImpl(HashPMap<String, String> implProperties, HashPMap<String, Resource> implResources) {
        this.properties = implProperties;
        this.resources = implResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        TileImpl tile = (TileImpl) o;

        return (this.properties != null ? this.properties.equals(tile.properties) : tile.properties == null) &&
                (this.resources != null ? this.resources.equals(tile.resources) : tile.resources == null);
    }

    @Override
    public int hashCode() {
        if (this.hash == 0) {
            int result = this.properties != null ? this.properties.hashCode() : 0;
            result = 31 * result + (this.resources != null ? this.resources.hashCode() : 0);
            this.hash = result;
        }
        return this.hash;
    }

    public TileImpl removeProperty(String property) {
        if (this.properties == null) {
            return this;
        }
        return new TileImpl(this.properties.minus(property), this.resources);
    }

    public String getProperty(String property) {
        if (this.properties == null) {
            return null;
        }
        return this.properties.get(property);
    }

    public TileImpl setProperty(String property, String value) {
        if (this.properties == null) {
            return new TileImpl(HashTreePMap.singleton(property, value), this.resources);
        }
        return new TileImpl(this.properties.plus(property, value), this.resources);
    }

    public boolean hasProperty(String property) {
        return this.properties != null && this.properties.containsKey(property) &&
                this.properties.get(property) != null;
    }

    public TileImpl removeResource(String resource) {
        if (this.resources == null) {
            return this;
        }
        return new TileImpl(this.properties, this.resources.minus(resource));
    }

    public Resource getResource(String resource) {
        if (this.resources == null) {
            return null;
        }
        return this.resources.get(resource);
    }

    public TileImpl setResource(String resourceKey, Resource resource) {
        if (this.resources == null) {
            return new TileImpl(this.properties, HashTreePMap.singleton(resourceKey, resource));
        }
        return new TileImpl(this.properties, this.resources.plus(resourceKey, resource));
    }

    public boolean hasResource(String resourceKey) {
        return this.resources != null && this.resources.containsKey(resourceKey) &&
                this.resources.get(resourceKey) != null;
    }

}
