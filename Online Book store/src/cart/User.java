package cart;

public class User {
private int price;
private String books;

public  User(String books, int price) {
	this.books=books;
	this.price=price;
}
public String getbooks() {
	return books;}
public int getprice() {
	return price;
}
}
