package fileManagment;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public final class FontManager {
	
	public static Font getFont(String name, String style, int size){
		InputStream fontStream = FontManager.class.getResourceAsStream("/Fonts/"+name+".TTF");
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
			if(style.equals("Plain"))font = font.deriveFont(Font.PLAIN, size);
			else if(style.equals("Bold"))font = font.deriveFont(Font.BOLD, size);
			fontStream.close();
			return font;
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (Font)null;
	}

}
