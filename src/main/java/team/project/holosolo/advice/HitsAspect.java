package team.project.holosolo.advice;

import org.aspectj.lang.ProceedingJoinPoint;

import team.project.holosolo.model.service.ContentService;
import team.project.holosolo.model.service.ShareTipService;

public class HitsAspect {
	// fields.
	private ContentService contentService;
	private ShareTipService shareTipService;
	
	// setters.
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	public void setShareTipService(ShareTipService shareTipService) {
		this.shareTipService = shareTipService;
	}
	
	public Object hitsUp(ProceedingJoinPoint pjp) throws Throwable{
		if(pjp.getSignature().getName().equals("contentShowDetail")){
			Object[] params = pjp.getArgs();
			contentService.updateHits(params[0].toString());
		}
		else if(pjp.getSignature().getName().equals("shareShowDetail")){
			Object[] params = pjp.getArgs();
			shareTipService.updateHits(params[0].toString());
		}
		Object returnValues = pjp.proceed();
		
		return returnValues;
	}
}