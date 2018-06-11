package atomic.shareMem;

import redis.ShareMemory;
import redis.ShareMemoryParameter;
import redis.shareMemory.UseInterface.ShareResource;

public class ShareMem extends ShareResource{
	
	public ShareMem(ShareMemoryParameter fp , boolean startMemModel){
		if (getFmem() == null){
			if ((fp != null) && startMemModel){
				setFmem(new ShareMemory());
				getFmem().setFparam(fp);
			}	
		}
	}
	
}
