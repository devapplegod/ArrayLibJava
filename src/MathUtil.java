public class MathUtil {

    public static Float randomBetween(Float min, Float max){
        return (float) (Math.random() * (max - min)) + min;
    }

    public static float ReLU(float f){
        return Math.max(f, 0);
    }

    public static Array2D ReLU(Array2D array){
        Array2D relu = new Array2D(array.getRows(), array.getColumns());
        for (int y = 0; y < array.getRows(); y++) {
            for (int x = 0; x < array.getColumns(); x++) {
                relu.setValue(y, x, ReLU(array.getValue(y, x)));
            }
        }
        return relu;
    }

    public static Array2D difference(Array2D a, Array2D b){
        Array2D result = new Array2D(a.getRows(), a.getColumns());
        for (int i = 0; i < result.getColumns(); i++) {
            for (int j = 0; j < result.getRows(); j++) {
                result.setValue(j, i, a.getValue(j, i)-b.getValue(j, i));
            }
        }
        return result;
    }

}
