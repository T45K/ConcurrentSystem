package jp.ac.osaka_u.ist.kingJanken;

public enum JankenOperation {
    ROCK {
        public JankenResult janken(final JankenOperation operation) {
            switch (operation) {
                case ROCK:
                    return JankenResult.DRAW;
                case SCISSORS:
                    return JankenResult.WIN;
                case PAPER:
                    return JankenResult.LOSE;
            }

            throw new RuntimeException();
        }
    },
    SCISSORS {
        public JankenResult janken(final JankenOperation operation) {
            switch (operation) {
                case ROCK:
                    return JankenResult.LOSE;
                case SCISSORS:
                    return JankenResult.DRAW;
                case PAPER:
                    return JankenResult.WIN;
            }

            throw new RuntimeException();
        }

    },
    PAPER {
        public JankenResult janken(final JankenOperation operation) {
            switch (operation) {
                case ROCK:
                    return JankenResult.WIN;
                case SCISSORS:
                    return JankenResult.LOSE;
                case PAPER:
                    return JankenResult.DRAW;
            }

            throw new RuntimeException();
        }
    };

    public static JankenOperation getOperationFromString(final String operationString) {
        final int operationNumber = Integer.parseInt(operationString) % 3;
        switch (operationNumber) {
            case 0:
                return ROCK;
            case 1:
                return SCISSORS;
            case 2:
                return PAPER;
        }

        throw new RuntimeException();
    }

    abstract JankenResult janken(final JankenOperation operation);
}
