import org.junit.Test;
import ru.nsu.ccfit.radeev.commonclient.config.ClientConfig;

import static org.junit.Assert.assertEquals;

public class testClientConfig {
    @Test
    public void testSetterOneTime(){
        ClientConfig.setId(25);
        assertEquals(25, ClientConfig.getId());
    }

    @Test
    public void testSetterTwoTimes(){
        ClientConfig.setId(25);
        ClientConfig.setId(27);
        assertEquals(25, ClientConfig.getId());
    }

}
