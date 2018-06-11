package business.common.zjcenter.common.parameter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.common.Constant;
import base.exception.CenterException;
import base.util.FactoryStatic;
import atomic.dao.IDAO;
import atomic.shareMem.BaseMemory;
@SuppressWarnings({"rawtypes" , "unchecked"})
public class CenterConnectParameter extends BaseMemory {

	private Map<String , ConnectParameter> fmap = new HashMap<String , ConnectParameter>();
	public CenterConnectParameter()
	{
		shareMemKey = "connect_erpuserlist";
	}
	private static CenterConnectParameter fobj = null;
	public static CenterConnectParameter getObj()
	{
		if (fobj == null)
		{
			fobj = new CenterConnectParameter(); 
		}
		return fobj;
	}
	
	
	public void readInfo()
	{
		if (this.get() == null)
		{
			String str = "select a.*"
					+" from sys_erpfactory a"
					+" inner join sys_factory b"
					+" on a.i_sys_factory_id = b.id"
					+" where b.c_status ='有效'";
			List l = getDao().executeQuery(str, null);
			for (int i = 0 ; i<l.size() ; i++)
			{
				ConnectParameter para = new ConnectParameter();
				Map m = (Map)l.get(0);
				para.id = Integer.valueOf(m.get("ID").toString());
				para.factoryId = Integer.valueOf(m.get("I_SYS_FACTORY_ID").toString());
				para.server =m.get("C_ERPURL").toString(); 
				para.port = Integer.valueOf(m.get("I_PORT").toString());
				para.projectName =m.get("C_PROJECT").toString(); 
				
				para.erpToCenterPassWord =m.get("C_PASSWORD").toString();
				para.erpToCenterUserName =m.get("C_USER").toString();
				
				para.centerToErpPassWord =m.get("C_ERPPASSWORD").toString();
				para.centerToErpUserName =m.get("C_ERPUSER").toString();
				fmap.put(para.id.toString(), para);
			}
			put(fmap);
		} else {
			if(fmap.size()<=0)
			{
				fmap = (Map)get();
			}
		}
		
	}
	

	private static IDAO getDao()
	{
		return FactoryStatic.getDao();
	}
	
	public Map<String , ConnectParameter> getAll()
	{
		readInfo();
		return fmap;
	}
	public ConnectParameter get(String id)
	{
		readInfo();
		if (fmap.containsKey(id))
		{
			return fmap.get(id);
		}
		return null;
	}

	@Override
	public void pClear() {
		// TODO Auto-generated method stub
		fmap.clear();	
	}


	public Boolean erpCheck(String connectType , String factoryId , String userName , String userPassword)
	{
		ConnectParameter p = get(factoryId);
		if (p == null)
		{
			throw new CenterException(Constant.ERPCONNECTERROR+"用户不存在");
		}
		if(!connectType.equals("erpuser"))
		{
			throw new CenterException(Constant.ERPCONNECTERROR+"登录类型不匹配");
		}
		if(!p.erpToCenterUserName.equals(userName))
		{
			throw new CenterException(Constant.ERPCONNECTERROR+"用户名称不正确");
		}
		if(!p.erpToCenterPassWord.equals(userPassword))
		{
			throw new CenterException(Constant.ERPCONNECTERROR+"密码不正确");
		}
		return true;
	}
	
	
}
