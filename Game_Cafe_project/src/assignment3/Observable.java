package assignment3;

public interface Observable 
{
	public abstract void subscribe(Observer observer);
	public abstract void unsubscribe(Observer observer);
}
