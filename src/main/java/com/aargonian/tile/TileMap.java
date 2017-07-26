package com.aargonian.tile;

import com.aargonian.resource.Resource;

import java.util.ArrayList;

/**
 * Created by aargonian on 7/4/17.
 */
public class TileMap
{
    private final ArrayList<TileImpl> tilesImplementations;
    private final Tile[] tiles;
    private final int rows;
    private final int columns;
    
    public TileMap(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
        this.tilesImplementations = new ArrayList<>();
        this.tilesImplementations.add(new TileImpl(null, null));
        this.tilesImplementations.get(0).setProperty(TileImpl.PROPERTY_TILETYPE, "EmptyTile");
        this.tiles = new Tile[columns * rows];
        for(int i = 0; i < this.tiles.length; i++)
        {
            this.tiles[i] = new Tile(this.tilesImplementations.get(0));
        }
    }
    
    public void addTileImplementation(TileImpl impl)
    {
        this.tilesImplementations.add(impl);
    }
    
    public ArrayList<TileImpl> getTileImplementations()
    {
        return this.tilesImplementations;
    }
    
    public int getRows()
    {
        return this.rows;
    }
    
    public int getColumns()
    {
        return this.columns;
    }
    
    private void setTileImplementation(Tile tile, TileImpl newImpl)
    {
        for(TileImpl implementation : this.tilesImplementations)
        {
            if(implementation.hashCode() == newImpl.hashCode())
            {
                if(implementation.equals(newImpl))
                {
                    tile.setTileImplementation(implementation);
                    return;
                }
            }
        }
        //For loop found no duplicates. Add the new implementation to the list.
        this.tilesImplementations.add(newImpl);
        tile.setTileImplementation(newImpl);
    }
    
    private boolean inBounds(int x, int y)
    {
        return !(x < 0 || x >= this.columns || y < 0 || y >= this.rows);
    }
    
    public String getTilePropertyAt(String property, int x, int y)
    {
        if(this.inBounds(x, y))
        {
            return this.tiles[(y * this.columns) + x].getTileImplementation().getProperty(property);
        }
        return null;
    }
    
    public void setTilePropertyAt(String property, String value, int x, int y)
    {
        if(!this.inBounds(x, y))
        {
            return;
        }
        Tile tile = this.tiles[(y * this.columns) + x];
        TileImpl newImpl = tile.getTileImplementation().setProperty(property, value);
        this.setTileImplementation(tile, newImpl);
    }
    
    public Resource getTileResourceAt(String resource, int x, int y)
    {
        if(!this.inBounds(x, y))
        {
            return null;
        }
        return this.tiles[(y * this.columns) + x].getTileImplementation().getResource(resource);
    }
    
    public void setTileResourceAt(String resourceKey, Resource resource, int x, int y)
    {
        if(!this.inBounds(x, y))
        {
            return;
        }
        Tile tile = this.tiles[(y * this.columns) + x];
        TileImpl newImpl = tile.getTileImplementation().setResource(resourceKey, resource);
        this.setTileImplementation(tile, newImpl);
    }
    
    private final class Tile
    {
        private TileImpl implementation;
    
        private Tile(TileImpl impl)
        {
            this.implementation = impl;
        }
        
        @Override
        public boolean equals(Object o)
        {
            if(this == o)
            {
                return true;
            }
            if(o == null || this.getClass() != o.getClass())
            {
                return false;
            }
            
            Tile tile = (Tile) o;
    
            return this.implementation.equals(tile.implementation);
        }
        
        @Override
        public int hashCode()
        {
            return this.implementation.hashCode();
        }
    
        private TileImpl getTileImplementation()
        {
            return this.implementation;
        }
    
        private void setTileImplementation(TileImpl impl)
        {
            this.implementation = impl;
        }
    }
}
