/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 * 
 * @author Garrett Pierce, Ruben Acuna
 * @version 1.0
 */
public class PierceMatrix implements Matrix {

    private final int[][] class_Matrix;

    //TODO: implement interface.
    public PierceMatrix(int[][] matrix) {
        this.class_Matrix = matrix;
    }

    public int getElement(int y, int x) {
        return this.class_Matrix[y][x];
    }

    public int getRows() {
        return this.class_Matrix.length;
    }

    public int getColumns() {
        if (class_Matrix[0].length == 0) {
            return 0;
        }
        return class_Matrix[0].length;
    }

    public PierceMatrix scale(int scalar) {
        int[][] scale_matrix = this.class_Matrix;
        //PierceMatrix scale_matrix = new PierceMatrix(class_Matrix);

        for (int i = 0; i < scale_matrix.length; i++) {
            for (int j = 0; j < scale_matrix[i].length; j++) {
                scale_matrix[i][j] = scale_matrix[i][j] * scalar;
            }
        }

        PierceMatrix scale_return = new PierceMatrix(scale_matrix);
        //Incompatible types
        //Expects Matrix, gets int[][]
        return scale_return;
    }

    public PierceMatrix plus(Matrix other) throws RuntimeException {

        int[][] added_matrix = this.class_Matrix;

        if (added_matrix.length != other.getRows() || added_matrix[0].length != other.getColumns()) {
            throw new RuntimeException("Unequal matrix dimensions.");
        }

        for (int i = 0; i < added_matrix.length; i++) {
            for (int j = 0; j < added_matrix[0].length; j++) {
                added_matrix[i][j] = added_matrix[i][j] + other.getElement(i, j);
            }
        }

        PierceMatrix added_return = new PierceMatrix(added_matrix);
        return added_return;
    }

    public PierceMatrix minus(Matrix other) {
        int[][] subbed_matrix = this.class_Matrix;

        if (subbed_matrix.length != other.getRows() || subbed_matrix[0].length != other.getColumns()) {
            throw new RuntimeException("Unequal matrix dimensions.");
        }

        for (int i = 0; i < subbed_matrix.length; i++) {
            for (int j = 0; j < subbed_matrix[0].length; j++) {
                subbed_matrix[i][j] = subbed_matrix[i][j] - other.getElement(i, j);
            }
        }

        PierceMatrix subbed_return = new PierceMatrix(subbed_matrix);
        return subbed_return;
    }

    public PierceMatrix multiply(Matrix other) {
        int[][] mult_matrix = this.class_Matrix;

        if (mult_matrix.length != other.getRows() || mult_matrix[0].length != other.getColumns()) {
            throw new RuntimeException("Unequal matrix dimensions.");
        }

        int[][] products = new int[mult_matrix.length][mult_matrix.length];
        int sum = 0;
        int count = 0;
        for (int i = 0; i < mult_matrix.length; i++) {
            for (int j = 0; j < mult_matrix[0].length; j++) {
                sum = mult_matrix[i][j] * other.getElement(j, i);
            }
            products[i][count] = sum;
            if (count >= mult_matrix.length) {
                count = 0;
            }
        }

        PierceMatrix mult_return = new PierceMatrix(mult_matrix);
        return mult_return;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;

        return false;
    }

    public String toString() {
        int[][] out_matrix = class_Matrix;
        String out = "";
        for (int i = 0; i < class_Matrix.length; i++) {
            out = "{ ";
            for (int j = 0; j < class_Matrix[0].length; j++) {
                if (j == class_Matrix[0].length - 1) {
                    out = out + class_Matrix[i][j];
                } else {
                    out = out + class_Matrix[i][j] + ", ";
                }
            }
            out = out + " }" + "\n";
        }
        return out;
    }
    
    /**
     * Entry point for matrix testing.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        
        Matrix m1 = new PierceMatrix(data1);
        Matrix m2 = new PierceMatrix(data2);
        Matrix m3 = new PierceMatrix(data3);
        Matrix m4 = new PierceMatrix(data4);
        
        //System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());
        
        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true
        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true
        
        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));
        
        //not tested... multiply(). you know what to do.
        System.out.println("m2 * m3:\n" + m2.multiply(m3));
        
        //test operations (invalid)
        //System.out.println("m1 + m2" + m1.plus(m2));
        //System.out.println("m1 - m2" + m1.minus(m2));
    }
}