package cs320.midterm;

public class Quotation {
	
	
	private String quote;
	private String author;
	private int count;
	
	public Quotation(String quote, String author, int count) {
		this.quote = quote;
		this.author = author;
		this.count = count;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
