package business.cn.backMember.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;
import business.common.zjcenter.common.HttpSend;

/**
 * 退货单
 * 
 * @author 王丹
 * @version  1.0  2015-6-19
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@Service("backMemberService")
@SuppressWarnings({"rawtypes" , "unchecked"})
public class BackMemberService {

	@Resource(name="dao")
	protected IDAO dao;
	
	/**
	 * TODO
	 * 
	 * @param requestModel
	 * @return ModelMap
	 * 
	 * history
	 * 
	 */
	public ModelMap getDateList(ModelMap requestModel){
		Map map = new HashMap();
		map.put("serviceId", 1);
		Map dataMap = HttpSend.httpSend("backMember.action?method=list", map, "1");
		if(dataMap == null){
			return null;
		}
		return null;
	}
}
