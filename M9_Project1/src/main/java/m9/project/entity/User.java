package m9.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID; // DB-generated id 
    
    @Column(nullable = false, length = 200, unique = true)    
    private String name;

	@Column(nullable = false) 
    @JsonIgnore
    private String password;
    
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name ="role_id")
	private Role role;
	
    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public User(Long userID, String name) {
		this.userID = userID;
		this.name = name;
	}
	
	public User(Long userID, String name, String password, Boolean enabled) {
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.enabled = enabled;
	}

	public User() {}
    
}
