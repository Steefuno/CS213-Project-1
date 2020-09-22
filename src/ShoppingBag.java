package shopping;

public class ShoppingBag {
	
		private GroceryItem[] bag; // array-based implementation of the bag
		private int size; // number of items currently in the bag
		/**
		This constructor sets every instance of the ShoppingBag to size 0
		and the array length to size 5.
		*/
		public ShoppingBag() {
			this.size = 0;
			this.bag = new GroceryItem[5];
		}
		/**
		Finds the item in the list and returns the index in the bag
		returns -1 if the item doesn't appear in the bag
		@param name of the item to be found
		@return if item found returns index otherwise returns -1
		*/
		private int find(GroceryItem item) { 
			for(int i = 0;i < this.bag.length;i++) {
				if(this.bag[i] == null) {
					continue;
				}
				if(this.bag[i].equals(item)) {
					return i;
				}
				
			}
			return -1;
			
		} 
		
		/**
		Grows the item by 5 if the bag goes past its respective capacity
		*/
		private void grow() { 
				GroceryItem[] newBag = new GroceryItem[this.bag.length+5]; 
				System.arraycopy(this.bag, 0, newBag, 0, this.bag.length);
				this.bag = newBag;
			
		}
		
		/**
		Adds an item to the bag in the next available spot if the bag goes past its
		capacity the grow methods is called to increase the capacity to the bag
		@param Grocery item to be added
		*/
		public void add(GroceryItem item) {
			this.size++;
			if(this.size > this.bag.length) {
				this.grow();
			}
			for(int i = 0;i < this.bag.length;i++) {
				if(this.bag[i] == null) {
					this.bag[i] = item;
					break;
				}
			}
			
		}
		
		/**
		Finds the item in the list using the find helper methods. 
		If found returns true and removes by setting the index found to the last element 
		in the list, and sets the last element to null. Otherwise returns 
		false and do nothing
		@param name of the item to be removed
		@return if the item is in the bag return true otherwise return false
		*/
		public boolean remove(GroceryItem item) {
			int index = this.find(item);
			if (index>=0) {
				this.size--;
				this.bag[index] = this.bag[bag.length-1];
				this.bag[this.bag.length-1] = null;
				return true;
			}
			else{
				return false;
			
			}
			

		}
		
		/**
		Calculates the total price of each item in the bag 
		@return Returns the total sale price of the bag 
		*/
		public double salesPrice() {
			double totalSale = 0;
			for(int i = 0;i < bag.length;i++) {
				if(this.bag[i] == null) {
					continue;
				}
				else {
					totalSale += this.bag[i].getPrice();
				}
			}
			return totalSale;
		}
		
		/**
		Calculates the total sale tax of each item in the bag 
		@return Returns the total sale tax price of the bag 
		*/
		public double salesTax() { 
			double totalSaleTax = 0;
			for(int i = 0;i < this.bag.length;i++) {
				if(this.bag[i] == null) {
					continue;
				}
				if(this.bag[i].getTaxable()==true) {
					totalSaleTax += (this.bag[i].getPrice() * 0.06625);
				}
				else {
					continue;
				}
			}
			
			return totalSaleTax;
		}
		
		/**
		Prints the item name first, the sale price second, 
		and if the item is taxable last 
		*/
		public void print() { 
			  for (GroceryItem element: this.bag) {
				  if(element == null) {
					  continue;
				  }
		            System.out.println(element);
		            
		        }
		}
		
		/**
		This method checks the amount of items in the bag by 
		returning the size. To give the amount of items in the bag.
		@return Returns the amount of items in the bag
		*/
		public int getItemCount() {
			return this.size;
		}
		
		/**
		clears all items in the bag by assigning a new 
		bag to the instance of bag, and reseting the size instance to 0 
		*/
		public void clear() {
			GroceryItem[] newBag = new GroceryItem[5]; 
			this.bag = newBag;
			this.size = 0;
		}
		
	/**
	Testbed for implementing the test cases in test document
	*/	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GroceryItem apple = new GroceryItem("Apple",2.12,true);
		GroceryItem skirt = new GroceryItem("Skirt",10.05,false);
		GroceryItem puppy = new GroceryItem("Puppy",500.00,true);
		GroceryItem pencil = new GroceryItem("Pencil",1.00,true);
		
		ShoppingBag x = new ShoppingBag();
		//items in shopping bag
		x.add(apple);
		x.add(apple);
		x.add(skirt);
		x.add(apple);
		x.add(apple);
		x.add(apple);
		x.remove(skirt);
		x.remove(apple);
		x.print();
		
		//tests add if changes size
		System.out.println("size: " + x.size);
		
		//tests print method and if grow method copies elements
		x.print();
		//tests sales price and sales tax method
		System.out.printf("Sales_Price: $%.2f ", x.salesPrice());
		System.out.println();
		System.out.printf("sales_Tax: $%.2f", x.salesTax());
		System.out.println();
		
		//tests if grow method doubles array
		x.add(pencil);
		System.out.println("array_length: " + x.bag.length);
		
		//tests find method:
		//returns index for item in list, if not present -1 returned
		int findSkirt = x.find(skirt);
		System.out.println("skirt index: " + findSkirt);
		//tests if not in list
		int findPuppy = x.find(puppy);
		System.out.println("puppy index: " + findPuppy);
		
		
		//tests if remove method removes from list: 
		//for false
		boolean puppyPresent = x.remove(puppy); 
		System.out.println("puppy in bag: " + puppyPresent);
		//for true
		boolean skirtPresent = x.remove(skirt); 
		System.out.println("skirt in bag: " + skirtPresent);
		//tests remove if changes size
		System.out.println("size: " + x.size);
		//checks if remove put item in right index
		x.print();
		x.add(apple);
		System.out.println("size: " + x.size);
		x.print();
		//tests sales price and sales tax method
		
		System.out.printf("Sales_Price: $%.2f ", x.salesPrice());
		System.out.println();
		System.out.printf("sales_Tax: $%.2f", x.salesTax());
		System.out.println();
		x.clear();
		x.print();
		System.out.println(x.getItemCount());
	}
}

