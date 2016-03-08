//Kyler Kiminki
//kkiminki@ucsc.edu
//cmps 101
//6/23/15
//List.java
//Creates an ADT for a doubly linked list structure


class List{
   
   //Node class
   class node{
      Object item;
      node next;
      node prev;

      public String toString(){
         return item.toString();
      }

      public boolean equals(node n){
         if(this.item == n.item)
            return true;
         return false;
      }
   }

   //private members
   private node cursor;
   private node current;
   private node first;
   private node last;

//Constructor

   List(){
      this.cursor = null;
      this.current = null;
      this.first = null;
      this.last = null;
   }

//Access functions

   //checks length of list   
   int length(){
      int counter = 0;
      this.current = this.first;
      while(this.current != null) {
         counter++;
         this.current = this.current.next;
      }
   return counter;
   }
   
   //checks index of cursor
   int index(){
      if(cursor == null){
         return -1;
      }else{
         this.current = this.first;
         int counter = 0;
         while(current != cursor){
            counter++;
            this.current = this.current.next;
         }
      return counter;
      }
   }

   //Returns the string at the start of the list
   Object front(){
      if(this.length() == 0){
         throw new RuntimeException(
            "front(): List is empty");
      }
      return this.first.item;
   }

   //Returns the string at the end of the list
   Object back(){
      if(this.length() == 0){
         throw new RuntimeException(
            "back(): List is empty");
      }
      return this.last.item;
   }

   //Returns the string at the cursor
   Object get(){
      if(this.length() == 0 || this.cursor == null){
         throw new RuntimeException(
            "get(): List is empty or cursor is not defined");
      }
      return this.cursor.item;
   }

   //Returns if this list and List L are the same
   boolean equals(List L){
      if(this.length() != L.length()){
         return false;
      }else{
         this.current = this.first;
         L.current = L.first;
         while(this.current != null){
            if(this.current.equals(L.current)){
               this.current = this.current.next;
               L.current = L.current.next;
               continue;
            }else{
               return false;
            }
         }
         return true;
      }  
   }
   
//Manipulation Procedures

   //Clears the list
   void clear(){
      this.first = null;
      this.last = null;
      this.current = null;
      this.cursor = null;
   }

   //Moves the cursor to the front
   void moveFront(){
      if(this.length() == 0 ){
         throw new RuntimeException(
            "List: moveFront: Error list is empty");
      }else{
         this.cursor = this.first;
      }
   }

   //Moves the cursor to the back
   void moveBack(){
      if(this.length() == 0){
         System.out.println("Error list is empty");
      }else{
         this.cursor = this.last;
      }
   }

   //Moves cursor to next element
   void moveNext(){
      if(this.cursor != null){
         this.cursor = this.cursor.next;
      }else{
         throw new RuntimeException(
            "moveNext(): Cursor is not defined");
      }
   }
   
   //Moves cursor to previous element
   void movePrev(){
      if(this.cursor != null){
         this.cursor = this.cursor.prev;
      }else{
         throw new RuntimeException(
            "movePrev(): Cursor is not defined");
      }
   }

   //Adds element into the front of the list
   void prepend(Object item){
      if(this.length() != 0){
         node n = new node();
         n.item = item;
         n.next = this.first;
         this.first.prev = n;
         this.first = n;
      }else{
         node n = new node();
         n.item = item;
         this.first = n;
         this.last = n;
      }
   }

   //Adds element into the end of the list 
   void append(Object item){
      if(this.length() != 0){
         node n = new node();
         n.item = item;
         n.prev = this.last;
         this.last.next = n;
         this.last = n;
      }else{
         node n = new node();
         n.item = item;
         this.first = n;
         this.last = n;
      }
   }

   //Inserts element before the cursor
   void insertBefore(Object item){
      if(this.length() != 0){
         if(this.cursor != null){
            if(this.cursor == this.first){
               node n = new node();
               n.item = item;
               this.cursor.prev = n;
               n.next = this.cursor;
               this.first = n;
            }else{
               node n = new node();
               node temp = new node();
               temp = this.cursor.prev;
               n.item = item;
               n.next = this.cursor;
               this.cursor.prev = n;
               temp.next = n;
               n.prev = temp;
            }
         }else{
            throw new RuntimeException(
               "insertBefore(): Cursor is not defined");
         }
      }else{
         throw new RuntimeException(
            "insertBefore(): List is empty");
      }
   }

   //Inserts element after the cursor
   void insertAfter(Object item){
      if(this.length() != 0){
         if(this.cursor != null){
            if(this.cursor == this.last){
               node n = new node();
               n.item = item;
               this.cursor.next = n;
               n.prev = this.cursor;
               this.last = n;
            }else{
               node n = new node();
               node temp = new node();
               temp = this.cursor.next;
               n.item = item;
               n.prev = this.cursor;
               this.cursor.next = n;
               temp.prev = n;
               n.next = temp;
            }
         }else{
            throw new RuntimeException(
               "insertAfter(): Cursor is not defined");
         }
      }else{
         throw new RuntimeException(
            "insertAfter(): List is empty");
      }
   }

   //Deletes the term at the front of the list
   void deleteFront(){
      if(this.length() != 0){
         if(this.first.next != null){
            node temp = new node();
            temp = this.first.next;
            if(this.cursor == this.first)
               this.cursor = null;
            this.first = null;
            temp.prev = null;
            this.first = temp;
         }else{
            this.first = null;
            this.last = null;
            this.cursor = null;
         }
      }else{
         throw new RuntimeException(
            "deleteFront(): List is empty");
      }
   }

   //Deletes the term at the back of the list
   void deleteBack(){
      if(this.length() != 0){
         if(this.last.prev != null){
            node temp = this.last.prev;
            if(this.cursor == this.last)
               this.cursor = null;
            this.last = null;
            temp.next = null;
            this.last = temp;
         }else{
            this.first = null;
            this.last = null;
            this.cursor = null;
         }
      }else{
         throw new RuntimeException(
            "deleteBack(): List is empty");
      }
   }

   //Deletes the element the cursor is on
   void delete(){
      if(this.length() != 0){
         if(this.cursor != null && this.cursor.next != null && this.cursor.prev != null){
            node tempA = new node();
            node tempB = new node();
            tempA = this.cursor.next;
            tempB = this.cursor.prev;
            tempA.prev = tempB;
            tempB.next = tempA;
            this.cursor = null;
         }else if(this.cursor == this.first && this.cursor == this.last){
            this.first = null;
            this.last = null;
            this.cursor = null;
         }else if(this.cursor == this.first){
            node temp = new node();
            temp = this.cursor.next;
            this.cursor = null;
            this.first = null;
            this.first = temp;
         }else if(this.cursor == this.last){
            node temp = new node();
            temp = this.last.prev;
            this.cursor = null;
            this.last = null;
            this.last = temp;
         }
      }else{
         throw new RuntimeException(
            "delete(): List is empty");
      }
   }

   //Returns a string of the elements in the list
   public String toString(){
      if(this.length() == 0){
         throw new RuntimeException(
            "toString(): List is empty");
      }
      String str = "";
      this.current = this.first;
      while(this.current != null){
         str = str + this.current + " ";
         this.current = this.current.next;
      }
      return str;
   }

   //Returns a copy of this array
   List copy(){
      List L = new List();
      this.current = this.first;
      while(this.current != null){
         L.append(this.current.item);
         this.current = this.current.next;
      }
      return L;
   }
 
   //Concatonates the this list and the list argument
   List concat(List L){
      List list = new List();
      list = this.copy();
      L.moveFront();
      while(L.index() != -1){
         list.append(L.get());
         L.moveNext();
      }
      return list;
   }
}
