package team.project.holosolo.model.dto;

public class MemberDTO {
	// Fields
	private String id;
	private String password;
	private String name;
	private String sex;				// M or W
	private String email;
	private boolean authority;		//권한
	// 추가
	private String myPhoto;		//이미지 파일 명
	
	// Constructors
	public MemberDTO() {}
	
	public MemberDTO(String id, String password) { //로그인 시
		this.id = id;
		this.password = password;
	}

	public MemberDTO(String id, String password, String name, String sex,
			String email, boolean authority, String myPhoto) {
		this(id, password);
		this.name = name;
		this.sex = sex;
		this.email = email;
		this.authority = authority;
		this.myPhoto = myPhoto;
	}
	
   // 수정용
   public MemberDTO(String id, String password, String email) {
      this.id = id;
      this.password = password;
      this.email = email;
   }

	// Setters & Getters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAuthority() {
		return authority;
	}
	public void setAuthority(boolean authority) {
		this.authority = authority;
	}
	public String getMyPhoto() {
		return myPhoto;
	}
	public void setMyPhoto(String myPhoto) {
		this.myPhoto = myPhoto;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", password=" + password + ", name="
				+ name + ", sex=" + sex + ", email=" + email + ", authority="
				+ authority + ", myPhoto=" + myPhoto + "]";
	}
}
