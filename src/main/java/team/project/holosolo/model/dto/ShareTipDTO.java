package team.project.holosolo.model.dto;

public class ShareTipDTO {
	// Fields
	private int sno; // Contents_seq
	private String title;
	private String content;
	private String category;
	private String postedTime; // column posted_time
	// 여기에 org, new
	private int hits;
	private MemberDTO memberDTO;
	private String fileName;

	// Constructors
	public ShareTipDTO() {
	}

	public ShareTipDTO(int sno, String title, String content, String category,
			String postedTime, int hits, MemberDTO memberDTO, String fileName) {
		this.sno = sno;
		this.title = title;
		this.content = content;
		this.category = category;
		this.postedTime = postedTime;
		this.hits = hits;
		this.memberDTO = memberDTO;
		this.fileName = fileName;
	}

	// Getters() & Setters()
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
		return "ShareTipDTO [sno=" + sno + ", title=" + title + ", content="
				+ content + ", category=" + category + ", postedTime="
				+ postedTime + ", hits=" + hits + ", memberDTO=" + memberDTO
				+ ", fileName=" + fileName + "]";
	}
}
