package business.common.clearMem;

import business.common.authorityMem.movable.MovableRoleMem;
import business.common.authorityMem.web.WebRoleMem;

/**
 * 清除系统缓存类
 * 
 * @author 王丹
 * @version  1.0  2015-6-27
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
public class ClearMem {
	
	private static WebRoleMem webRoleMem;
	private static MovableRoleMem movableRoleMem;
	
	/**
	 * 清除web角色缓存
	 * 
	 * @param map void
	 * 
	 * history
	 * 
	 */
	public static void clearWebRole(Integer factoryId, Integer RoleId){
		if (webRoleMem == null){
			webRoleMem = WebRoleMem.getFMem();
		}
		webRoleMem.setMemKey(factoryId, RoleId);
		webRoleMem.clear();
	}

	/**
	 * 清除移动角色缓存
	 * 
	 * @param map void
	 * 
	 * history
	 * 
	 */
	public static void clearMovableRole(Integer factoryId, Integer RoleId){
		if (movableRoleMem == null){
			movableRoleMem = MovableRoleMem.getFMem();
		}
		movableRoleMem.setMemKey(factoryId, RoleId);
		movableRoleMem.clear();
	}

}
