package team.project.holosolo.model.dto;

public class GroupDTO {
	// Fields
	private int gno;		//Contents_seq
	private String title;
	private String content; 
	private String category;
	private String promiseDate;  //column promise_date
	private String sexCheck;
	private int currentNum;		//column current_num ... default 1
	private int maxNum;			//column max_num ... default 2
	private int status;			//-1: 취소 , 0: 진행중 , 1: 마감완료	default 0
	private MemberDTO memberDTO;
	//추가
	private String fileName;
	private String latitude;
	private String longitude;
	private String promiseAddr;
	
	// Constructors
	public GroupDTO() {}

	public GroupDTO(int gno, String title, String content, String category,
			String promiseDate, String sexCheck, int currentNum, int maxNum,
			int status, MemberDTO memberDTO, String fileName, String latitude,
			String longitude, String promiseAddr) {
		this.gno = gno;
		this.title = title;
		this.content = content;
		this.category = category;
		this.promiseDate = promiseDate;
		this.sexCheck = sexCheck;
		this.currentNum = currentNum;
		this.maxNum = maxNum;
		this.status = status;
		this.memberDTO = memberDTO;
		this.fileName = fileName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.promiseAddr = promiseAddr;
	}

	// 추가된 생성자
	public GroupDTO(String title, String content, String category,
			String promiseDate, String sexCheck, int maxNum, MemberDTO memberDTO){
		this.title = title;
		this.content = content;
		this.category = category;
		this.promiseDate = promiseDate;
		this.sexCheck = sexCheck;
		this.maxNum = maxNum;
		this.memberDTO = memberDTO;
	}
	
	// Getters() & Setters()
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPromiseDate() {
		return promiseDate;
	}
	public void setPromiseDate(String promiseDate) {
		this.promiseDate = promiseDate;
	}
	public String getSexCheck() {
		return sexCheck;
	}
	public void setSexCheck(String sexCheck) {
		this.sexCheck = sexCheck;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPromiseAddr() {
		return promiseAddr;
	}
	public void setPromiseAddr(String promiseAddr) {
		this.promiseAddr = promiseAddr;
	}

	@Override
	public String toString() {
		return "GroupDTO [gno=" + gno + ", title=" + title + ", content="
				+ content + ", category=" + category + ", promiseDate="
				+ promiseDate + ", sexCheck=" + sexCheck + ", currentNum="
				+ currentNum + ", maxNum=" + maxNum + ", status=" + status
				+ ", memberDTO=" + memberDTO + ", fileName=" + fileName
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", promiseAddr=" + promiseAddr + "]";
	}
	
}
