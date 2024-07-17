package ru.knyazeva;

public class IntNumberCollector extends CollectorBase {
    private long minValue = Long.MAX_VALUE;
    private long maxValue = Long.MIN_VALUE;
    private long sum = 0;

    IntNumberCollector(String fullOutputFilePath, boolean appendToFile) {
        super(fullOutputFilePath, appendToFile);
    }

    @Override
    protected boolean checkInput(String input) {
        try {
            long result = Long.parseLong(input);
            minValue = Math.min(minValue, result);
            maxValue = Math.max(maxValue, result);
            sum += result;
            return true;
        } catch (NumberFormatException ex) {
        }
        return false;
    }

    @Override
    public void showStatistics(StatisticsMode options) {
        String result;
        switch (options) {
            default:
                return;
            case FULL:
                if (getCount() > 0) {
                    result = String.format("Integer numbers: count: %d, Min: %d, Max: %d, Sum: %d, Average: %g", getCount(), minValue, maxValue, sum, (double) sum / getCount());
                    break;
                }
            case SHORT:
                result = String.format("Integer numbers: count: %d", getCount());
                break;
        }
        System.out.println(result);
    }
}
