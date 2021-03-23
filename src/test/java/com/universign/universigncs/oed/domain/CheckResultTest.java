package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class CheckResultTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CheckResult.class);
        CheckResult checkResult1 = new CheckResult();
        checkResult1.setId("id1");
        CheckResult checkResult2 = new CheckResult();
        checkResult2.setId(checkResult1.getId());
        assertThat(checkResult1).isEqualTo(checkResult2);
        checkResult2.setId("id2");
        assertThat(checkResult1).isNotEqualTo(checkResult2);
        checkResult1.setId(null);
        assertThat(checkResult1).isNotEqualTo(checkResult2);
    }
}
