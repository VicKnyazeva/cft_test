package ru.knyazeva;

public class RealNumberCollector extends CollectorBase {
    private double minValue = Long.MAX_VALUE;
    private double maxValue = Long.MIN_VALUE;
    private double sum = 0;

    RealNumberCollector(String fullOutputFilePath, boolean appendToFile) {
        super(fullOutputFilePath, appendToFile);
    }

    @Override
    protected boolean checkInput(String input) {
        try {
            double result = Double.parseDouble(input);
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
                    result = String.format("Float numbers: count: %d, Min: %g, Max: %g, Sum: %g, Average: %g", getCount(), minValue, maxValue, sum, sum / getCount());
                    break;
                }
            case SHORT:
                result = String.format("Float numbers: count: %d", getCount());
                break;
        }
        System.out.println(result);
    }
}
