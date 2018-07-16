
package pojo.CoursewareAlternateProduct;

import java.util.HashMap;
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
    "id",
    "name",
    "multiselect",
    "dimensionName",
    "dimensionId"
})
public class SelectedRefinement {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("multiselect")
    private Boolean multiselect;
    @JsonProperty("dimensionName")
    private String dimensionName;
    @JsonProperty("dimensionId")
    private Integer dimensionId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("multiselect")
    public Boolean getMultiselect() {
        return multiselect;
    }

    @JsonProperty("multiselect")
    public void setMultiselect(Boolean multiselect) {
        this.multiselect = multiselect;
    }

    @JsonProperty("dimensionName")
    public String getDimensionName() {
        return dimensionName;
    }

    @JsonProperty("dimensionName")
    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    @JsonProperty("dimensionId")
    public Integer getDimensionId() {
        return dimensionId;
    }

    @JsonProperty("dimensionId")
    public void setDimensionId(Integer dimensionId) {
        this.dimensionId = dimensionId;
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
        return new HashCodeBuilder().append(id).append(name).append(multiselect).append(dimensionName).append(dimensionId).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SelectedRefinement) == false) {
            return false;
        }
        SelectedRefinement rhs = ((SelectedRefinement) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(multiselect, rhs.multiselect).append(dimensionName, rhs.dimensionName).append(dimensionId, rhs.dimensionId).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
