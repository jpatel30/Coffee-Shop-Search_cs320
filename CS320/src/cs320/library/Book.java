package cs320.library;

public class Book {

	private int id;
	private String title;
	private int copies;
	private String borrowed;
	private String studentName;
	private int borrowerId;
	
	public Book(int id, String title, int copies) {
		
		this.id =id;
		this.title = title;
		this.copies = copies;
	}
	
	public Book(int id, String title, int copies, String borrowed,
			String studentName, int borrowerId) {
		
		this.id = id;
		this.title = title;
		this.copies = copies;
		this.borrowed = borrowed;
		this.studentName = studentName;
		this.borrowerId = borrowerId;
	}
	
	public int getBorrowerId() {
		return borrowerId;
	}

	public void setBorrowerId(int borrowerId) {
		this.borrowerId = borrowerId;
	}

	public String getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}
	
}
