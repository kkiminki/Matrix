class MatrixTest{ 
   public static void main(String [] args){     
      Matrix m = new Matrix(3);
      m.changeEntry(1, 1, 1);
      m.changeEntry(1, 2, 2);
      m.changeEntry(1, 3, 3);
      m.changeEntry(2, 1, 4);
      m.changeEntry(2, 2, 5);
      m.changeEntry(2, 3, 6);
      m.changeEntry(3, 1, 7);
      m.changeEntry(3, 3, 9);
      System.out.println("Matrix m");
      System.out.println(m);
      System.out.println("this matrix is "+m.getSize()+"x"+m.getSize());
      System.out.println("There are "+m.getNNZ()+" nonzero entries");
      Matrix k = new Matrix(m.length);
      k.changeEntry(1, 1, 1.0);
      k.changeEntry(1, 3, 1.0);
      k.changeEntry(3, 1, 1.0);
      k.changeEntry(3, 2, 1.0);
      k.changeEntry(3, 3, 1.0);
      m = k.scalarMult(1);
      Matrix product = k.mult(m);
      System.out.println("K");
      System.out.println(k);
      m = k.transpose();
      System.out.println("Transpose of k");
      System.out.println(m);
      System.out.println("Product of k and m");
      System.out.print(product);
      product = m.sub(k);
      System.out.println("M - K");
      System.out.println(product);
      product = m.add(k);
      System.out.println("M + K");
      System.out.println(product);
      Matrix n = m.scalarMult(1);
      System.out.println("Matrix N");
      System.out.println(n);
      System.out.println("M = N");
      System.out.println(m.equals(n));
   }
}
