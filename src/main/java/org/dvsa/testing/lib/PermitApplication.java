package org.dvsa.testing.lib;

import activesupport.string.Str;
import org.dvsa.testing.lib.enums.PermitStatus;
import org.dvsa.testing.lib.enums.PermitType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PermitApplication {

    private String referenceNumber;
    private int id;
    private String licenceNumber;
    private Integer NoOfPermits;
    private PermitType type;
    private PermitStatus status;

    public PermitApplication(String referenceNumber, Integer noOfPermits, PermitType type, PermitStatus status) {
        this.referenceNumber = referenceNumber;
        NoOfPermits = noOfPermits;
        this.type = type;
        this.status = status;
    }

    public PermitApplication() {}

    public PermitApplication withReferenceNumber(@NotNull String referenceNumber) {
        this.referenceNumber = referenceNumber;
        this.id = extractPermitId(referenceNumber);
        this.licenceNumber = extractLicenceNumber(referenceNumber);
        return this;
    }

    public PermitApplication withNoOfPermits(Integer noOfPermits) {
        this.NoOfPermits = noOfPermits;
        return this;
    }

    public PermitApplication withType(@NotNull PermitType type) {
        this.type = type;
        return this;
    }

    public PermitApplication withType(@NotNull String type) {
        return withType(PermitType.getEnum(type));
    }

    public PermitApplication withStatus(@NotNull PermitStatus status) {
        this.status = status;
        return this;
    }

    public PermitApplication withStatus(@NotNull String status) {
        return withStatus(PermitStatus.getEnum(status));
    }

    private int extractPermitId(@NotNull String referenceNumber) {
        return Integer.parseInt(Str.find("(?<=\\w{2}\\d{7} / )\\d+", referenceNumber));
    }

    private String extractLicenceNumber(@NotNull String referenceNumber) {
        return Str.find("\\w{2}\\d{7}", referenceNumber);
    }

    public int getId() {
        return id;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermitApplication that = (PermitApplication) o;
        return Objects.equals(referenceNumber, that.referenceNumber) &&
                Objects.equals(NoOfPermits, that.NoOfPermits) &&
                Objects.equals(type, that.type) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceNumber, NoOfPermits, type, status);
    }

}
