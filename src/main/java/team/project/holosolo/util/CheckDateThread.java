package team.project.holosolo.util;

import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.service.GroupService;

public class CheckDateThread extends Thread{
	private GroupService groupService;

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println("Thread Start!");
				List<GroupDTO> list = groupService.getGroupInLastDate();
				for(GroupDTO gdto : list){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("gno", String.valueOf(gdto.getGno()));
					if(gdto.getCurrentNum() == 1){
						map.put("status", "-1"); // 취소됨을 의미함.
						groupService.groupCancel(String.valueOf(gdto.getGno())); // member_groups 내의 모든 내용 삭제.
					}
					else{
						map.put("status", "1"); // 완료됨을 의미함.
					}
					
					int result = groupService.updateStatus(map);
				}
				// 1일간 잠잔다.
				sleep(24*60*60*1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
