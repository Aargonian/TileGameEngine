package com.aargonian.resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aargonian on 7/17/17.
 * <p>
 * A utility class for loading and caching common resources used by the editor. Primarily used to load images and sound
 * files.
 */
public final class ResourceLoader
{
    private static final Map<String, Resource> resourceCache = new HashMap<>(100);
    
    public static ImageResource loadImage(String imagePath)
    {
        if(imagePath == null || imagePath.isEmpty())
        {
            return null;
        }
        else if(resourceCache.containsKey(imagePath) && resourceCache.get(imagePath) != null)
        {
            return (ImageResource) resourceCache.get(imagePath);
        }
        else
        {
            ImageResource resource = new ImageResource(imagePath);
            resourceCache.put(imagePath, resource);
            return resource;
        }
    }
}
