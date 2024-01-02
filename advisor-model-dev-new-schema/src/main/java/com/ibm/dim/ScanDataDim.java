package com.ibm.dim;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ScanDataDim {
    private LocalDate scanDate;
    private List<Object> dims;
    private List<Object> facts;

    public LocalDate getScanDate() {
        return this.scanDate;
    }

    public void setScanDate(LocalDate scanDate) {
        this.scanDate = scanDate;
    }

    public List<Object> getDims() {
        if (this.dims == null) {
            this.dims = new ArrayList<>();
        }
        return this.dims;
    }

    public void setFacts(List<Object> facts) {
        this.facts = facts;
    }

    public List<Object> getFacts() {
        if (this.facts == null) {
            this.facts = new ArrayList<>();
        }
        return this.facts;
    }

    public void setDims(List<Object> dims) {
        this.dims = dims;
    }

}
