package numbers;

public class NumberRecord {
    private final long number;
    private final boolean even;
    private final boolean odd;
    private final boolean buzz;
    private final boolean duck;
    private final boolean palindromic;
    private final boolean gapful;
    private final boolean spy;
    private final boolean square;
    private final boolean sunny;
    private final boolean jumping;
    private final boolean happy;
    private final boolean sad;

    public NumberRecord(long number) {
        this.number = number;
        this.even = PropertyChecks.isEven(number);
        this.odd = PropertyChecks.isOdd(number);
        this.buzz = PropertyChecks.isBuzz(number);
        this.duck = PropertyChecks.isDuck(number);
        this.palindromic = PropertyChecks.isPalindromic(number);
        this.gapful = PropertyChecks.isGapful(number);
        this.spy = PropertyChecks.isSpy(number);
        this.square = PropertyChecks.isSquare(number);
        this.sunny = PropertyChecks.isSunny(number);
        this.jumping = PropertyChecks.isJumping(number);
        this.sad = PropertyChecks.isSad(number);
        this.happy = PropertyChecks.isHappy(number);
    }

    public long getNumber() {
        return number;
    }

    public boolean isEven() {
        return even;
    }

    public boolean isBuzz() {
        return buzz;
    }

    public boolean isDuck() {
        return duck;
    }

    public boolean isPalindromic() {
        return palindromic;
    }

    public boolean isGapful() {
        return gapful;
    }

    public boolean isOdd() {
        return odd;
    }

    public boolean isSpy() {
        return spy;
    }

    public boolean isSquare() {
        return square;
    }

    public boolean isSunny() {
        return sunny;
    }

    public boolean isJumping() {
        return jumping;
    }

    public boolean isHappy() {
        return happy;
    }

    public boolean isSad() {
        return sad;
    }
}
