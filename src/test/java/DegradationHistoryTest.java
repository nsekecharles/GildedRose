import static org.junit.Assert.*;

import org.junit.Test;

public class DegradationHistoryTest {

	@Test
	public void 
	should_have_same_degradation() throws Exception {
		DegradationHistory dh = new DegradationHistory();
		
		assertEquals(dh.getHistory(1), dh.getHistoryLegacy(1));
	}
}
