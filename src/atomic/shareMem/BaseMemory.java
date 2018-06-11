package atomic.shareMem;

import base.util.FactoryStatic;

public abstract class BaseMemory {

	protected String shareMemKey = "";
	
	protected ShareMem getMem(){
		return FactoryStatic.getShareMem();
	}
	
	public void clear(){
		getMem().setObject(shareMemKey, null);
		pClear();
	}
	
	public abstract void pClear();
	
	protected Object get(){
		return getMem().getObject(shareMemKey);
	}
	
	protected void put(Object obj){
		getMem().setObject(shareMemKey, obj);
	}
	
}
