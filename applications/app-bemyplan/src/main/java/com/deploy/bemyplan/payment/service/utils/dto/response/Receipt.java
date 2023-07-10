package com.deploy.bemyplan.payment.service.utils.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Receipt {
    @JsonAlias("adam_id")
    private Number adamId;
    @JsonAlias("app_item_id")
    private Number app_item_id;
    @JsonAlias("application_version")
    private String applicationVersion;
    @JsonAlias("bundle_id")
    private String bundleId;
    @JsonAlias("download_id")
    private Long downloadId;
    @JsonAlias("in_app")
    private List<InApp> inApp;
    @JsonAlias("original_application_version")
    private String originalApplicationVersion;
    @JsonAlias("original_purchase_date")
    private String originalPurchaseDate;
    @JsonAlias("original_purchase_date_ms")
    private String originalPurchaseDateMs;
    @JsonAlias("original_purchase_date_pst")
    private String originalPurchaseDatePst;
    @JsonAlias("receipt_creation_date")
    private String receiptCreationDate;
    @JsonAlias("receipt_creation_date_ms")
    private String receiptCreationDateMs;
    @JsonAlias("receipt_creation_date_pst")
    private String receiptCreationDatePst;
    @JsonAlias("receipt_type")
    private String receiptType;
    @JsonAlias("request_date")
    private String requestDate;
    @JsonAlias("request_date_ms")
    private String requestDateMs;
    @JsonAlias("request_date_pst")
    private String requestDatePst;
    @JsonAlias("version_external_identifier")
    private Integer versionExternalIdentifier;

    public Number getAdamId() {
        return this.adamId;
    }

    public Number getApp_item_id() {
        return this.app_item_id;
    }

    public String getApplicationVersion() {
        return this.applicationVersion;
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public Long getDownloadId() {
        return this.downloadId;
    }

    public List<InApp> getInApp() {
        return this.inApp;
    }

    public String getOriginalApplicationVersion() {
        return this.originalApplicationVersion;
    }

    public String getOriginalPurchaseDate() {
        return this.originalPurchaseDate;
    }

    public String getOriginalPurchaseDateMs() {
        return this.originalPurchaseDateMs;
    }

    public String getOriginalPurchaseDatePst() {
        return this.originalPurchaseDatePst;
    }

    public String getReceiptCreationDate() {
        return this.receiptCreationDate;
    }

    public String getReceiptCreationDateMs() {
        return this.receiptCreationDateMs;
    }

    public String getReceiptCreationDatePst() {
        return this.receiptCreationDatePst;
    }

    public String getReceiptType() {
        return this.receiptType;
    }

    public String getRequestDate() {
        return this.requestDate;
    }

    public String getRequestDateMs() {
        return this.requestDateMs;
    }

    public String getRequestDatePst() {
        return this.requestDatePst;
    }

    public Integer getVersionExternalIdentifier() {
        return this.versionExternalIdentifier;
    }

    public String toString() {
        return "Receipt(adamId=" + this.getAdamId() + ", app_item_id=" + this.getApp_item_id() + ", applicationVersion=" + this.getApplicationVersion() + ", bundleId=" + this.getBundleId() + ", downloadId=" + this.getDownloadId() + ", inApp=" + this.getInApp() + ", originalApplicationVersion=" + this.getOriginalApplicationVersion() + ", originalPurchaseDate=" + this.getOriginalPurchaseDate() + ", originalPurchaseDateMs=" + this.getOriginalPurchaseDateMs() + ", originalPurchaseDatePst=" + this.getOriginalPurchaseDatePst() + ", receiptCreationDate=" + this.getReceiptCreationDate() + ", receiptCreationDateMs=" + this.getReceiptCreationDateMs() + ", receiptCreationDatePst=" + this.getReceiptCreationDatePst() + ", receiptType=" + this.getReceiptType() + ", requestDate=" + this.getRequestDate() + ", requestDateMs=" + this.getRequestDateMs() + ", requestDatePst=" + this.getRequestDatePst() + ", versionExternalIdentifier=" + this.getVersionExternalIdentifier() + ")";
    }
}
