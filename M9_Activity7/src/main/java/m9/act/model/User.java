package m9.act.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	   @Id 
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   private String username;
	   private String password;
	   private Boolean enabled;
	   public Long getId() {
		   return id;
	   }
	   public void setId(Long id) {
		   this.id = id;
	   }
	   public String getUsername() {
		   return username;
	   }
	   public void setUsername(String username) {
		   this.username = username;
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
	   public User(Long id, String username, String password, Boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	   }
	   
	   public User() {}
	   
}
