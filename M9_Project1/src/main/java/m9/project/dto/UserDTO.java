package m9.project.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
	
	private Long userID;
	@NotBlank(message = "Name is required")
	private String name;
	
    private String password;
    
	private Boolean enabled;
	
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
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

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public UserDTO(Long userID, @NotBlank(message = "Name is required") String name, Boolean enabled,String role) {
		this.role = role;
		this.userID = userID;
		this.name = name;
		this.enabled = enabled;
	}
	public UserDTO() {}
}
