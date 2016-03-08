

import java.util.Scanner;
import static java.lang.System.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

class Sparse{
   public static void main(String [] args)throws IOException{
      if(args.length!=2){
         throw new RuntimeException(
            "Sparse: Usage Error: Sparse [inputFile] [outputFile]");
      }
      String [] input;
      int count = getCount(args[0]);
      input = new String[count];
      Matrix [] M = fillString(args[0], input);
      output(args[1], M);
   }

   static int getCount(String file)throws IOException{
      BufferedReader in = new BufferedReader(
         new FileReader(file));
      int i = 0;
      String line;
      while(true){
         i++;
         line = in.readLine();
         if(line==null)
            break;
         }
      return i;
   }

   static Matrix[] fillString(String file, String[] input)throws IOException{
      try{
         Scanner sc = new Scanner(new File(file));
         int size = sc.nextInt();
         int aLength = sc.nextInt();
         int bLength = sc.nextInt();
         Matrix A = new Matrix(size);
         Matrix B = new Matrix(size);
         for(int i = 0; i < aLength; i++){
            int row = sc.nextInt();
            int column = sc.nextInt();
            double data = sc.nextDouble();
            A.changeEntry(row, column, data);
         }
         for(int i = 0; i < bLength; i++){
            int row = sc.nextInt();
            int column = sc.nextInt();
            double data = sc.nextDouble();
            B.changeEntry(row, column, data);
         }
         return getMatrix(A, B);
      }catch(Exception e){
        throw new RuntimeException(e);
      }
   }

   static Matrix[] getMatrix(Matrix A, Matrix B){
      Matrix [] M = new Matrix[10];
      Matrix a = new Matrix(A.getSize());
      Matrix b = new Matrix(B.getSize());
      a = A.scalarMult(1);
      b = B.scalarMult(1);
      M[0] = A;
      M[1] = B;
      M[2] = A.scalarMult(1.5);
      M[3] = A.add(B);
      System.out.println("out of first add");
      M[4] = A.add(a);
      System.out.println("out of second add");
      M[5] = B.sub(A);
      M[6] = A.sub(a);
      System.out.println("Out of sub");
      M[7] = A.transpose();
      M[8] = A.mult(b);
      M[9] = B.mult(b);
      return M;
   }

   static void output(String fileName, Matrix [] M)throws IOException{
      File file = new File(fileName);
      FileOutputStream fos = new FileOutputStream(file);
      PrintStream ps = new PrintStream(fos);
      PrintStream console = System.out;
      System.setOut(ps);
      System.out.println("A has "+M[0].getNNZ()+" non-zero entries: ");
      System.out.println(M[0]);
      System.out.println("B has "+M[1].getNNZ()+" non-zero entries: ");
      System.out.println(M[1]);
      System.out.println("(1.5)*A= ");
      System.out.println(M[2]);
      System.out.println("A+B = ");
      System.out.println(M[3]);
      System.out.println("A+A = ");
      System.out.println(M[4]);
      System.out.println("B-A = ");
      System.out.println(M[5]);
      System.out.println("A-A = ");
      System.out.println(M[6]);
      System.out.println("Transpose(A) = ");
      System.out.println(M[7]);
      System.out.println("A*B = ");
      System.out.println(M[8]);
      System.out.println("B*B = ");
      System.out.println(M[9]);
      System.setOut(console);
   }
      
}
