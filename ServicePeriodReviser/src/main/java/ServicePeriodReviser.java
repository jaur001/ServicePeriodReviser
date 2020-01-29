import java.util.List;
import java.util.stream.IntStream;

public class ServicePeriodReviser {

    private static final long MILLISECONDSTODAYS = 86400000;
    private List<ServicePeriod> servicePeriodList;
    private Clock clock;

    public ServicePeriodReviser(List<ServicePeriod> servicePeriodList,Clock clock) {
        this.clock = clock;
        this.servicePeriodList = servicePeriodList;
    }


    public boolean isOverLap(){
        return IntStream.range(0,servicePeriodList.size()-1)
                .anyMatch(i -> compareConsecutive(i) == 1);
    }


    private int compareConsecutive(int i) {
        return servicePeriodList.get(i).compareTo(servicePeriodList.get(i+1));
    }

    public boolean isLap(){
        return IntStream.range(0,servicePeriodList.size()-1)
                .anyMatch(i -> compareConsecutive(i) == -1);
    }

    public int getDaysPending(){
        return convertToDays(getTimeInMilliseconds());
    }

    private int convertToDays(long timeInMilliseconds) {
        return (int)(timeInMilliseconds/MILLISECONDSTODAYS);
    }

    private long getTimeInMilliseconds() {
        return clock.getDate().getTime() - getLastServicePeriod().getEndDate().getTime();
    }

    private ServicePeriod getLastServicePeriod() {
        return servicePeriodList.get(servicePeriodList.size()-1);
    }
}
