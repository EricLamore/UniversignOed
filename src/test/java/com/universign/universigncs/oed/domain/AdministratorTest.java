package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class AdministratorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Administrator.class);
        Administrator administrator1 = new Administrator();
        administrator1.setId("id1");
        Administrator administrator2 = new Administrator();
        administrator2.setId(administrator1.getId());
        assertThat(administrator1).isEqualTo(administrator2);
        administrator2.setId("id2");
        assertThat(administrator1).isNotEqualTo(administrator2);
        administrator1.setId(null);
        assertThat(administrator1).isNotEqualTo(administrator2);
    }
}
