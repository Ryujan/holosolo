package team.project.holosolo.model.dto;

public class ShareTipCommentDTO {
	// Fields
	private int rno;		//contents_review_seq
	private String reply;
	private String postedTime;  //column posted_time
	private int sno;			//column sno(FK)
	private MemberDTO memberDTO;
	
	// Constructors
	public ShareTipCommentDTO(){}

	public ShareTipCommentDTO(int rno, String reply, String postedTime,
			int sno, MemberDTO memberDTO) {
		this.rno = rno;
		this.reply = reply;
		this.postedTime = postedTime;
		this.sno = sno;
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	@Override
	public String toString() {
		return "ShareTipCommentDTO [rno=" + rno + ", reply=" + reply
				+ ", postedTime=" + postedTime + ", sno=" + sno
				+ ", memberDTO=" + memberDTO + "]";
	}
}
