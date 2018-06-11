package business.common.zjcenter.common.parameter;

import atomic.shareMem.ShareMemoryPojo;



@SuppressWarnings("serial")
public class ConnectParameter extends ShareMemoryPojo {

	public Integer id = -99;
	public Integer factoryId = -99;
	public String server = "";
	public Integer port = 0;
	public String projectName = "";
	
	public String erpToCenterUserName = "";
	public String erpToCenterPassWord = "";
	
	public String centerToErpUserName = "";
	public String centerToErpPassWord = "";
}
