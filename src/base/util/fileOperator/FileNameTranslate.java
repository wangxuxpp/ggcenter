package base.util.fileOperator;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileNameTranslate {
	public class FileName {
		public String fileName;
		public String fileExtend;
		public String fileNameAndExtend;
		public String filePath;
		public String filePathAndName;
		public FileName()
		{
			fileName ="";
			fileExtend="";
			fileNameAndExtend="";
			filePath="";
			filePathAndName="";
		}
	}
	

		public FileName getFromRequest(HttpServletRequest request , String fileKey)
		{
			FileName f = new FileName();
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			CommonsMultipartFile cfile = (CommonsMultipartFile) multipartRequest.getFile(fileKey);
			String name = cfile.getOriginalFilename();
			f.fileName =name.substring(0 , name.lastIndexOf("."));
			f.fileExtend=name.substring(name.lastIndexOf(".")+1);
			f.fileNameAndExtend=name;
			f.filePath="";
			f.filePathAndName="";
			return f;
		}
		public FileName getFromRequest(HttpServletRequest request)
		{
			return getFromRequest(request ,"FILE_PATH");
		}
		public FileName getFromString(String fileName)
		{
			FileName f = new FileName();
			if (fileName.lastIndexOf("\\")>0)
			{
				f.fileNameAndExtend = fileName.substring(fileName.lastIndexOf('\\')+1);
				f.filePath=fileName.substring( 0 , fileName.lastIndexOf('\\'));
				f.filePathAndName=fileName;
			} else {
				f.fileNameAndExtend = fileName;
			}

			f.fileName =f.fileNameAndExtend.substring(0 , f.fileNameAndExtend.lastIndexOf("."));
			f.fileExtend=f.fileNameAndExtend.substring(f.fileNameAndExtend.lastIndexOf(".")+1);

			return f;
		}

}
