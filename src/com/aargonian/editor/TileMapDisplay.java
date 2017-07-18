package com.aargonian.editor;

import com.aargonian.tile.TileImpl;
import com.aargonian.tile.TileMap;
import com.aargonian.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by aargonian on 7/8/17.
 */
public class TileMapDisplay extends JComponent
{
    private static final OptionsBuilder DEFAULT_OPTIONS =
            new OptionsBuilder().borderColor(Color.black).displaySize(640, 480).tileSize(32, 32);
    private final OptionsBuilder options;
    private TileMap tileMap;
    private EditorTool currentActiveTool;
    private List<Pair<Integer, Integer>> selectedTiles;
    
    public TileMapDisplay()
    {
        this.options = DEFAULT_OPTIONS;
    }
    
    public TileMapDisplay(OptionsBuilder options)
    {
        this.options = options;
        this.setPreferredSize(new Dimension(options.displayWidth(), options.displayHeight()));
    }
    
    public OptionsBuilder getOptions()
    {
        return options;
    }
    
    public TileMap getCurrentTileMap()
    {
        return this.tileMap;
    }
    
    public void setCurrentTileMap(TileMap map)
    {
        this.tileMap = map;
    }
    
    public List<Pair<Integer, Integer>> getSelectedTiles()
    {
        return this.selectedTiles;
    }
    
    public void setSelectedTiles(List<Pair<Integer, Integer>> tiles)
    {
        this.selectedTiles = tiles;
        this.repaint();
    }
    
    public EditorTool getCurrentActiveTool()
    {
        return this.currentActiveTool;
    }
    
    public void setCurrentActiveTool(EditorTool tool)
    {
        if(this.currentActiveTool != null)
        {
            this.removeMouseListener(currentActiveTool);
            this.removeMouseMotionListener(currentActiveTool);
            this.removeKeyListener(currentActiveTool);
        }
        
        this.currentActiveTool = tool;
        this.addMouseListener(tool);
        this.addMouseMotionListener(tool);
        this.addKeyListener(tool);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(options.borderColor());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int x = 0; x < tileMap.getColumns(); x++)
        {
            for(int y = 0; y < tileMap.getRows(); y++)
            {
                final int tileXLoc = x * options.tileWidth();
                final int tileYLoc = y * options.tileHeight();
                
                Image img = (Image) tileMap.getTileResourceAt(TileImpl.PROPERTY_IMG, x, y);
                if(img != null)
                {
                    g2.drawImage(img, tileXLoc, tileYLoc, options.tileWidth(), options.tileHeight(), null);
                }
                else
                {
                    final int HALF_WIDTH = options.tileWidth() / 2;
                    final int HALF_HEIGHT = options.tileHeight() / 2;
                    
                    g2.setColor(Color.lightGray);
                    g2.fillRect(tileXLoc, tileYLoc, HALF_WIDTH, HALF_HEIGHT);
                    g2.fillRect(tileXLoc + HALF_WIDTH, tileYLoc + HALF_HEIGHT, HALF_WIDTH, HALF_HEIGHT);
                    
                    g2.setColor(Color.gray);
                    g2.fillRect(tileXLoc, tileYLoc + HALF_HEIGHT, HALF_WIDTH, HALF_HEIGHT);
                    g2.fillRect(tileXLoc + HALF_WIDTH, tileYLoc, HALF_WIDTH, HALF_HEIGHT);
                }
                
                if(selectedTiles != null)
                {
                    for(Pair<Integer, Integer> selectedTile : selectedTiles)
                    {
                        if(selectedTile.getFirst() == x && selectedTile.getSecond() == y)
                        {
                            g2.setColor(Color.YELLOW);
                            g2.fillRect(tileXLoc, tileYLoc, options.tileWidth(), options.tileHeight());
                            g2.setColor(Color.YELLOW.brighter());
                            g2.fillRect(tileXLoc + 2, tileYLoc + 2, options.tileWidth() - 2, options.tileHeight() - 2);
                        }
                    }
                }
            }
        }
    }
    
    public static final class OptionsBuilder
    {
        private int tileWidth = 32;
        private int tileHeight = 32;
        private int displayWidth = 640;
        private int displayHeight = 480;
        private Color borderColor = Color.black;
        private boolean fullscreen = false;
        
        public OptionsBuilder tileWidth(int width)
        {
            this.tileWidth = width <= 0 ? 0 : width;
            return this;
        }
        
        public OptionsBuilder tileHeight(int height)
        {
            this.tileHeight = height <= 0 ? 0 : height;
            return this;
        }
        
        public OptionsBuilder tileSize(int width, int height)
        {
            return this.tileWidth(width).tileHeight(height);
        }
        
        public OptionsBuilder borderColor(Color c)
        {
            this.borderColor = c;
            return this;
        }
        
        public OptionsBuilder displayWidth(int width)
        {
            this.displayWidth = width <= 0 ? 0 : width;
            return this;
        }
        
        public OptionsBuilder displayHeight(int height)
        {
            this.displayHeight = height <= 0 ? 0 : height;
            return this;
        }
        
        public OptionsBuilder displaySize(int width, int height)
        {
            return this.displayWidth(width).displayHeight(height);
        }
        
        public OptionsBuilder fullscreen(boolean value)
        {
            this.fullscreen = value;
            return this;
        }
        
        public int tileWidth()
        {
            return tileWidth;
        }
        
        public int tileHeight()
        {
            return tileHeight;
        }
        
        public int displayWidth()
        {
            return displayWidth;
        }
        
        public int displayHeight()
        {
            return displayHeight;
        }
        
        public Color borderColor()
        {
            return borderColor;
        }
        
        public boolean fulllscreen()
        {
            return fullscreen;
        }
    }
}
