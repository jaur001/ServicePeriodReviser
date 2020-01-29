import java.util.Date;

public class ServicePeriod implements Comparable<ServicePeriod>{
    private Date entryDate;
    private Date endDate;

    public ServicePeriod(Date entryDate, Date endDate) {
        this.entryDate = entryDate;
        this.endDate = endDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public int compareTo(ServicePeriod sp) {
        return sp.getEntryDate().compareTo(this.endDate); //1: paso despues (overlap) -1: paso antes (lap)
    }
}
