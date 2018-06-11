package base.util.pojo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;

public class ImgDispatch {

	private static final int imgWidth = 420;
	private static final int imghigh = 360;
	
	public static void imgTranslate(String src , String des , String frm){
		try{
			File f = new File(src);
			if (!f.exists()){
				return ;
			}
			BufferedImage srcFile = ImageIO.read(f); 
			int srcw = srcFile.getWidth();
			int srch = srcFile.getHeight();
			
			srcw = srcw <= imgWidth ? srcw :imgWidth;
			srch = srch <= imghigh ? srch :imghigh;
			
			Image image = srcFile.getScaledInstance(srcw, srch, Image.SCALE_DEFAULT); 
			BufferedImage tag = new BufferedImage(srcw, srch, BufferedImage.TYPE_INT_RGB); 
			Graphics g = tag.getGraphics(); 
			g.drawImage(image, 0, 0, null); 
			g.dispose(); 
			ImageIO.write(tag, frm, new File(des));//输出到文件流
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
