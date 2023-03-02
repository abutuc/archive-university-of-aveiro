import java.util.*;

public class VectorGeneric<T>{
	private T[] vec;		
	private int nElem;	      
	private final static int ALLOC = 50;   
	private int dimVec = ALLOC;     

	@SuppressWarnings("unchecked")
	public VectorGeneric() {
		vec = (T[]) new Object[dimVec];
		nElem = 0;
	}
	
	public boolean addElem(T elem) {
		if (elem == null)
			return false;
		ensureSpace();
		vec[nElem++] = elem;
		return true;
	}

	private void ensureSpace() {
		if (nElem>=dimVec) {
			dimVec += ALLOC;
			@SuppressWarnings("unchecked")
			T[] newArray = (T[]) new Object[dimVec];
			System.arraycopy(vec, 0, newArray, 0, nElem );
			vec = newArray;
		}
	}

	public boolean removeElem(T elem) {
		for (int i = 0; i < nElem; i++) {
			if (vec[i].equals(elem)) {
				if (nElem-i-1 > 0) // not last element
					System.arraycopy(vec, i+1, vec, i, nElem-i-1 );
				vec[--nElem] = null; // libertar último objecto para o GC
				return true;
			}
		}
		return false;
	}

	public int totalElem() {
		return nElem;
	}
	
	public T getElem(int i) {
		return (T) vec[i];
	}

	public Iterator<T> Iterator(){
		return (this).new VectorIterator<T>();
	}
	
	public ListIterator<T> listIterator(){
		return (this).new VectorListIterator<T>();
	}
	
	public ListIterator<T> listIterator(int index){ // start at index
		return (this).new VectorListIterator<T>(index);
	}

	private class VectorIterator<T> implements Iterator<T>{
		private int indice=0;
		@Override
		public boolean hasNext() {
			return (indice < nElem);
		}
		@Override
		public T next() {
			if (hasNext())
				return (T) VectorGeneric.this.vec[indice++];
			throw new NoSuchElementException("only " + nElem + " elements");
		}

		
	}

	private class VectorListIterator<T> implements ListIterator<T>{
		private int index;

		public VectorListIterator(){
			this.index = 0;
		}
		public VectorListIterator(int index){
			this.index = index;
		}
		@Override
		public boolean hasNext() {
			return (index < nElem);
		}
		@Override
		public T next() {
			if (hasNext())
				return (T) VectorGeneric.this.vec[index++];
			throw new NoSuchElementException("only " + nElem + " elements");
		}

		@Override
		public boolean hasPrevious() {
			return (index >= 0);
		}
		@Override
		public T previous() {
			if (hasPrevious())
				return (T) VectorGeneric.this.vec[index--];
			throw new NoSuchElementException("only " + nElem + " elements");
		}

		@Override
		public int nextIndex() {
			return index + 1;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			
		}	
	}
}


