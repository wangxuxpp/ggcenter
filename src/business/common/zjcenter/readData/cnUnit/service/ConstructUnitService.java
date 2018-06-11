 package business.common.zjcenter.readData.cnUnit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;

/**
 * 施工单位
 * 
 * @author 王丹
 * @version  1.0  2015-6-17
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("constructUnitService")
@SuppressWarnings({"rawtypes"})
public class ConstructUnitService {

	@Resource(name="dao")
	protected IDAO dao;
	
	/**
	 * 得到施工单位列表信息
	 * 
	 * @return List
	 * 
	 * history
	 * 
	 */
	public List syncUnit(ModelMap returnMap){
		String serviceId = returnMap.get("serviceId").toString();
		String cPro = returnMap.get("cPro").toString();
		String cCity = returnMap.get("cCity").toString();
		String sql = "SELECT T.ID I_SERVICE_ID, T.C_PRO C_OWNER_PRO, T.C_CITY C_OWNER_CITY, T.C_NAME " +
					" FROM SYS_FACTORY T INNER JOIN SYS_CONSTRUCTIONPERMIT T1 ON T.ID = T1.I_SYS_FACTORY_ID  " +
					" WHERE T.C_STATUS = '有效' AND T1.C_STATUS = '有效' and t.id > ? " +
					" and t.c_pro = ? and t.c_city = ? ";
		return dao.executeQuery(sql, new Object[]{serviceId, cPro, cCity});
	}
	
}
