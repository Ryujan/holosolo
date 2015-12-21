package team.project.holosolo.model.dto;

public class ContentDTO {
	// Fields
	private int cno;		//Contents_seq
	private String title;
	private String content;
	private String category;
	private String postedTime;  //column posted_time
	private int hits;
	private MemberDTO memberDTO;
	//추가
	private String fileName;	

	// Constructors
	public ContentDTO() {}

	public ContentDTO(int cno, String title, String content, String category,
			String postedTime, int hits, MemberDTO memberDTO, String fileName) {
		this.cno = cno;
		this.title = title;
		this.content = content;
		this.category = category;
		this.postedTime = postedTime;
		this.hits = hits;
		this.memberDTO = memberDTO;
		this.fileName = fileName;
	}

	// Getters() & Setters()
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
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
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
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

	@Override
	public String toString() {
		return "ContentDTO [cno=" + cno + ", title=" + title + ", content="
				+ content + ", category=" + category + ", postedTime="
				+ postedTime + ", hits=" + hits + ", memberDTO=" + memberDTO
				+ ", fileName=" + fileName + "]";
	}
}
