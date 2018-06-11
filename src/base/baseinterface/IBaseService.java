package base.baseinterface;



import org.springframework.ui.ModelMap;

import atomic.dao.IDAO;

/**
 * 系统中基础的业务类接口
 * 
 * @author 王丹
 * @version  Mar 15, 2014
 * 
 * 修改
 * @author wx
 * @version  2014-3-20
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public interface IBaseService {

	ModelMap list(ModelMap requestModel);
	
	ModelMap saveCheck(ModelMap requestModel);
	
	ModelMap save(ModelMap requestModel);
	
	ModelMap deleteCheck(ModelMap requestModel);
	
	ModelMap delete(ModelMap requestModel);
	
	ModelMap regainCheck(ModelMap requestModel);
	
	ModelMap regain(ModelMap requestModel);
	
	ModelMap get(ModelMap requestModel);
	
	ModelMap updateCheck(ModelMap requestModel);
	
	ModelMap update(ModelMap requestModel);
	
	ModelMap precedeItem(ModelMap requestModel);
	
	ModelMap nextItem(ModelMap requestModel);
	
	IDAO getDao();
}
