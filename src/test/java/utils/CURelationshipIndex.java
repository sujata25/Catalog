package utils;

public enum CURelationshipIndex{
    PARENT_ISBN(0),
    PARENT_CU_INCLUSION(1),
    PARENT_CU_EXCLUSION_REASON(2),
    RELSHIP_TYPE(3),
    RELSHIP_ACTIVE(4),
    RELATED_PRODUCT_ISBN(5),
    RELATED_PRODUCT_CU_INCLUSION(6),
	RELATED_PRODUCT_CU_EXCLUSION_REASON(7),
	PARENT_MEDIA(8),
	PARENT_ITEM(9),
	PARENT_EBOOK(10),
	PARENT_STOCKING(11),
	PRODUCT_MEDIA(12),
	PRODUCT_ITEM(13),
	PRODUCT_EBOOK(14),
	PRODUCT_STOCKING(15);


    private final int index;

    CURelationshipIndex(final int newIndex) {
        index = newIndex;
    }

    public int getIndex() { return index; }
}