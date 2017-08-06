package com.aargonian.resource;

/**
 * Created by aargonian on 7/22/17.
 * <p>
 * A Resource is an abstract representation of something that the editor needs, such as an image, a file, a soundbyte,
 * or anything else that may be limited or is unique in some fashion. Resources are named entities, and are cacheable
 * and ownable. Resources are required to override the equals() and hashCode() methods, for convenience.
 */
abstract public class Resource
{
    private final String       name;
    private final ResourceType type;

    protected Resource(String name, ResourceType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return this.name;
    }

    public ResourceType getType()
    {
        return this.type;
    }

    @Override
    abstract public boolean equals(Object o);

    @Override
    abstract public int hashCode();

    //We know at compile-time what sort of resources exist, so they are enumerated here for checking
    public enum ResourceType
    {
        FILE,
        IMAGE,
        SOUND,
        SCRIPT,
        SOCKET
    }
}
