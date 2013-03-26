package za.ac.uct.cs.lwsjam005.eshelf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents an E-Book. For now, all books have the same pages.
 * 
 * @author James Lewis
 * 
 */
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3897497402079237771L;
	/**
	 * Title of book
	 */
	private String title;
	/**
	 * Author of book
	 */
	private String author;
	/**
	 * Format: 'class#division#section'
	 */
	private String classification;
	/**
	 * Publisher of book
	 */
	private String publisher;
	/**
	 * Cost of book using currency locale
	 */
	private double price;
	/**
	 * Collection of search tags for book
	 */
	private String[] tags;
	/**
	 * Display thumbnail (cover)
	 */
	private int thumbailID;
	/**
	 * If true, book is owned by the user and appear on shelf.
	 */
	private boolean owned;
	/**
	 * Current page this book is on
	 */
	private int currentPage;
	/**
	 * ISBN number
	 */
	private String ISBN;
	/**
	 * Rating of 1 to 5
	 */
	private int rating;
	/**
	 * Book's bookmarks
	 */
	private ArrayList<Bookmark> bookmarks;
	/**
	 * If the book is in the cart
	 */
	private boolean inCart;

	public Book(String title, String author, String classification,
			String publisher, double price, String[] tags, int thumbailID,
			boolean owned, int rating) {
		super();
		this.title = title;
		this.author = author;
		this.classification = classification;
		this.publisher = publisher;
		this.price = price;
		this.tags = tags;
		this.thumbailID = thumbailID;
		this.owned = owned;
		this.bookmarks = new ArrayList<Bookmark>();
		this.rating = rating;
		this.inCart = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public int getIcon() {
		return thumbailID;
	}

	public void setIcon(int icon) {
		this.thumbailID = icon;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getISBN() {
		return ISBN;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public ArrayList<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(ArrayList<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public boolean isInCart() {
		return inCart;
	}

	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}

}
