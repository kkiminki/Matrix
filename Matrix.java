class Matrix{


   class Entry{
      
      int column;
      double data;

      Entry(int col, double data){
         this.column = col;
         this.data = data;
      }

      boolean equals(Entry item){
         if(this.column == item.column && this.data == item.data)
            return true;
         return false;
      }

      public String toString(){
         return "("+this.column+", "+this.data+") ";
      }
   }

   List[] row;
   int length;

//Constructor

   Matrix(int n ){
      if(n<1){
         throw new RuntimeException(
            "Matrix: Matrix(): Matrix size must be >= 1");
      }
      this.row = new List[n+1];
      this.length = n;
      for(int i = 1; i < n + 1; i++){
         this.row[i] = new List();
      }
   }

//Access functions

   int getSize(){
      return this.length;
   }

   int getNNZ(){
      int count = 0;
      for(int i =1; i < this.length + 1; i++){
      	if(this.row[i].length()==0) continue;
         this.row[i].moveFront();
         while(this.row[i].index() != -1){
            count++;
            this.row[i].moveNext();
         }
      }
      return count;
   }

   public boolean equals(Object x){
      try{
         Matrix m = (Matrix) x;
         for(int i = 1; i < this.length + 1; i++){
            if(this.row[i].equals(m.row[i]))
               continue;
            return false;
         }
         return true;
      }catch(Exception e){
         throw new RuntimeException(
            "Matrix: equals: input cannot be converted to Matrix");
      }
   }

//Manipulation procedures

   void changeEntry(int i, int j, double x){
      if(j > this.length){
         throw new RuntimeException(
            "Matrix: changeEntry: Input column does not exist in this matrix");
      }
      Entry e = new Entry(j, x);
      if(this.row[i].length()==0 && x==0){
         return;
      }
      if(this.row[i].length()==0){
         this.row[i].append(e);
         return;
      }
      this.row[i].moveFront();
      while(this.row[i].index() != -1){
         Entry curr = (Entry)this.row[i].get();
         if(curr.column > j && x == 0){
            return;
         }
         if(curr.column > j){
            this.row[i].insertBefore(e);
            return;
         }
         if(curr.column == j && x == 0){
            this.row[i].delete();
            return;
         }
         if(curr.column == j){
            curr.data = x;
            return;
         }
         this.row[i].moveNext();
      }
      this.row[i].append(e);
   }

   Matrix scalarMult(double x){
      Matrix m = new Matrix(this.length);
      for(int i = 1; i <this.length + 1; i++){
         if(this.row[i].length()==0)continue;
         this.row[i].moveFront();
         while(this.row[i].index() != -1){
            Entry curr = (Entry)this.row[i].get();
            Entry e = new Entry(curr.column, curr.data * x);
            m.row[i].append(e);
            this.row[i].moveNext();
         }
      }
      return m;
   }

   Matrix add(Matrix M){
      Matrix n = new Matrix(this.getSize());
      if(this.getSize() != M.getSize()){
         throw new RuntimeException(
            "Matrix: add(): This matrix and matrix M are not the same size");
      }
      for(int i = 1; i < this.length + 1; i++){
         if(this.row[i].length()==0 && M.row[i].length()==0) continue;
         if(this.row[i].length()==0){
            M.row[i].moveFront();
            while(M.row[i].index()!=-1){
               n.row[i].append(M.row[i].get());
               M.row[i].moveNext();
            }
            continue;
         }
         if(M.row[i].length()==0){
            this.row[i].moveFront();
            while(this.row[i].index()!=-1){
               n.row[i].append(this.row[i].get());
               this.row[i].moveNext();
            }
         continue;
         }
         this.row[i].moveFront();
         M.row[i].moveFront();
         Entry a;
         Entry b;
         while(true){
            if(this.row[i].index()==-1 && M.row[i].index()==-1){
               break;
            }
            if(this.row[i].index()==-1){
               while(true){
                  n.row[i].append(M.row[i].get());
                  M.row[i].moveNext();
                  if(M.row[i].index()==-1){
                     break;
                  }
               }
               break;
            }
            if(M.row[i].index()==-1){
               while(this.row[i].index()!=-1){
                  n.row[i].append(this.row[i].get());
                  this.row[i].moveNext();
               }
               break;
            }
            a = (Entry)this.row[i].get();
            b = (Entry)M.row[i].get();
            if(a.column == b.column){
               double threshold = .000001;
               double sum = a.data+b.data;
               if((-1*threshold)<sum &&(threshold)>sum){
                  M.row[i].moveNext();
                  this.row[i].moveNext();
                  continue;
               }
               Entry e = new Entry(a.column, sum);
               n.row[i].append(e);
               this.row[i].moveNext();
               M.row[i].moveNext();
               continue;
            }
            if(a.column > b.column){
               Entry e = new Entry(b.column, b.data);
               n.row[i].append(e);
               M.row[i].moveNext();
               continue;
            }
            Entry e = new Entry(a.column, a.data);
            n.row[i].append(e);
            this.row[i].moveNext();
         }
      }
      return n;
   }

   Matrix sub(Matrix M){
      if(this.getSize()!=M.getSize()){
         throw new RuntimeException(
            "Matrix: sub: This matrix and input matrix are not the same size");
      }
      Matrix n = new Matrix(this.length);
      Matrix temp = M.scalarMult(-1);
      n = this.add(temp);
      return n;
   }

   Matrix transpose(){
      Matrix m = new Matrix(this.length);
      for(int i = 1; i < this.length+1; i++){
         if(this.row[i].length()==0){
            continue;
         }
         Entry e;
         this.row[i].moveFront();
         while(this.row[i].index()!=-1){
            e = (Entry)this.row[i].get();
            double data = e.data;
            int column = e.column;
            m.changeEntry(column, i, data);
            this.row[i].moveNext();
         }
      }
      return m;
   }

   Matrix mult(Matrix M){
      if(this.length!=M.length){
         throw new RuntimeException(
            "Matrix: mult: This matrix and input matrix are not the same length");
      }  
      Matrix n = new Matrix(this.length);
      Matrix k = M.transpose();
      for(int i = 1; i < this.row.length; i++){
         for(int j = 1; j < k.row.length; j++){
         	if(this.row[i].length()==0 || k.row[j].length()==0)
         	   continue;
            double data = this.dot(this.row[i], k.row[j]);
            n.changeEntry(i, j, data);
         }
      }
      return n;
   }

   private double dot(List A, List B){
      double sum = 0;
      if(A.length()==0 || B.length()==0){
         return 0;
      }
      A.moveFront();
      B.moveFront();
      while(true){
         if(A.index()==-1 || B.index()==-1){ 
            return sum;
         }
         Entry a = (Entry) A.get();
         Entry b = (Entry) B.get();
         if(a.column == b.column){
            sum += (a.data * b.data);
            A.moveNext();
            B.moveNext();
            continue;
         }
         if(a.column > b.column){
            B.moveNext();
            continue;
         }
         if(b.column > a.column){
            A.moveNext();
            continue;
         }
      }
   }

   public String toString(){
      String str = "";
      for(int i = 1;i < this.length + 1; i++){
          if(this.row[i].length()==0) continue;
          str = str +i+": "+ this.row[i] + '\n';
      }
      return str;
   }
}
