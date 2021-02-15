package az.ibar.bcmsproduct.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SortBy {

    UNIT_PRICE("unitPrice"),
    INVENTORY("inventory");

    private final String fieldName;
}
