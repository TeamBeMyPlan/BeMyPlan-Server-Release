package com.deploy.bemyplan.external.client.apple.purchase.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
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
    private Integer downloadId;
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
}
