package team.project.holosolo.model.dto;

public class GroupCommentDTO {
	// Fields
	private int rno;		//contents_review_seq
	private String reply;
	private String postedTime;  //column posted_time
	private int gno;			//column gno(FK)
	private MemberDTO memberDTO;
	
	// Constructors
	public GroupCommentDTO() {}
	
	public GroupCommentDTO(int rno, String reply, String postedTime, int gno,
			MemberDTO memberDTO) {
		this.rno = rno;
		this.reply = reply;
		this.postedTime = postedTime;
		this.gno = gno;
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
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	@Override
	public String toString() {
		return "GroupCommentDTO [rno=" + rno + ", reply=" + reply
				+ ", postedTime=" + postedTime + ", gno=" + gno
				+ ", memberDTO=" + memberDTO + "]";
	}
}
