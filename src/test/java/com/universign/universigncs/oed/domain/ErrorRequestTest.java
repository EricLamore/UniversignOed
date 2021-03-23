package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class ErrorRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ErrorRequest.class);
        ErrorRequest errorRequest1 = new ErrorRequest();
        errorRequest1.setId("id1");
        ErrorRequest errorRequest2 = new ErrorRequest();
        errorRequest2.setId(errorRequest1.getId());
        assertThat(errorRequest1).isEqualTo(errorRequest2);
        errorRequest2.setId("id2");
        assertThat(errorRequest1).isNotEqualTo(errorRequest2);
        errorRequest1.setId(null);
        assertThat(errorRequest1).isNotEqualTo(errorRequest2);
    }
}
