package generics;

public interface Receiver<M, V> 
{
	V receive(M message);
}
