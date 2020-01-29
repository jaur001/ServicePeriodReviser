import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class ServicePeriodReviserTest {

    @Test
    public void days_Pending_Should_Work_Properly(){
        /*Configure DOC*/
        //Mock
        Clock clock = mock(Clock.class);
        //Stub
        when(clock.getDate()).thenReturn(new Date(100,1,15));

        /*Configure SUT*/
        ServicePeriod servicePeriod = new ServicePeriod(new Date(100,1,5),new Date(100,1,10));
        ServicePeriodReviser servicePeriodReviser = new ServicePeriodReviser(Arrays.asList(servicePeriod),clock);
        /*Exec*/
        int days = servicePeriodReviser.getDaysPending();

        /*Verify*/
        assertThat(days).isEqualTo(5);
        /*Finalize*/
    }

    @Test
    public void overlaps_Should_Return_False(){
        /*Configure DOC*/
        //Mock
        Clock clock = mock(Clock.class);
        //Stub
        when(clock.getDate()).thenReturn(new Date(100,1,20));

        /*Configure SUT*/
        ServicePeriod servicePeriod = new ServicePeriod(new Date(100,1,5),new Date(100,1,10));
        ServicePeriod servicePeriod2 = new ServicePeriod(new Date(100,1,10),new Date(100,1,15));
        ServicePeriodReviser servicePeriodReviser = new ServicePeriodReviser(Arrays.asList(servicePeriod,servicePeriod2),clock);
        /*Exec*/
        boolean result = servicePeriodReviser.isLap();

        /*Verify*/
        assertThat(result).isEqualTo(false);
        /*Finalize*/
    }

    @Test
    public void overlaps_Should_Return_True(){
        /*Configure DOC*/
        //Mock
        Clock clock = mock(Clock.class);
        //Stub
        when(clock.getDate()).thenReturn(new Date(100,1,20));

        /*Configure SUT*/
        ServicePeriod servicePeriod = new ServicePeriod(new Date(100,1,5),new Date(100,1,9));
        ServicePeriod servicePeriod2 = new ServicePeriod(new Date(100,1,10),new Date(100,1,15));
        ServicePeriodReviser servicePeriodReviser = new ServicePeriodReviser(Arrays.asList(servicePeriod,servicePeriod2),clock);
        /*Exec*/
        boolean result = servicePeriodReviser.isLap();

        /*Verify*/
        assertThat(result).isEqualTo(false);
        /*Finalize*/
    }
}
