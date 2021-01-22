package Problems;

public class ImplementRand10UsingRand7 {
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            if (num <= 40) {
                return num % 10 + 1;
            }
            num = (num - 40 - 1) * 7 + rand7();
            if (num <= 60) {
                return num % 10 + 1;
            }
            num = (num - 60 - 1) * 7 + rand7();
            if (num <= 20) {
                return num % 10 + 1;
            }
        }
    }

    private int rand7() {
        return 0;
    }
}
