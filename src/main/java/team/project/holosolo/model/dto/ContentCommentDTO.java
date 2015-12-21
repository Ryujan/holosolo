package team.project.holosolo.model.dto;

public class ContentCommentDTO {
	// Fields
	private int rno;		//contents_review_seq
	private String reply;
	private String postedTime;  //column posted_time
	private int cno;			//column cno(FK)
	private MemberDTO memberDTO;
	
	// Constructors
	public ContentCommentDTO() {}

	public ContentCommentDTO(int rno, String reply, String postedTime, int cno,
			MemberDTO memberDTO) {
		this.rno = rno;
		this.reply = reply;
		this.postedTime = postedTime;
		this.cno = cno;
		this.memberDTO = memberDTO;
	}

	// Getters() & Setters()
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(String postedTime) {
		this.postedTime = postedTime;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	@Override
	public String toString() {
		return "ContentCommentDTO [rno=" + rno + ", reply=" + reply
				+ ", postedTime=" + postedTime + ", cno=" + cno
				+ ", memberDTO=" + memberDTO + "]";
	}
}
