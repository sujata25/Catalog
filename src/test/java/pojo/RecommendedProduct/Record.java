
package pojo.RecommendedProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "epi",
    "isbn13",
    "isbn10",
    "edition",
    "newFlag",
    "authorLast",
    "authors",
    "metaDescription",
    "metaKeywords",
    "pacIsbn10",
    "pacIsbn13",
    "copyrightYear",
    "lowestPrice",
    "smpDescription",
    "pubDate",
    "publishAvailability",
    "subtitle",
    "unlimited",
    "dims",
    "cuRecIsbns",
    "fulfillmentTypes",
    "cuEBookIsbns",
    "digitalOptions",
    "formats",
    "printRentals",
    "expandedPlatforms"
})
public class Record {

    @JsonProperty("title")
    private String title;
    @JsonProperty("epi")
    private String epi;
    @JsonProperty("isbn13")
    private String isbn13;
    @JsonProperty("isbn10")
    private String isbn10;
    @JsonProperty("edition")
    private String edition;
    @JsonProperty("newFlag")
    private Boolean newFlag;
    @JsonProperty("authorLast")
    private Object authorLast;
    @JsonProperty("authors")
    private List<Author> authors = new ArrayList<Author>();
    @JsonProperty("metaDescription")
    private Object metaDescription;
    @JsonProperty("metaKeywords")
    private Object metaKeywords;
    @JsonProperty("pacIsbn10")
    private Object pacIsbn10;
    @JsonProperty("pacIsbn13")
    private Object pacIsbn13;
    @JsonProperty("copyrightYear")
    private String copyrightYear;
    @JsonProperty("lowestPrice")
    private Object lowestPrice;
    @JsonProperty("smpDescription")
    private String smpDescription;
    @JsonProperty("pubDate")
    private String pubDate;
    @JsonProperty("publishAvailability")
    private String publishAvailability;
    @JsonProperty("subtitle")
    private Object subtitle;
    @JsonProperty("unlimited")
    private Boolean unlimited;
    @JsonProperty("dims")
    private Dims dims;
    @JsonProperty("cuRecIsbns")
    private List<String> cuRecIsbns = new ArrayList<String>();
    @JsonProperty("fulfillmentTypes")
    private Object fulfillmentTypes;
    @JsonProperty("cuEBookIsbns")
    private List<String> cuEBookIsbns = new ArrayList<String>();
    @JsonProperty("digitalOptions")
    private Object digitalOptions;
    @JsonProperty("formats")
    private Object formats;
    @JsonProperty("printRentals")
    private Object printRentals;
    @JsonProperty("expandedPlatforms")
    private Object expandedPlatforms;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("epi")
    public String getEpi() {
        return epi;
    }

    @JsonProperty("epi")
    public void setEpi(String epi) {
        this.epi = epi;
    }

    @JsonProperty("isbn13")
    public String getIsbn13() {
        return isbn13;
    }

    @JsonProperty("isbn13")
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    @JsonProperty("isbn10")
    public String getIsbn10() {
        return isbn10;
    }

    @JsonProperty("isbn10")
    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    @JsonProperty("edition")
    public String getEdition() {
        return edition;
    }

    @JsonProperty("edition")
    public void setEdition(String edition) {
        this.edition = edition;
    }

    @JsonProperty("newFlag")
    public Boolean getNewFlag() {
        return newFlag;
    }

    @JsonProperty("newFlag")
    public void setNewFlag(Boolean newFlag) {
        this.newFlag = newFlag;
    }

    @JsonProperty("authorLast")
    public Object getAuthorLast() {
        return authorLast;
    }

    @JsonProperty("authorLast")
    public void setAuthorLast(Object authorLast) {
        this.authorLast = authorLast;
    }

    @JsonProperty("authors")
    public List<Author> getAuthors() {
        return authors;
    }

    @JsonProperty("authors")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonProperty("metaDescription")
    public Object getMetaDescription() {
        return metaDescription;
    }

    @JsonProperty("metaDescription")
    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    @JsonProperty("metaKeywords")
    public Object getMetaKeywords() {
        return metaKeywords;
    }

    @JsonProperty("metaKeywords")
    public void setMetaKeywords(Object metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    @JsonProperty("pacIsbn10")
    public Object getPacIsbn10() {
        return pacIsbn10;
    }

    @JsonProperty("pacIsbn10")
    public void setPacIsbn10(Object pacIsbn10) {
        this.pacIsbn10 = pacIsbn10;
    }

    @JsonProperty("pacIsbn13")
    public Object getPacIsbn13() {
        return pacIsbn13;
    }

    @JsonProperty("pacIsbn13")
    public void setPacIsbn13(Object pacIsbn13) {
        this.pacIsbn13 = pacIsbn13;
    }

    @JsonProperty("copyrightYear")
    public String getCopyrightYear() {
        return copyrightYear;
    }

    @JsonProperty("copyrightYear")
    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    @JsonProperty("lowestPrice")
    public Object getLowestPrice() {
        return lowestPrice;
    }

    @JsonProperty("lowestPrice")
    public void setLowestPrice(Object lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    @JsonProperty("smpDescription")
    public String getSmpDescription() {
        return smpDescription;
    }

    @JsonProperty("smpDescription")
    public void setSmpDescription(String smpDescription) {
        this.smpDescription = smpDescription;
    }

    @JsonProperty("pubDate")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("pubDate")
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @JsonProperty("publishAvailability")
    public String getPublishAvailability() {
        return publishAvailability;
    }

    @JsonProperty("publishAvailability")
    public void setPublishAvailability(String publishAvailability) {
        this.publishAvailability = publishAvailability;
    }

    @JsonProperty("subtitle")
    public Object getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(Object subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("unlimited")
    public Boolean getUnlimited() {
        return unlimited;
    }

    @JsonProperty("unlimited")
    public void setUnlimited(Boolean unlimited) {
        this.unlimited = unlimited;
    }

    @JsonProperty("dims")
    public Dims getDims() {
        return dims;
    }

    @JsonProperty("dims")
    public void setDims(Dims dims) {
        this.dims = dims;
    }

    @JsonProperty("cuRecIsbns")
    public List<String> getCuRecIsbns() {
        return cuRecIsbns;
    }

    @JsonProperty("cuRecIsbns")
    public void setCuRecIsbns(List<String> cuRecIsbns) {
        this.cuRecIsbns = cuRecIsbns;
    }

    @JsonProperty("fulfillmentTypes")
    public Object getFulfillmentTypes() {
        return fulfillmentTypes;
    }

    @JsonProperty("fulfillmentTypes")
    public void setFulfillmentTypes(Object fulfillmentTypes) {
        this.fulfillmentTypes = fulfillmentTypes;
    }

    @JsonProperty("cuEBookIsbns")
    public List<String> getCuEBookIsbns() {
        return cuEBookIsbns;
    }

    @JsonProperty("cuEBookIsbns")
    public void setCuEBookIsbns(List<String> cuEBookIsbns) {
        this.cuEBookIsbns = cuEBookIsbns;
    }

    @JsonProperty("digitalOptions")
    public Object getDigitalOptions() {
        return digitalOptions;
    }

    @JsonProperty("digitalOptions")
    public void setDigitalOptions(Object digitalOptions) {
        this.digitalOptions = digitalOptions;
    }

    @JsonProperty("formats")
    public Object getFormats() {
        return formats;
    }

    @JsonProperty("formats")
    public void setFormats(Object formats) {
        this.formats = formats;
    }

    @JsonProperty("printRentals")
    public Object getPrintRentals() {
        return printRentals;
    }

    @JsonProperty("printRentals")
    public void setPrintRentals(Object printRentals) {
        this.printRentals = printRentals;
    }

    @JsonProperty("expandedPlatforms")
    public Object getExpandedPlatforms() {
        return expandedPlatforms;
    }

    @JsonProperty("expandedPlatforms")
    public void setExpandedPlatforms(Object expandedPlatforms) {
        this.expandedPlatforms = expandedPlatforms;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(title).append(epi).append(isbn13).append(isbn10).append(edition).append(newFlag).append(authorLast).append(authors).append(metaDescription).append(metaKeywords).append(pacIsbn10).append(pacIsbn13).append(copyrightYear).append(lowestPrice).append(smpDescription).append(pubDate).append(publishAvailability).append(subtitle).append(unlimited).append(dims).append(cuRecIsbns).append(fulfillmentTypes).append(cuEBookIsbns).append(digitalOptions).append(formats).append(printRentals).append(expandedPlatforms).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Record) == false) {
            return false;
        }
        Record rhs = ((Record) other);
        return new EqualsBuilder().append(title, rhs.title).append(epi, rhs.epi).append(isbn13, rhs.isbn13).append(isbn10, rhs.isbn10).append(edition, rhs.edition).append(newFlag, rhs.newFlag).append(authorLast, rhs.authorLast).append(authors, rhs.authors).append(metaDescription, rhs.metaDescription).append(metaKeywords, rhs.metaKeywords).append(pacIsbn10, rhs.pacIsbn10).append(pacIsbn13, rhs.pacIsbn13).append(copyrightYear, rhs.copyrightYear).append(lowestPrice, rhs.lowestPrice).append(smpDescription, rhs.smpDescription).append(pubDate, rhs.pubDate).append(publishAvailability, rhs.publishAvailability).append(subtitle, rhs.subtitle).append(unlimited, rhs.unlimited).append(dims, rhs.dims).append(cuRecIsbns, rhs.cuRecIsbns).append(fulfillmentTypes, rhs.fulfillmentTypes).append(cuEBookIsbns, rhs.cuEBookIsbns).append(digitalOptions, rhs.digitalOptions).append(formats, rhs.formats).append(printRentals, rhs.printRentals).append(expandedPlatforms, rhs.expandedPlatforms).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
