package com.aargonian.resource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by aargonian on 7/22/17.
 * <p>
 * This is a simple extension to the existing BufferedImage class that provides an implementation of equals() and
 * hashCode().
 */
public class ImageResource extends Resource
{
    private final BufferedImage res;
    private int hash = 0;
    
    public ImageResource(String imagePath)
    {
        super(imagePath, ResourceType.IMAGE);
        
        BufferedImage temp = null;
        if(imagePath != null && !imagePath.isEmpty())
        {
            try
            {
                File imageFile = new File(imagePath);
                if(imageFile.exists())
                {
                    temp = ImageIO.read(imageFile);
                }
                else
                {
                    throw new IOException("Image File Does Not Exist: " + imagePath);
                }
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
        this.res = temp;
    }
    
    public BufferedImage getImage()
    {
        return this.res;
    }
    
    @Override
    public int hashCode()
    {
        if(hash == 0 && this.res != null)
        {
            int[] data = new int[this.res.getWidth() * this.res.getHeight()];
            this.res.getRGB(0, 0, this.res.getWidth(), this.res.getHeight(), data, 0, 0);
            hash = Arrays.hashCode(data);
        }
        return hash;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null)
        {
            return false;
        }
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        
        ImageResource other = (ImageResource) o;
        if(this.res.getWidth() != other.res.getWidth())
        {
            return false;
        }
        if(this.res.getHeight() != other.res.getHeight())
        {
            return false;
        }
        
        for(int x = 0; x < this.res.getHeight(); x++)
        {
            for(int y = 0; y < this.res.getWidth(); y++)
            {
                if(this.res.getRGB(x, y) != other.res.getRGB(x, y))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
