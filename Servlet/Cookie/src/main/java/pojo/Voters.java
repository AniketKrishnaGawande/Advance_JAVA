package pojo;

public class Voters {
	private String uname;
	private String password;
	private String status;

	public Voters() {
		uname = "";
		password = "";
		status = "";
	}

	public Voters(String unm, String psw, String status) {
		uname = unm;
		password = psw;
		this.status = status;

	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Voters [uname=" + uname + ", status=" + status + "]";
	}


}
