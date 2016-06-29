package xyz.pabloblanco.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Writer {

	/**
	 * Write the files in the specified path
	 * @param files to save
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void writeFiles(List<File> files) throws FileNotFoundException, IOException;
	
}
