package lab2_GuestBook;

public class GuestBookModel {
	private String user;
	private String headline;
	private String text;
	
	public GuestBookModel() {
		// TODO Auto-generated constructor stub
	}
	
//DEFAULT GETTER/SETTER METHODS	
	
	public GuestBookModel(String user, String headline, String text) {
		super();
		this.user = user;
		this.headline = headline;
		this.text = text;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

//EQUALS METHOD:

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headline == null) ? 0 : headline.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuestBookModel other = (GuestBookModel) obj;
		if (headline == null) {
			if (other.headline != null)
				return false;
		} else if (!headline.equals(other.headline))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
//toString METHOD
	
}
