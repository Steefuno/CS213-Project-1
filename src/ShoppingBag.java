public class ShoppingBag {
	
		private GroceryItem[] bag; // array-based implementation of the bag
		private int size; // number of items currently in the bag
		
		public ShoppingBag() {
			this.size = 0;
			this.bag = new GroceryItem[5];
		}
		private int find(GroceryItem item) { 
			for(int i = 0;i<this.bag.length;i++) {
				if(this.bag[i] == null) {
					continue;
				}
				if(this.bag[i].equals(item)) {
					return i;
				}
				
			}
			return -1;
			
		} // helper method to find an item
		private void grow() { 
				GroceryItem[] newBag = new GroceryItem[this.bag.length+5]; 
				System.arraycopy(this.bag, 0, newBag, 0, this.bag.length);
				this.bag = newBag;
			
		} // helper method to grow the capacity
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
		public boolean remove(GroceryItem item) {
			int index = this.find(item);
			System.out.println("index: " + index);
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
		public double salesPrice() {
			double totalSale = 0;
			for(int i = 0;i<bag.length;i++) {
				if(this.bag[i] == null) {
					continue;
				}
				else {
					totalSale += this.bag[i].getPrice();
				}
			}
			return totalSale;
		}
		public double salesTax() { 
			double totalSaleTax = 0;
			for(int i = 0;i<this.bag.length;i++) {
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
		public void print() { 
			  for (GroceryItem element: this.bag) {
				  if(element == null) {
					  continue;
				  }
		            System.out.println(element);
		            
		        }
		}
		public int getItemCount() {
			
			return this.size;
		}
		public void clear() {
			GroceryItem[] newBag = new GroceryItem[5]; 
			this.bag = newBag;
			this.size = 0;
		}
		
		

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
