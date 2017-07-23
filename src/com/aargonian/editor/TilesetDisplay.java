package com.aargonian.editor;

import com.aargonian.resource.ImageResource;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by aargonian on 7/9/17.
 * <p>
 * This class implements a display for a set of tiles.
 */
public final class TilesetDisplay extends JComponent implements MouseListener
{
    private final ArrayList<ImageResource> images;
    private final ArrayList<TilesetListener> listeners;
    private int currentlySelectedImage = 0;
    private int tileSize = 0;
    
    public TilesetDisplay(int tileSize, ArrayList<ImageResource> tileImages)
    {
        if(tileImages == null)
        {
            throw new NullPointerException("Passed Image Set is Null.");
        }
        if(tileSize < 0)
        {
            throw new IllegalArgumentException("Tilesize Cannot Be Negative!");
        }
        this.images = tileImages;
        this.tileSize = tileSize;
        this.listeners = new ArrayList<>();
    
        this.addMouseListener(this);
        //TODO: Remove this later?
        int squareSize = ((int) (Math.sqrt(tileImages.size()))) * tileSize;
        this.setPreferredSize(new Dimension(squareSize, squareSize));
    }
    
    public void addSetListener(TilesetListener listener)
    {
        this.listeners.add(listener);
    }
    
    public void removeSetListener(TilesetListener listener)
    {
        this.listeners.remove(listener);
    }
    
    private void setCurrentlySelectedImage(int index)
    {
        currentlySelectedImage = index;
        if(index < images.size() && index >= 0)
        {
            for(TilesetListener listener : listeners)
            {
                listener.imageSelected(images.get(index));
            }
        }
    }
    
    public ImageResource getCurrentlySelectedImage(ImageResource res)
    {
        if(currentlySelectedImage >= 0 && currentlySelectedImage < images.size())
        {
            return images.get(currentlySelectedImage);
        }
        return null;
    }
    
    public void addImageToDisplay(ImageResource img)
    {
        images.add(img);
        this.repaint();
    }
    
    public void removeImageFromDisplay(ImageResource img)
    {
        images.remove(img);
        this.repaint();
    }
    
    //Todo: Update this to use a scrollpane and avoid divide by zero.
    @Override
    public void paintComponent(Graphics g)
    {
        int columns = (int) (getWidth() / tileSize);
        if(columns != 0) // Avoid divide by zero and simply display nothing.
        {
            for(int i = 0; i < images.size(); i++)
            {
                g.drawImage(images.get(i).getImage(), (i % columns) * tileSize, ((int) (i / columns)) * tileSize,
                            tileSize, tileSize, null);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(this.images.size() < 1)
        {
            return;
        }
        
        int columns = this.getWidth() / tileSize;
        int tileX = e.getX() / tileSize;
        int tileY = e.getY() / tileSize;
        setCurrentlySelectedImage(tileY * columns + tileX);
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseEntered(MouseEvent e)
    {
    
    }
    
    @Override
    public void mouseExited(MouseEvent e)
    {
    
    }
    
    public static interface TilesetListener
    {
        public void imageSelected(ImageResource image);
    }
}
