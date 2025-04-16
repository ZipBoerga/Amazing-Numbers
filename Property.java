package numbers;

public enum Property {
    EVEN {
        public boolean test(long number) {
            return PropertyChecks.isEven(number);
        }
    },
    ODD {
        public boolean test(long number) {
            return PropertyChecks.isOdd(number);
        }
    },
    BUZZ {
        public boolean test(long number) {
            return PropertyChecks.isBuzz(number);
        }
    },
    DUCK {
        public boolean test(long number) {
            return PropertyChecks.isDuck(number);
        }
    },
    PALINDROMIC {
        public boolean test(long number) {
            return PropertyChecks.isPalindromic(number);
        }
    },
    GAPFUL {
        public boolean test(long number) {
            return PropertyChecks.isGapful(number);
        }
    },
    SPY {
        public boolean test(long number) {
            return PropertyChecks.isSpy(number);
        }
    },
    SQUARE {
        public boolean test(long number) {
            return PropertyChecks.isSquare(number);
        }
    },
    SUNNY {
        public boolean test(long number) {
            return PropertyChecks.isSunny(number);
        }
    },
    JUMPING {
        public boolean test(long number) {
            return PropertyChecks.isJumping(number);
        }
    },
    SAD {
        public boolean test(long number) {
            return PropertyChecks.isSad(number);
        }
    },
    HAPPY {
        public boolean test(long number) {
            return PropertyChecks.isHappy(number);
        }
    },
    ;

    public abstract boolean test(long number);
}