//Kyler Kiminki
//CMPS101
//kkiminki

class ListTest{
   public static void main(String [] args){
      List L = new List();
      System.out.println("List is empty: "+(L.length()==0));
      System.out.println("Cursor: "+L.index());
      L.append(5);
      L.prepend(6);
      L.moveFront();
      System.out.println("Appending 5 and prepending 6...");
      System.out.println("The front object is "+L.get());
      L.moveNext();
      System.out.println("The back object is "+L.get());
      System.out.println("Appending 1, 2, 3, 4");
      L.append(1);
      L.append(2);
      L.append(3);
      L.append(4);
      System.out.println(L);
      List k = L.copy();
      System.out.println("List K");
      System.out.println(k);
      System.out.println("L = K: "+L.equals(k));
   }
}
