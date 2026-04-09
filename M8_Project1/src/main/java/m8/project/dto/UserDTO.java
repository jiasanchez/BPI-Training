package m8.project.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
	
	private Long userID;
	@NotBlank(message = "Name is required")
	private String name;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserDTO(Long userID, String name) {
		this.userID = userID;
		this.name = name;
	}
	public UserDTO() {}
}
