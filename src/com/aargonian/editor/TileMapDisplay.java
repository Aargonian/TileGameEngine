package com.aargonian.editor;

import com.aargonian.com.aargonian.tile.TileImpl;
import com.aargonian.com.aargonian.tile.TileMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aargonian on 7/8/17.
 */
public class TileMapDisplay extends JComponent
{
    private TileMap tileMap;
    private final OptionsBuilder options;
    private static final OptionsBuilder DEFAULT_OPTIONS =
            new OptionsBuilder().borderColor(Color.black).displaySize(640, 480).tileSize(32, 32);

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

    public void setCurrentTileMap(TileMap map)
    {
        this.tileMap = map;
    }

    public TileMap getCurrentTileMap()
    {
        return this.tileMap;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(options.borderColor());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int x = 0; x < tileMap.getColumns(); x++)
        {
            for(int y = 0; y < tileMap.getRows(); y++)
            {
                Image img = (Image)tileMap.getTileResourceAt(TileImpl.PROPERTY_IMG, x, y);
                if(img != null)
                    g2.drawImage(img,
                            x*options.tileWidth(), y*options.tileHeight(), options.tileWidth(), options.tileHeight(),
                            null);
                else
                {
                    GradientPaint defPaint = new GradientPaint(x*options.tileWidth(), y*options.tileHeight(),
                            Color.white, (x+1)*options.tileWidth(), (y+1)*options.tileHeight, Color.black);
                    g2.setPaint(defPaint);
                    g2.fillRect(x*options.tileWidth(), y*options.tileHeight(), options.tileWidth(), options.tileHeight());
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

        public int tileWidth() { return tileWidth; }
        public int tileHeight() { return tileHeight; }
        public int displayWidth() { return displayWidth; }
        public int displayHeight() { return displayHeight; }
        public Color borderColor() { return borderColor; }
        public boolean fulllscreen() { return fullscreen; }
    }
}
