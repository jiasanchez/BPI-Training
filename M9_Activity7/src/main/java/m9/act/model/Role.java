package m9.act.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	   @Id 
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;

	   @ManyToOne
	   @JoinColumn(name = "user_id")
	   private User user;
	   private String role;
	   public Long getId() {
		   return id;
	   }
	   public void setId(Long id) {
		   this.id = id;
	   }
	   public User getUser() {
		   return user;
	   }
	   public void setUser(User user) {
		   this.user = user;
	   }
	   public String getRole() {
		   return role;
	   }
	   public void setRole(String role) {
		   this.role = role;
	   }
	   public Role(Long id, User user, String role) {
		this.id = id;
		this.user = user;
		this.role = role;
	   }
	   public Role() {}

}
