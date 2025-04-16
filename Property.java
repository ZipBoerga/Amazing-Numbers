package numbers;

public enum Property {
    EVEN {
        public boolean test(NumberRecord record) {
            return record.isEven();
        }
    },
    ODD {
        public boolean test(NumberRecord record) {
            return record.isOdd();
        }
    },
    BUZZ {
        public boolean test(NumberRecord record) {
            return record.isBuzz();
        }
    },
    DUCK {
        public boolean test(NumberRecord record) {
            return record.isDuck();
        }
    },
    PALINDROMIC {
        public boolean test(NumberRecord record) {
            return record.isPalindromic();
        }
    },
    GAPFUL {
        public boolean test(NumberRecord record) {
            return record.isGapful();
        }
    },
    SPY {
        public boolean test(NumberRecord record) {
            return record.isSpy();
        }
    },
    SQUARE {
        public boolean test(NumberRecord record) {
            return record.isSquare();
        }
    },
    SUNNY {
        public boolean test(NumberRecord record) {
            return record.isSunny();
        }
    },
    JUMPING {
        public boolean test(NumberRecord record) {
            return record.isJumping();
        }
    },
    SAD {
        public boolean test(NumberRecord record) {
            return record.isSad();
        }
    },
    HAPPY {
        public boolean test(NumberRecord record) {
            return record.isHappy();
        }
    },
    ;

    public abstract boolean test(NumberRecord record);
}