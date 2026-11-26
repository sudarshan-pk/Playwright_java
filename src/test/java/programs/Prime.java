package programs;

public class Prime {

    public static void main(String[] args) {

        int checkPrime = 29;
        boolean isPrime = true;

        if (checkPrime == 0 || checkPrime == 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(checkPrime); i++) {
                if (checkPrime % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println("Prime no : "+isPrime);
    }
}
