package com.aargonian.editor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by aargonian on 7/9/17.
 * <p>
 * This class implements a display for a set of tiles.
 */
public final class TilesetDisplay extends JComponent
{
    private final ArrayList<Image> images;
    private int tileSize;
    
    public TilesetDisplay(int tileSize, ArrayList<Image> tileImages)
    {
        if(tileImages == null)
        {
            throw new NullPointerException("Passed Image Set is Null.");
        }
        this.images = tileImages;
        this.tileSize = tileSize;
        
        //TODO: Remove this later?
        int squareSize = ((int) (Math.sqrt(tileImages.size()))) * tileSize;
        this.setPreferredSize(new Dimension(squareSize, squareSize));
    }
    
    public void addImageToDisplay(Image img)
    {
        images.add(img);
        this.repaint();
    }
    
    public void removeImageFromDisplay(Image img)
    {
        images.remove(img);
        this.repaint();
    }
    
    //Todo: Update this to use a scrollpane and avoid divide by zero.
    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
        int columns = (int) (getWidth() / tileSize);
        if(columns != 0) // Avoid divide by zero and simply display nothing.
        {
            for(int i = 0; i < images.size(); i++)
            {
                g.drawImage(images.get(i), (i % columns) * tileSize, ((int) (i / columns)) * tileSize, tileSize,
                            tileSize, null);
            }
        }
    }
}
