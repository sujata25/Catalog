
package pojo.CoursewareSurcharge;

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
    "status",
    "params",
    "dimensions",
    "totalRecords",
    "records",
    "guidedNavigation",
    "selectedRefinements",
    "searchReports",
    "redirect"
})
public class Surchage {

    @JsonProperty("status")
    private Status status;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("dimensions")
    private Object dimensions;
    @JsonProperty("totalRecords")
    private Integer totalRecords;
    @JsonProperty("records")
    private List<Record> records = new ArrayList<Record>();
    @JsonProperty("guidedNavigation")
    private List<Object> guidedNavigation = new ArrayList<Object>();
    @JsonProperty("selectedRefinements")
    private List<SelectedRefinement> selectedRefinements = new ArrayList<SelectedRefinement>();
    @JsonProperty("searchReports")
    private Object searchReports;
    @JsonProperty("redirect")
    private String redirect;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("params")
    public Params getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(Params params) {
        this.params = params;
    }

    @JsonProperty("dimensions")
    public Object getDimensions() {
        return dimensions;
    }

    @JsonProperty("dimensions")
    public void setDimensions(Object dimensions) {
        this.dimensions = dimensions;
    }

    @JsonProperty("totalRecords")
    public Integer getTotalRecords() {
        return totalRecords;
    }

    @JsonProperty("totalRecords")
    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    @JsonProperty("records")
    public List<Record> getRecords() {
        return records;
    }

    @JsonProperty("records")
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @JsonProperty("guidedNavigation")
    public List<Object> getGuidedNavigation() {
        return guidedNavigation;
    }

    @JsonProperty("guidedNavigation")
    public void setGuidedNavigation(List<Object> guidedNavigation) {
        this.guidedNavigation = guidedNavigation;
    }

    @JsonProperty("selectedRefinements")
    public List<SelectedRefinement> getSelectedRefinements() {
        return selectedRefinements;
    }

    @JsonProperty("selectedRefinements")
    public void setSelectedRefinements(List<SelectedRefinement> selectedRefinements) {
        this.selectedRefinements = selectedRefinements;
    }

    @JsonProperty("searchReports")
    public Object getSearchReports() {
        return searchReports;
    }

    @JsonProperty("searchReports")
    public void setSearchReports(Object searchReports) {
        this.searchReports = searchReports;
    }

    @JsonProperty("redirect")
    public String getRedirect() {
        return redirect;
    }

    @JsonProperty("redirect")
    public void setRedirect(String redirect) {
        this.redirect = redirect;
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
        return new HashCodeBuilder().append(status).append(params).append(dimensions).append(totalRecords).append(records).append(guidedNavigation).append(selectedRefinements).append(searchReports).append(redirect).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Surchage) == false) {
            return false;
        }
        Surchage rhs = ((Surchage) other);
        return new EqualsBuilder().append(status, rhs.status).append(params, rhs.params).append(dimensions, rhs.dimensions).append(totalRecords, rhs.totalRecords).append(records, rhs.records).append(guidedNavigation, rhs.guidedNavigation).append(selectedRefinements, rhs.selectedRefinements).append(searchReports, rhs.searchReports).append(redirect, rhs.redirect).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
