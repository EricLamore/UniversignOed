package com.universign.universigncs.oed.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.universign.universigncs.oed.web.rest.TestUtil;

public class ColumnTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Column.class);
        Column column1 = new Column();
        column1.setId("id1");
        Column column2 = new Column();
        column2.setId(column1.getId());
        assertThat(column1).isEqualTo(column2);
        column2.setId("id2");
        assertThat(column1).isNotEqualTo(column2);
        column1.setId(null);
        assertThat(column1).isNotEqualTo(column2);
    }
}
