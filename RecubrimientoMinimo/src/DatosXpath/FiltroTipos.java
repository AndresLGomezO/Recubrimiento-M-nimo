package DatosXpath;

import java.io.File;

import javax.swing.filechooser.*;


public class FiltroTipos extends FileFilter {
	
	private final String ext;
	private final String desc;
	
	
	public FiltroTipos(String ext, String desc) {
		this.ext = ext;
		this.desc = desc;
		
		

	// TODO Auto-generated constructor stub

}


	@Override
	public boolean accept(File f) {
	
		if (f.isDirectory()) {
			return true;
		}
		
		return f.getName().endsWith(ext);
		
	}


	@Override
	public String getDescription() {
		return desc + String.format(" (.xml)", ext);
				
	}

	

}
