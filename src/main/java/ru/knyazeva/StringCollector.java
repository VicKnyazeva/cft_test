package ru.knyazeva;

public class StringCollector extends CollectorBase {
    private long minLength = Long.MAX_VALUE;
    private long maxLength = Long.MIN_VALUE;

    StringCollector(String fullOutputFilePath, boolean appendToFile) {
        super(fullOutputFilePath, appendToFile);
    }

    @Override
    protected boolean checkInput(String input) {
        minLength = Math.min(minLength, input.length());
        maxLength = Math.max(maxLength, input.length());
        return true;
    }

    @Override
    public void showStatistics(StatisticsMode options) {
        String result;
        switch (options) {
            default:
                return;
            case FULL:
                if (getCount() > 0) {
                    result = String.format("Strings: count: %d, Min length: %d, Max length: %d", getCount(), minLength, maxLength);
                    break;
                }
            case SHORT:
                result = String.format("Strings: count: %d", getCount());
                break;
        }
        System.out.println(result);
    }
}
