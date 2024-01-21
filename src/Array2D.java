import java.util.ArrayList;

public class Array2D {

    private ArrayList<ArrayList<Float>> array;
    private int rows;
    private int columns;

    public Array2D(int rows, int columns){
        this.columns = columns;
        this.rows = rows;
        resetArray();
    }

    public float sum(){
        float sum = 0F;
        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getColumns(); x++) {
                sum += getValue(y, x);
            }
        }
        return sum;
    }

    public Array2D power(float power){
        Array2D result = new Array2D(getRows(), getColumns());
        for (int y = 0; y < result.getRows(); y++) {
            for (int x = 0; x < result.getColumns(); x++) {
                result.setValue(y, x, (float) Math.pow(this.getValue(y, x), power));
            }
        }
        return result;
    }

    public Array2D multiply(float n){
        Array2D result = new Array2D(getRows(), getColumns());
        for (int y = 0; y < result.getRows(); y++) {
            for (int x = 0; x < result.getColumns(); x++) {
                result.setValue(y, x, n * this.getValue(y, x));
            }
        }
        return result;
    }

    public Array2D add(float n){
        Array2D result = new Array2D(getRows(), getColumns());
        for (int y = 0; y < result.getRows(); y++) {
            for (int x = 0; x < result.getColumns(); x++) {
                result.setValue(y, x, n + this.getValue(y, x));
            }
        }
        return result;
    }

    public Array2D T(){
        Array2D transposed = new Array2D(columns, rows);
        for (int y = 0; y < rows; y++) {
            transposed.setColumn(y, this.getRow(y));
        }
        return transposed;
    }

    public Array2D dot(Array2D array2){
        if(this.rows == array2.columns || this.columns == array2.rows){
            Array2D product = new Array2D(rows, array2.getColumns());
            for (int Y = 0; Y < product.rows; Y++) {
                for (int X = 0; X < product.columns; X++) {
                    float p = 0F;
                    for (int n = 0; n < this.columns; n++) {
                        p += getValue(Y, n) * array2.getValue(n, X);
                    }
                    product.setValue(Y, X, p);
                }
            }
            return product;
        } else {
            System.out.println("Matrix dimensions not compatible: [" + this.rows + ", " + this.columns + "] [" + array2.rows + ", " + array2.columns + "]");
        }
        return null;
    }

    public Array2D antidot(Array2D array2){
        if(this.rows == array2.columns || this.columns == array2.rows){
            Array2D product = new Array2D(rows, array2.getColumns());
            for (int Y = 0; Y < product.rows; Y++) {
                for (int X = 0; X < product.columns; X++) {
                    float p = 0F;
                    for (int n = 0; n < this.columns; n++) {
                        p += getValue(Y, n) / array2.getValue(n, X);
                    }
                    product.setValue(Y, X, p);
                }
            }
            return product;
        } else {
            System.out.println("Matrix dimensions not compatible: [" + this.rows + ", " + this.columns + "] [" + array2.rows + ", " + array2.columns + "]");
        }
        return null;
    }

    public Array2D random(){
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Float r = MathUtil.randomBetween(-1F, 1F);
                setValue(y, x, r);
            }
        }
        return this;
    }
    
    public void setRow(int index, ArrayList<Float> row){
        array.set(index, row);
    }

    public void setColumn(int index, ArrayList<Float> column){
        for (int i = 0; i < rows; i++) {
            array.get(i).set(index, column.get(i));
        }
    }

    public ArrayList<Float> getRow(int index){
        return array.get(index);
    }

    public ArrayList<Float> getColumn(int index){
        ArrayList<Float> quantities = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            quantities.set(i, array.get(i).get(index));
        }
        return quantities;
    }

    public void setValue(int row, int column, Float value){
        array.get(row).set(column, value);
    }

    public Float getValue(int row, int column){
        return array.get(row).get(column);
    }

    public ArrayList<ArrayList<Float>> getArray(){
        return array;
    }

    public void resetArray(){
        ArrayList<ArrayList<Float>> array = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            ArrayList<Float> valuesPerColumn = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                valuesPerColumn.add((float) 0);
            }
            array.add(valuesPerColumn);
        }
        this.array = array;


        // array = new float[][]{
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        //            new float[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }

    public void setArray(ArrayList<ArrayList<Float>> array){
        this.array = array;
    }

    public void printValues(int decimals){
        int dec = decimals;
        String start = "[";
        String end = "]";
        StringBuilder builder = new StringBuilder();
        builder.append(start);
        builder.append("[ ");
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if(y == 0 && x == 0){
                    if (getValue(y, x) >= 0){
                        if(getValue(y, x) > 0){
                            builder.append(" " + String.format("%." + dec + "f", getValue(y, x)) + "   ");
                        } else {
                            builder.append(String.format("%." + dec + "f", getValue(y, x)) + "   ");
                        }
                    } else {
                        if(getValue(y, x) >= 0){
                            builder.append(" " + String.format("%." + dec + "f", getValue(y, x)) + "  ");
                        } else {
                            builder.append(String.format("%." + dec + "f", getValue(y, x)) + "  ");
                        }
                    }

                } else {
                    if (x < columns - 1 && getValue(y, x) >= 0){
                        builder.append(String.format("%." + dec + "f", getValue(y, x)) + "   ");
                    } else {
                        builder.append(String.format("%." + dec + "f", getValue(y, x)) + "  ");
                    }
                }
            }
            builder.append("]");
            if(y < rows - 1){
                if(getValue(y, 0) >= 0){
                    builder.append("\n [  ");
                } else {
                    builder.append("\n [ ");
                }

            }
        }
        builder.append(end);
        System.out.println(builder);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

}