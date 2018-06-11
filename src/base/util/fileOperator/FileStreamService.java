package base.util.fileOperator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import atomic.exception.SystemException;

public class FileStreamService {

	/**
	 * 上传文件
	 * 
	 * @param request 请求
	 * @param fileKey 请求文件所使用的KEY
	 * @param DesFileName 目标路径文件名 
	 * @return String     全路径文件名 
	 * 
	 * history
	 *
	 */
	public String fileUpLoad(HttpServletRequest request , String fileKey , String DesFileName){
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		CommonsMultipartFile cfile = (CommonsMultipartFile) multipartRequest.getFile(fileKey);
		File fo = null;
		try {
			fo = new File(DesFileName );
			cfile.getFileItem().write(fo);
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		return DesFileName;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param fileKey
	 * @param desFilePath
	 * @param DesFileName
	 * @return String
	 */
	public String fileUpLoad(HttpServletRequest request, String fileKey, String desFilePath, String DesFileName ){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile cfile = (CommonsMultipartFile) multipartRequest.getFile(fileKey);
		File dir = new File(desFilePath+File.separator);
		if (!dir.exists()){
			dir.mkdirs();
		}
		String fileName = cfile.getOriginalFilename();
		String fix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		fileName = desFilePath+DesFileName+fix;
		File fo = null;
		try{
			fo = new File(fileName);
			cfile.getFileItem().write(fo);
		}catch(Exception e){
			throw new SystemException(e.getMessage());
		}
		return fileName;
	}
	
	/**
	 * 文件下载
	 * 
	 * @param response
	 * @param filePath 服务器文件路径
	 * @param fileName 服务器文件名
	 * @param saveFileName 目标文件名
	 * @throws IOException
	 */
	public void fileDownLoad(HttpServletResponse response , String filePath , String fileName , String saveFileName) throws IOException{
		InputStream fis = null;
		try{
			File file = new File(filePath + fileName);
			if(!file.exists()){
				throw new SystemException("文件不存在");
			}
			fis = new BufferedInputStream(new FileInputStream(filePath+fileName));
			String f = saveFileName.equals("") ? fileName : saveFileName;
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="+ new String(f.getBytes("GB2312"), "ISO-8859-1"));
			response.setContentType("application/" + fileName.substring(fileName.lastIndexOf(".") + 1));
			FileCopyUtils.copy(fis, response.getOutputStream());
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileName void
	 */
	public void fileDel(String fileName){
		File file = new File(fileName);
		if (file.exists()){
			file.delete();
		}
	}
}
