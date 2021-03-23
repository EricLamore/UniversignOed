package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class CertifiedUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CertifiedUser.class);
        CertifiedUser certifiedUser1 = new CertifiedUser();
        certifiedUser1.setId("id1");
        CertifiedUser certifiedUser2 = new CertifiedUser();
        certifiedUser2.setId(certifiedUser1.getId());
        assertThat(certifiedUser1).isEqualTo(certifiedUser2);
        certifiedUser2.setId("id2");
        assertThat(certifiedUser1).isNotEqualTo(certifiedUser2);
        certifiedUser1.setId(null);
        assertThat(certifiedUser1).isNotEqualTo(certifiedUser2);
    }
}
