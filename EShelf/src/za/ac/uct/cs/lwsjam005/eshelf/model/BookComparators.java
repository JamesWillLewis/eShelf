package za.ac.uct.cs.lwsjam005.eshelf.model;

import java.util.Comparator;

public class BookComparators {

	public static class CompareTitle implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			return lhs.getTitle().compareTo(rhs.getTitle());
		}

	}

	public static class CompareAuthor implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			return lhs.getAuthor().compareTo(rhs.getAuthor());
		}

	}

	public static class CompareClassification implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			return lhs.getClassification().compareTo(rhs.getClassification());
		}

	}

	public static class ComparePrice implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			if (lhs.getPrice() < rhs.getPrice())
				return -1;
			else
				return 1;
		}

	}

	public static class ComparePublisher implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			return lhs.getPublisher().compareTo(rhs.getPublisher());
		}

	}

	public static class CompareDate implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			return 0;
		}

	}

	public static class CompareOwned implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			if (lhs.isOwned() && !rhs.isOwned())
				return -1;
			else if (!lhs.isOwned() && rhs.isOwned())
				return 1;
			else
				return 0;
		}

	}
	
	public static class CompareRead implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			if(lhs.getCurrentPage() < rhs.getCurrentPage())
				return -1;
			else 
				return 1;
		}

	}
	
	public static class CompareRating implements Comparator<Book> {

		@Override
		public int compare(Book lhs, Book rhs) {
			if(lhs.getRating() < rhs.getRating())
				return -1;
			else 
				return 1;
		}

	}
}
