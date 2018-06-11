package business.common.shareMem;

import org.springframework.stereotype.Service;

import business.common.authorityMem.movable.MovableRoleMem;
import business.common.authorityMem.web.WebRoleMem;

/**
 * 缓存对象管理
 * 
 * @author wx
 * @version  1.0  2014-7-24
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */

@Service("memObject")
public class MemObject {

	public MovableRoleMem getMovableRoleMem() {
		return MovableRoleMem.getFMem();
	}

	public WebRoleMem getWebRoleMem() {
		return WebRoleMem.getFMem();
	}
	
}
