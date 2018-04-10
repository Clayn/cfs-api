# Clayn FileSystem

[![Build Status](https://travis-ci.org/Clayn/cfs-api.svg?branch=0.3)](https://travis-ci.org/Clayn/cfs-api)

This projects provides an abstraction layer for working with files and directories. In difference to other projects or the java.nio classes the CFS is not bound to your machines filesystem. 

The goal is to ease the usage of files using a simple API. 

Here are some of the features of the CFS:

- Distinctness between files and directories without calling a method
- No dependency to an implementation
- Basic operations such as creating, deleting, reading and writing
- Optional watching for filechanges in a directory

One reason for creating the project was to reduce effort needed for handling files. 
For example if you want to create a file (including parent directories) you would do something like this:
````java
File f=new File("somePath/MyFile.txt");
if(!f.exists()) {
	File parent=f.getParent();
    if(!parent.exists() {
    	if(!parent.mkDirs()) {
        	throw new Exception("Error Message");
        }
    }
    if(!f.createNewFile()) {
    	throw new Exception("Error Message");
    }
}
````

of course you could put this in a method but still you need it. 

Using the CFS you could do the same thing just with:
````java
CFileSystem cfs=new FancyFileSystem(); //Or get it from somewhere else
Directory dir=cfs.getDirectory("SomeDirectory");
SimpleFile file=dir.getFile("MyFile.txt");
file.create(); //If you want an exception thrown in case the file exists
file.createSafe(); //If you want the file created and go on if it exists already
````

Additonal the CFS provides a simple API for watching directories for changes. This is an optional feature and may not be available in all implementations. 

This is the code to watch a directory using java.nio (taken from [Docs Oracle](https://docs.oracle.com/javase/tutorial/essential/io/notification.html):
````java
for (;;) {

    // wait for key to be signaled
    WatchKey key;
    try {
        key = watcher.take();
    } catch (InterruptedException x) {
        return;
    }

    for (WatchEvent<?> event: key.pollEvents()) {
        WatchEvent.Kind<?> kind = event.kind();

        // This key is registered only
        // for ENTRY_CREATE events,
        // but an OVERFLOW event can
        // occur regardless if events
        // are lost or discarded.
        if (kind == OVERFLOW) {
            continue;
        }

        // The filename is the
        // context of the event.
        WatchEvent<Path> ev = (WatchEvent<Path>)event;
        Path filename = ev.context();

        // Verify that the new
        //  file is a text file.
        try {
            // Resolve the filename against the directory.
            // If the filename is "test" and the directory is "foo",
            // the resolved name is "test/foo".
            Path child = dir.resolve(filename);
            if (!Files.probeContentType(child).equals("text/plain")) {
                System.err.format("New file '%s'" +
                    " is not a plain text file.%n", filename);
                continue;
            }
        } catch (IOException x) {
            System.err.println(x);
            continue;
        }

        // Email the file to the
        //  specified email alias.
        System.out.format("Emailing file %s%n", filename);
        //Details left to reader....
    }

    // Reset the key -- this step is critical if you want to
    // receive further watch events.  If the key is no longer valid,
    // the directory is inaccessible so exit the loop.
    boolean valid = key.reset();
    if (!valid) {
        break;
    }
}

````

Using a CFS implementation you get this code:
````java
CFileSystem cfs=new FancyFileSystem();
if(cfs.isActiveDirectorySupported()) {
	ActiveDirectory aDir=cfs.getActiveDirectory("SomePath");
	aDir.setOnCreate(this::consumeNew); //Optional if you want create events
	aDir.setOnDelete(this::consumeDelete); //Optional if you want delete events
	aDir.setOnModify(this:consumeModify); //Optionalif you want modify events
	aDir.activate(); //Start watching the directory
}
````

As you can see the CFS solution is much simpler and easy to use. The activation may be optional in some implementations. But it should never be an error to use this method. The reason for the activation is that some implementations may claim ressources you don't want to have used for each active directory but only for some. 

### How to get the CFS

Because this project is currently not in the Maven Central Repository you have to either clone it from git an build it for yourself or wait until i upload prebuild Jars.

#### Requirements for Building

- Java 1.8
- JavaFX
- Maven 3.3.9
