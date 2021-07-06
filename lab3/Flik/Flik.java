/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        /** 'Integer' is a wrapper class which wraps a primitive type
         *  into an object. So '==' compares two object to see if the
         *  variables refer to the same object reference.
         *  To compare the value, using '.equals()' method instead.
         *  See more information on:
         *  https://stackoverflow.com/questions/170008
         */
        //return a == b;
        return a.equals(b);
    }
}
