package com.silenistudios.silenus.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.silenistudios.silenus.StreamFactory;

/**
 * This factory just keeps all files in memory and emulates a temporary file system.
 * @author Karel
 *
 */
public class MemoryStreamFactory implements StreamFactory {
	
	// map of string (filename) to byte array
	Map<String, MemoryFile> fFiles = new HashMap<String, MemoryFile>();
	

	@Override
	public OutputStream createOutputStream(File file) throws IOException {
		
		// create a new memory file and set it
		MemoryFile memoryFile = new MemoryFile(file.getPath());
		fFiles.put(file.getPath(), memoryFile);
		return new MemoryOutputStream(memoryFile);
	}

	@Override
	public InputStream createInputStream(File file) throws IOException {
		
		// does not exist :(
		if (!exists(file)) throw new IOException("File not found: " + file.getPath());
		
		// create a new memory file and set it
		MemoryFile memoryFile = fFiles.get(file.getPath());
		return new MemoryInputStream(memoryFile);
	}

	@Override
	public boolean exists(File file) {
		return fFiles.containsKey(file.getPath()) && fFiles.get(file.getPath()).isSet();
	}

}