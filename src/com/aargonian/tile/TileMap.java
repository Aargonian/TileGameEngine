package aargonian.tile;

import java.util.ArrayList;

/**
 * Created by aargonian on 7/4/17.
 */
public class TileMap
{
    private final ArrayList<TileImpl> tilesImplementations;
    private final Tile[] tiles;
    private int rows;
    private int columns;
    
    public TileMap(int columns, int rows)
    {
        this.columns = columns;
        this.rows = rows;
        tilesImplementations = new ArrayList<TileImpl>();
        tilesImplementations.add(new TileImpl(null, null));
        tilesImplementations.get(0).setProperty(TileImpl.PROPERTY_TILETYPE, "EmptyTile");
        tiles = new Tile[columns * rows];
        for(int i = 0; i < tiles.length; i++)
        {
            tiles[i] = new Tile(tilesImplementations.get(0));
        }
    }
    
    public void addTileImplementation(TileImpl impl)
    {
        tilesImplementations.add(impl);
    }
    
    public ArrayList<TileImpl> getTileImplementations()
    {
        return tilesImplementations;
    }
    
    public int getRows()
    {
        return rows;
    }
    
    public int getColumns()
    {
        return columns;
    }
    
    private final void setTileImplementation(Tile tile, TileImpl newImpl)
    {
        for(TileImpl implementation : tilesImplementations)
        {
            if(implementation.hashCode() == newImpl.hashCode())
            {
                if(implementation.equals(newImpl))
                {
                    tile.setTileImplementation(implementation);
                    newImpl = null;
                    return;
                }
            }
        }
        //For loop found no duplicates. Add the new implementation to the list.
        tilesImplementations.add(newImpl);
        tile.setTileImplementation(newImpl);
    }
    
    public String getTilePropertyAt(String property, int x, int y)
    {
        return tiles[(y * columns) + x].getTileImplementation().getProperty(property);
    }
    
    public void setTilePropertyAt(String property, String value, int x, int y)
    {
        Tile tile = tiles[(y * columns) + x];
        TileImpl newImpl = tile.getTileImplementation().setProperty(property, value);
        setTileImplementation(tile, newImpl);
    }
    
    public Object getTileResourceAt(String resource, int x, int y)
    {
        return tiles[(y * columns) + x].getTileImplementation().getResource(resource);
    }
    
    public void setTileResourceAt(String resourceKey, Object resource, int x, int y)
    {
        Tile tile = tiles[(y * columns) + x];
        TileImpl newImpl = tile.getTileImplementation().setResource(resourceKey, resource);
        setTileImplementation(tile, newImpl);
    }
    
    private final class Tile
    {
        private TileImpl implementation;
        
        public Tile(TileImpl impl)
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
            if(o == null || getClass() != o.getClass())
            {
                return false;
            }
            
            Tile tile = (Tile) o;
            
            return implementation.equals(tile.implementation);
        }
        
        @Override
        public int hashCode()
        {
            return implementation.hashCode();
        }
        
        public TileImpl getTileImplementation()
        {
            return this.implementation;
        }
        
        public void setTileImplementation(TileImpl impl)
        {
            this.implementation = impl;
        }
    }
}
