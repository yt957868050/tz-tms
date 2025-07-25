package com.feian.tms.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDeleteRequest {
    private Object id;

    public List<Long> getIdList() {
        if (id instanceof List) {
            return (List<Long>) id;
        } else if (id instanceof Number) {
            return Collections.singletonList(((Number) id).longValue());
        }
        return Collections.emptyList();
    }

}
