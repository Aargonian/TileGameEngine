package com.aargonian.editor;

import java.awt.event.*;

/**
 * Created by aargonian on 7/16/17.
 * <p>
 * An EditorTool is an implementation of a Controller for the main EditableTileMap. EditorTools can be thought of very
 * similarly to the "Tools" used by graphics editing programs. Each EditorTool gets access to the underlying TileMap
 * model, and receives input events from the TileMapDisplay. It is the job of the EditorTool to translate these input
 * events into corresponding actions on the underlying TileMap or in the TileMapDisplay.
 */
public class EditorTool implements MouseListener, MouseMotionListener, KeyListener
{
    private final TileMapDisplay display;
    
    public EditorTool(TileMapDisplay display)
    {
        if(display == null)
        {
            throw new NullPointerException("Given TileMapDisplay was Null!");
        }
        this.display = display;
    }
    
    protected TileMapDisplay getCurrentDisplay()
    {
        return this.display;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
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
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
}
