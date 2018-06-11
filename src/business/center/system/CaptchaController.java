package business.center.system;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import base.baseinterface.BaseController;
import base.common.Constant;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 验证码生成器
 * 
 * @version  1.0  Aug 31, 2010
 * 
 * 版权所有(C) 辽宁申瑞软件科技有限公司
 * copyright(C) LiaoNing Sunray Software Technology co., Ltd
 * 
 * history:
 *
 */
@Controller
@RequestMapping(value="/captcha.hm")
public class CaptchaController extends BaseController {
	
	private Random generator = new Random();
	private int width = 100;
	private int height = 40;
	private int codeCount = 4;
	
	private static char[] captchars = new char[]{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
										'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
										'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	/** 
	 * 生成随机的扭曲验证码
	 */ 
	@RequestMapping(params="method=getCaptcha")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int car = captchars.length - 1;
		StringBuffer test = new StringBuffer();
		ServletOutputStream outputStream = response.getOutputStream();
		
		//得到输出流 
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
		BufferedImage bi = new BufferedImage(width+20, height, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D graphics = bi.createGraphics(); 
		
		//设置背景色，并生成边框
		int w = bi.getWidth();
		int h = bi.getHeight();
		graphics.setColor(Color.white);//设置背景色
		graphics.fillRect(0, 0, w, h); 
		
		char temp;
		int red,green,blue,x=width/(codeCount);
		for (int i = 0; i < codeCount; i++) {
			temp = captchars[generator.nextInt(car) + 1];
			test.append(temp);
			graphics.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
			graphics.setColor(Color.black);//设置验证码字体颜色
			graphics.drawString(String.valueOf(temp), x*i+10, height-10);
		}
		
		//产生100个干扰点
		BasicStroke stroke = new BasicStroke(1);
		int px,py,yl,xl;
		for(int i = 0; i < 10; i++){
			px = generator.nextInt(w);
			py = generator.nextInt(h);
			xl = generator.nextInt(w);
			yl = generator.nextInt(h);
			graphics.setStroke(stroke);
			
			red = generator.nextInt(255);
			green = generator.nextInt(255);
			blue = generator.nextInt(255);
			graphics.setColor(new Color(red,green,blue));//设置验证码字体颜色
			graphics.drawLine(px, py, xl, yl);
		}
		
		request.getSession().setAttribute(Constant.CAPCHA_SESSION_KEY, test.toString().toUpperCase());
		
		response.setContentType("image/jpg"); 
		encoder.encode(bi); 
		outputStream.close();
		outputStream.flush();
	} 

}
