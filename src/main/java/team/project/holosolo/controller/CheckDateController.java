package team.project.holosolo.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import team.project.holosolo.util.CheckDateThread;

@Component
public class CheckDateController{
	private CheckDateThread checkDateThread;

	public void setCheckDateThread(CheckDateThread checkDateThread) {
		this.checkDateThread = checkDateThread;
	}
	
	@PostConstruct
	public void init(){
		checkDateThread.start();
	}
}
