package com.hello.design.iteratorpattern;

public class NameRepository implements Container {
	
	public String[] names = {"tom","jack"};
	
	@Override
	public Iterator iterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator {
		
		int index;

		@Override
		public boolean hasNext() {
			if(index<names.length){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext()){
				return names[index++];
			}
			return null;
		}

	}

}
