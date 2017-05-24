import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {
    
	private final String extension;
	private final String description;
	
	public FileTypeFilter (String extension, String Description)
	{
		this.extension = extension;
		this.description = Description;
	}
	@Override
	public boolean accept(File f) {
		if(f.isDirectory())
		{
			return true;
		}
		return f.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description + String.format("(*%s)", extension);
	}

}