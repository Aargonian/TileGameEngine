# Nytework Tile Editor

Welcome to the WIP version of the Nytework Tile Editor, an attempt to create an open-source Tile-based Game 
Engine/Editor. Currently, this project is in very, very early stages and as yet barely has a runnable build, let alone
anything resembling useful. All work right now is currently being focussed on the design and implementation of the 
backend, and UI is currently only there to confirm that the underlying pieces are working properly. 

If you wish to contribute to the project, feel free! I'll try to document much of the current design and reasoning here
or in the DesignDocument until such time that I feel the engine is ready for non-development users. However, as I work
on this engine alone currently, and only in my offtime from work/other projects, these resources may not be updated as 
frequently as necessary. Feel free to shoot many any questions on IRC or through my email. You can find me on IRC in the
\#programming channel on freenode, and you can feel free to shoot me a PM at any time. No need to ask first. If email is
better for you, you can email me at aargonian at gmail dot com.

Suggestions or recommendations are also welcome! As far as what has already been planning...

##Planned Features/Attributes (In no particularly order)

**NOTE:** *All of these are works in progress. Features may come and go as the engine design evolves or issues are 
discovered. Feel free to recommend more features, or explain why you think an existing feature may be a bad idea, or may
need to be rethought.*

* Scripting. 
    * It is my eventual hope that when the engine is complete, much of the game development can be completed by a 
    scripting language. Right now, the plan is to eventually use JavaScript as the language of choice, for several
    reasons: Java (the current implementation language) has built-in support for running JavaScript, and there are 
    many programmers/developers out there who are already very comfortable with the language.
    * Other possibilities include creating a custom DSL for the project, which would provide several tools/libraries
    specifically for working with the engine. These are not necessarily mutually exclusive options.
    * A final, longer term option that I've been tossing around is to provide a block-based programming language, 
    perhaps akin to Unreal Engine's Blueprints, that would allow non-programmers to do some simple scripting without
    having to learn programming, or for simple use cases.
    * Regardless of what options are used, the Engine will primarily revolve around these scripts. As stated in more 
    detail in the DesignDocument, the engine will be divided into two portions, the Upper engine and the Lower Engine. 
    The scripts are an essential part of the UE, because all essential functionality not related to OS/Engine-specific 
    tasks will be implemented in scripts. The goal is to eliminate the idea of anything "special" about certain tiles. 
    The final engine will know nothing about door tiles, water, characters (beyond the Actor Subsystem), or how tiles 
    and actors interact. Everything not explicitly about the engine will be scripting, to provide as much freedom as
    possible to the creators. 
* Basic Tile-based editing
    * Should probably go unsaid, but the engine/editor will, of course, have all the tools for working with tile-based 
    games in general. Much of this includes subsystems such as the Actor Subsystem, which will be how the engine (and 
    any user scripts) interact with any "actors" (i.e. characters), or the Tile subsystem, which basically dictates how
    tiles interact with each other and any scripts imposed on them.
* Import/Export of two file formats, NTE and NTG.
    * These represent the editor format, and the game format, respectively. This can be compared to PSD files versus the
    export formats Photoshop provides. The NTE files will contain all the information pertinent to the editor, while the
    NTG format is an optimized final export of a game that strips anything unneded for actual execution of the game, as 
    well as containing optimized forms of all maps/actors in the game that can 
be safely performed without losing information.
* Virtual File System 
    * Since most features of the Upper Engine are intended to work independent of any lower Operating System and should
    be less concerned about I/O issues, the engine will implement a sort of Virtual File System, that allows the game
    creator to assign files to a virtual "folder" or set of directories that the scripts the game employs can then 
    use to access these files. By adding an extra layer of abstraction, the engine makes it harder to "accidentally" 
    modify files or access files that weren't intended to be accessed by the game, while simultaneously allowing files
    to be moved wherever it is deemed fit to do so for performance. For instance, an image file, img.png, could be 
    accessed by requesting the image file "res/images/img.png", which in actuality could be transformed into a call
    for /home/user/.local/cache/TGE/images/img.png", a call to read a spritesheet that contains the image (by having yet
    another script intercept the call), or even simply retrieve the image from RAM as it was generated dynamically. 