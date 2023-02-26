package pojos;

import java.time.LocalDate;
import javax.persistence.*;//JPA : Java EE specs

@Entity // cls level anno to tell Hibernate , following class is an entity ,
//whose life cycle is to be managed!
@Table(name = "users_tbl") // default table name will be class name to change table name we use this //
							// annotation
public class User {

	@Id // PK constraint
	// @GeneratedValue //=> by default strategy is auto for whihc ID generation as
	// per hibernate_seq
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// => extra constraint : auto_increment : works as per Mysql sequence
	@Column(name = "user_id") // col name in mysql is by default data member name but to change column name
								// use this annotation
	private Integer userId;
	@Column(name = "first_name", length = 20) // varchar(20) by default varchar(255)
	private String firstName;
	@Column(name = "last_name", length = 20) // varchar(20)
	private String lastName;
	@Column(length = 30, unique = true) // => by defalt unique is false not make following data member unique constraint
	private String email;
	@Column(length = 20, nullable = false) // => NOT NULL constraint
	private String password;
	@Transient // => skip from persistence : no col generation
	private String confirmPassword;
	@Enumerated(EnumType.STRING) // => by default enum data type saved as int in mysql in which ordinal pos get
									// saved
									// to make col : varchar => enum const name
	@Column(name = "user_role", length = 20)
	private Role userRole;
	@Column(name = "reg_amount")
	private double regAmount;
	@Column(name = "reg_date")
	private LocalDate regDate; // localdate gets matched with mysql date automatically
	@Lob // => by defalt byte[] save as tinyBlob to make long col type : longblob for
			// Mysql
	private byte[] image;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, String password, String confirmPassword, Role userRole,
			double regAmount, LocalDate regDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.userRole = userRole;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", userRole=" + userRole + ", regAmount=" + regAmount + ", regDate=" + regDate + "]";
	}

}
